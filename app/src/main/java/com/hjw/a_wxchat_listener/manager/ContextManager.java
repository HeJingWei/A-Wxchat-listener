package com.hjw.a_wxchat_listener.manager;

import android.content.Context;

import java.lang.ref.WeakReference;

public class ContextManager {

    private static WeakReference<Context> m_appContext = null;

    public static Context getApplicationContext() {
        if (m_appContext == null) {
            return null;
        }

        return m_appContext.get();
    }

    public static void setApplicationContext(Context context) {
        m_appContext = new WeakReference<>(context);
    }
}
