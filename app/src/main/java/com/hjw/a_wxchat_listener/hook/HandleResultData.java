package com.hjw.a_wxchat_listener.hook;

import com.hjw.a_wxchat_listener.service.model.ResultData;

public class HandleResultData {
    public static <T> ResultData<T> handleResultDataSuccess(T t){
        ResultData<T> resultData = new ResultData<>();
        resultData.setData(t);
        resultData.setSuccess(true);
        return resultData;
    }

    public static <T> ResultData<T> handleResultDataFailed(String errorMsg){
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(false);
        resultData.setErrorMsg(errorMsg);
        return resultData;
    }
}
