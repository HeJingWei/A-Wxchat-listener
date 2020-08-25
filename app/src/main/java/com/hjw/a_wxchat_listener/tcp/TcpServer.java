package com.hjw.a_wxchat_listener.tcp;

import com.hjw.a_wxchat_listener.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer {
    private final int mPort;
    private final ExecutorService mExecutorService;

    public TcpServer(int port) {
        mPort = port;

        mExecutorService = Executors.newFixedThreadPool(4);
    }

    public void run() {
        mExecutorService.submit(() -> {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(mPort);
            } catch (IOException e) {
                LogUtils.logError(e);
                return;
            }
            try {
                while (true) {
                    Socket client = serverSocket.accept();
                    handleClient(client);
                }
            } catch (IOException e) {
                LogUtils.logError(e);
            }
        });
    }

    private void handleClient(Socket socket) {
        mExecutorService.submit(() -> {
            try {
                doHandleClient(socket);
            } catch (IOException e) {
                LogUtils.logError(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    LogUtils.logError(e);
                }
            }
        });
    }

    private void doHandleClient(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
        }
    }

}
