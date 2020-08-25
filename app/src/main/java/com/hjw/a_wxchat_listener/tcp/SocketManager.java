package com.hjw.a_wxchat_listener.tcp;

import android.os.Handler;
import android.os.HandlerThread;

import com.alibaba.fastjson.JSON;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.service.AppSendData;
import com.hjw.a_wxchat_listener.service.ConnectService;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.tcp.model.LogicTypeModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketManager {
    private static final long HEART_BEAT_INTERVAL_MILLIS = 15 * 1000;

    /**
     * 错误回调
     */
    public interface ErrorCallback {
        void onError();
    }

    /**
     * 读数据回调
     */
    public interface DataCallback {
        void onData(byte[] data, int offset, int len);
    }

    /**
     * 写数据回调
     */
    public interface WritingCallback {
        void onSuccess();

        void onFail(byte[] data, int offset, int len);
    }

    private final String mHost;
    private final int mPort;
    private final DataCallback mDataCallback;
    private final ErrorCallback mErrorCallback;

    private final HandlerThread mWriterThread;
    private final Handler mWriterHandler;

    private final Object mLock = new Object();
    private Socket mSocket;
    private boolean mClosed;

    private final Runnable mHeartBeatTask = new Runnable() {

        @Override
        public void run() {
            LogicTypeModel logicTypeModel = new LogicTypeModel();
            logicTypeModel.setLogic_type(Constant.TCP_Heart);
            logicTypeModel.setUserId(ConnectService.userId);
            logicTypeModel.setTaskId("1234");
            write(JSON.toJSONString(logicTypeModel).getBytes(), new WritingCallback() {
                @Override
                public void onSuccess() {
                    // 每隔15s发送一次
                    mWriterHandler.postDelayed(mHeartBeatTask, HEART_BEAT_INTERVAL_MILLIS);
                }

                @Override
                public void onFail(byte[] data, int offset, int len) {
                    // nop
                    // write() 方法会处理失败
                    LogUtils.log("心跳包发送失败");
                }
            });
        }
    };


    public SocketManager(String host, int port,
                         DataCallback dataCallback, ErrorCallback errorCallback) {
        mHost = host;
        mPort = port;
        mDataCallback = dataCallback;
        mErrorCallback = errorCallback;
        mWriterThread = new HandlerThread("socket-writer");
        mWriterThread.start();
        mWriterHandler = new Handler(mWriterThread.getLooper());
        mWriterHandler.post(this::initSocket);
    }

    private void initSocket() {
        EventModel.postLog("开始上线");
        LogUtils.log("开始上线");
        while (true) {
            if (closed()) return;
            try {
                Socket socket = new Socket(mHost, mPort);
                synchronized (mLock) {
                    if (mClosed) {
                        return;
                    }
                    mSocket = socket;
                    Thread reader = new Thread(new ReaderTask(socket), "socket-reader");
                    reader.start();
                    mWriterHandler.post(mHeartBeatTask);
                }
                break;
            } catch (IOException e) {
                LogUtils.log("连接Socket error: ");
                LogUtils.logError(e);
                EventModel.postLog("上线失败");
                LogUtils.log("上线失败");
                mErrorCallback.onError();
                close();
            }
        }
        postTcpStatus(true);
        AppSendData.sendGetHeartState();
        EventModel.postLog("上线成功");
        LogUtils.log("上线成功");
    }

    public void write(byte[] data, WritingCallback callback) {
        write(data, 0, data.length, callback);
    }

    public void write(byte[] data, int offset, int len, WritingCallback callback) {
        mWriterHandler.post(() -> {
            Socket socket = getSocket();
            if (socket == null) {
                initSocket();
                socket = getSocket();
                if (socket == null) {
                    if (!closed()) {
                        callback.onFail(data, offset, len);
                    }
                    return;
                }
            }
            try {
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(outputStream);
                out.writeInt(len);
                out.write(data, offset, len);
                callback.onSuccess();
            } catch (IOException e) {
                LogUtils.log("Socket writeError: ");
                LogUtils.logError(e);
                callback.onFail(data, offset, len);
                close();
            }
        });
    }

    public boolean closed() {
        synchronized (mLock) {
            return mClosed;
        }
    }

    private Socket getSocket() {
        synchronized (mLock) {
            return mSocket;
        }
    }

    private void postTcpStatus(boolean isConnect){
        ResultData resultData = new ResultData();
        resultData.setData(isConnect);
        EventModel.postTcpLoginState(resultData);
    }

    void close() {
        postTcpStatus(false);
        new Thread() {
            @Override
            public void run() {
                doClose();
            }
        }.start();
    }

    private void doClose() {
        synchronized (mLock) {
            if (mSocket == null) return;
            mSocket = null;
            mWriterHandler.removeCallbacks(mHeartBeatTask);
            mClosed = true;
            mWriterThread.quit();
        }
    }

    private class ReaderTask implements Runnable {

        private final Socket mSocket;

        ReaderTask(Socket socket) {
            mSocket = socket;
        }

        @Override
        public void run() {
            try {
                readResponse();
            }
            catch (SocketException e){
                LogUtils.log("socket read exception: ");
                LogUtils.logError(e);
                EventModel.postLog("TCP连接断开");
                close();
            }
            catch (IOException e) {
                LogUtils.log("ReaderTask$run: ");
                LogUtils.logError(e);
                EventModel.postLog("TCP读取数据出错："+e.getMessage());
                close();
            }
        }

        private void readResponse() throws IOException {
            byte[] buffer = new byte[1024];
            InputStream inputStream = mSocket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);
            while (true) {
                int nbyte = in.readInt();
                if (read(in, buffer, nbyte) != 0) {
                    break;
                }
                mDataCallback.onData(buffer, 0, nbyte);
            }
        }

        private int read(InputStream in, byte[] buffer, int n) throws IOException {
            int offset = 0;
            while (n > 0) {
                int readBytes = in.read(buffer, offset, n);
                if (readBytes < 0) {
                    break;
                }
                n -= readBytes;
                offset += readBytes;
            }
            return n;
        }
    }
}
