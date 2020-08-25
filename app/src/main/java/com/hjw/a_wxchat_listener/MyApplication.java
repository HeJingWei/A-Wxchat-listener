package com.hjw.a_wxchat_listener;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.net.RetrofitManager;
import com.hjw.a_wxchat_listener.service.ConnectService;

public class MyApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ContextManager.setApplicationContext(this);
        ConnectService.getInstance().setContext(this);
        RetrofitManager.getInstance().init();
    }
}
