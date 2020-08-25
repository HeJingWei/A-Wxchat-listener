package com.hjw.a_wxchat_listener.net;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.model.LoginModel;
import com.hjw.a_wxchat_listener.net.callback.OnLoadDataListener;
import com.hjw.a_wxchat_listener.net.request.LoginSend;
import com.hjw.a_wxchat_listener.net.service.NetApi;
import com.hjw.a_wxchat_listener.net.utils.SchedulerProvider;
import com.hjw.a_wxchat_listener.service.model.CollectBillModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.SpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestManager {
    @SuppressLint("CommitPrefEdits")
    public static void login(String userName, String password, OnLoadDataListener<LoginModel> onLoadDataListener) {
        LoginSend loginSend = new LoginSend();
        loginSend.setPwd(password);
        loginSend.setTime(System.currentTimeMillis());
        loginSend.setUserName(userName);

        RetrofitManager.getInstance()
                .createService(NetApi.class)
                .login(RetrofitManager.toRequestBody(loginSend))
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new BaseObserver<LoginModel>() {
                    @Override
                    public void onRequestSuccess(String msg, LoginModel loginResponseModel) {
                        SpUtils.setLoginUser(userName);
                        SpUtils.setLoginPwd(password);
                        onLoadDataListener.onSuccess(loginResponseModel);
                    }

                    @Override
                    public void onRequestError(String code, String errMessage) {
                        LogUtils.log("登录失败:"+errMessage);
                        onLoadDataListener.onFailure(code, errMessage);
                    }
                });
    }


    public static void postNewCollect(List<CollectBillModel> collects){
        Map<String,String> map = new HashMap<>();
        LogUtils.log("发送的账单："+JSON.toJSONString(collects));
        map.put("collect", JSON.toJSONString(collects));

        RetrofitManager.getInstance()
                .createService(NetApi.class)
                .newCollect(RetrofitManager.toRequestBody(map))
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onRequestSuccess(String msg, Object object) {
                        LogUtils.log("新账单发送成功");
                        EventModel.postLog("新账单发送成功");
                    }

                    @Override
                    public void onRequestError(String code, String errMessage) {
                        LogUtils.log("新账单发送失败:"+errMessage);
                        EventModel.postLog("新账单发送失败");
                    }
                });
    }
}
