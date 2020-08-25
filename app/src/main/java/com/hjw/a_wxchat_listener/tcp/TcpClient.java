package com.hjw.a_wxchat_listener.tcp;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.net.NetConstant;
import com.hjw.a_wxchat_listener.net.RequestManager;
import com.hjw.a_wxchat_listener.service.AppSendData;
import com.hjw.a_wxchat_listener.service.ConnectService;
import com.hjw.a_wxchat_listener.service.model.CollectBillModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.tcp.model.LogicTypeModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class TcpClient {
    private final SocketManager mSocketManager;

    private TcpClient(String host, int port) {
        mSocketManager = new SocketManager(host, port, new SocketManager.DataCallback() {
            @Override
            public void onData(byte[] data, int offset, int len) {
                LogUtils.log(" socket received: " + new String(data, offset, len));
                String dataStr = new String(data, offset, len);
                TcpReceive.handle(dataStr);
            }
        }, new SocketManager.ErrorCallback() {
            @Override
            public void onError() {
                EventModel.postLog("TCP无法连接");
            }
        });
    }

    public void handleCollects(String taskId, ResultData resultData, String logicType) {
        long pubTime = SpUtils.getPubTime();
        List<CollectBillModel> collectBillModels = (List<CollectBillModel>) resultData.getData();
        if (pubTime > 0) {
            List<CollectBillModel> postCollects = new ArrayList<>();
            for (int i = 0; i < collectBillModels.size(); i++) {
                if (collectBillModels.get(i).getPubTime() > pubTime) {
                    postCollects.add(collectBillModels.get(i));
                } else {
                    break;
                }
            }
            if (postCollects.size() != 0) {
                RequestManager.postNewCollect(postCollects);
            }
        } else {
            if (collectBillModels.get(0) != null) {
                SpUtils.setPubTime(collectBillModels.get(0).getPubTime());
            }
            RequestManager.postNewCollect(collectBillModels);
        }
        sendWxData(taskId, resultData, logicType);
    }

    public void sendWxData(String taskId, ResultData resultData, String logicType) {
        LogicTypeModel logicTypeModel = new LogicTypeModel();
        logicTypeModel.setLogic_type(logicType);
        logicTypeModel.setUserId(ConnectService.userId);
        logicTypeModel.setTaskId(taskId);
        logicTypeModel.setData(resultData);
        LogUtils.log(" tcp :" + JSON.toJSONString(logicTypeModel));
        send(JSON.toJSONString(logicTypeModel));
    }

    public void send(String msg) {
        LogUtils.log("tcp request：" + msg);
        if (mSocketManager.closed()) {
            EventModel.postLog("socket已关闭，请重新上线");
            return;
        }
        send(msg.getBytes());
    }

    public void send(byte[] bytes) {
        mSocketManager.write(bytes, new SocketManager.WritingCallback() {
            @Override
            public void onSuccess() {
                LogUtils.log("tcp send onSuccess");
                EventModel.postLog("TCP发送数据成功");
            }

            @Override
            public void onFail(byte[] data, int offset, int len) {
                LogUtils.log("onFail: fail to write: " + new String(data, offset, len));
                EventModel.postLog("TCP发送数据失败:" + new String(data, offset, len));
                mSocketManager.write(data, offset, len, this);
            }
        });
    }

    public static TcpClient connectSocket() {
        return new TcpClient(NetConstant.tcpHost, NetConstant.tcpPost);
    }

    public void disConnectSocket() {
        mSocketManager.close();
        EventModel.postLog("下线成功");
        LogUtils.log("socket已关闭");
    }

}
