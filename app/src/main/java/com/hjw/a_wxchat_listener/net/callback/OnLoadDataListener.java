package com.hjw.a_wxchat_listener.net.callback;

public interface OnLoadDataListener<T> {
    /**
     * 成功
     * @param t 数据
     */
    void onSuccess(T t);

    /**
     * 失败
     * @param errorMsg 错误信息
     */
    void onFailure(String code,String errorMsg);
}
