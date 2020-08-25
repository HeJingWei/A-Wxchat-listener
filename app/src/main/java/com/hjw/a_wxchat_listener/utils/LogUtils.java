package com.hjw.a_wxchat_listener.utils;

import android.util.Log;

public class LogUtils {

    public static final String LOG_TAG = "hook_wechat";
    public static final String LOG_ERROR_TAG = "hook_wechat_error";

    private LogUtils(){

    }

    public static void log(String msg){
        Log.e(LOG_TAG,msg);
    }

    public static void logError(Throwable msg){
        Log.e(LOG_ERROR_TAG,Log.getStackTraceString(msg));
    }
}
