package com.hjw.a_wxchat_listener.net;

import com.google.gson.JsonParseException;
import com.hjw.a_wxchat_listener.utils.LogUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<NetResponse<T>> {
    /**
     * 未知错误
     */
    public static final String UNKNOWN = "UNKNOWN";

    /**
     * 解析错误
     */
    public static final String PARSE_ERROR = "PARSE_ERROR";

    /**
     * 网络错误
     */
    public static final String NETWORK_ERROR = "NETWORK_ERROR";

    /**
     * 协议错误
     */
    public static final String HTTP_ERROR = "HTTP_ERROR";

    @Override
    public void onError(Throwable e) {
        LogUtils.log("http错误："+e.getMessage());
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onRequestError(PARSE_ERROR, "数据解析错误");
        } else if (e instanceof ConnectException || e instanceof UnknownServiceException) {
            onRequestError(NETWORK_ERROR, "网络连接错误");
        } else if (e instanceof HttpException || e instanceof UnknownHostException ||  e instanceof SocketTimeoutException){
            onRequestError(HTTP_ERROR, "网络异常");
        } else {
            onRequestError(UNKNOWN, "未知错误");
        }
    }

    @Override
    public void onNext(NetResponse<T> tBaseResponse) {
        if (tBaseResponse.isSuccess()) {
            onRequestSuccess(tBaseResponse.getMsg(), tBaseResponse.getData());
        } else {
            onRequestError(tBaseResponse.getCode(), tBaseResponse.getMsg());
        }
    }

    //具体实现下面两个方法，便可从中得到更直接详细的信息
    public abstract void onRequestSuccess(String msg, T t);

    public abstract void onRequestError(String code, String errMessage);

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }
}
