package com.hjw.a_wxchat_listener.utils;

import android.util.Log;

import com.hjw.a_wxchat_listener.manager.ContextManager;

public class SpUtils {
    interface Constant {
        String sp_login = "spLogin";
        String sp_login_name = "spLoginName";
        String sp_login_password = "spLoginPassword";

        String pubTime = "pubTime";
        String sp_pubTime = "spPubTime";
    }

    public static String getLoginUser() {
        return ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_login, 0).getString(Constant.sp_login_name, "");
    }

    public static String getLoginPwd() {
        return ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_login, 0).getString(Constant.sp_login_password, "");
    }

    public static void setLoginUser(String userName) {
        ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_login, 0).edit().putString(Constant.sp_login_name, userName).apply();
    }

    public static void setLoginPwd(String password) {
        ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_login, 0).edit().putString(Constant.sp_login_password, password).apply();
    }

    public static void setPubTime(long pubTime) {
        ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_pubTime, 0).edit().putLong(Constant.pubTime, pubTime).apply();
    }

    public static long getPubTime() {
        Long pubTime = ContextManager.getApplicationContext().getSharedPreferences(Constant.sp_pubTime, 0).getLong(Constant.pubTime, 0);
        LogUtils.log("pubTime :" + pubTime);
        return pubTime;
    }
}
