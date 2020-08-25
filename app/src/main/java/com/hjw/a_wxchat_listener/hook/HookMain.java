package com.hjw.a_wxchat_listener.hook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.service.ConnectWechatClient;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {

    public final static String PACKAGE_NAME = "com.tencent.mm";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.appInfo != null && lpparam.packageName.equals(PACKAGE_NAME)) {
            hookStart(lpparam);
        }
    }

    private void hookStart(final XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ContextManager.setApplicationContext((Context) param.thisObject);
                    HookWechat.setClassLoader(lpparam);

                    HookWechat.hookWechat();

                    activityListener(lpparam, (Application) param.thisObject);
                }
            });
        } catch (Throwable e) {
            LogUtils.logError(e);
        }
    }

    private void activityListener(final XC_LoadPackage.LoadPackageParam lpparam, Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtils.log("activity onCreate:" + activity.getClass().getName());
                if (TextUtils.equals("com.tencent.mm.ui.LauncherUI", activity.getClass().getName())) {
                    ConnectWechatClient.getInstance().connectToService();
                    HookWechat.emptyQrCodeListener();
                    HookWechat.qrCodeListener();
                    HookWechat.setCollectMoneyListener();
                }
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull final Activity activity) {
                LogUtils.log("activity onResume:" + activity.getClass().getName());
                if (TextUtils.equals(activity.getClass().getName(), "com.tencent.mm.ui.LauncherUI")) {

                    //查看调用参数class和返回
//                    Class<?> n = XposedHelpers.findClass("com.tencent.mm.al.n", lpparam.classLoader);
//                    XposedHelpers.findAndHookMethod("com.tencent.mm.al.q", lpparam.classLoader, "b",
//                            n,
//                            int.class,
//                            new XC_MethodHook() {
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    if (param.args != null) {
//                                        for (int i = 0; i < param.args.length; i++) {
//                                            LogUtils.log("q.b param " + i + "  : " + param.args[i]);
//                                            LogUtils.log("q.b param class" + i + "  : " + Utils.getObjectAllFiled(param.args[i], null));
//                                        }
//                                    }
//                                }
//                            }
//                    );
//
//                    XposedHelpers.findAndHookMethod("com.tencent.mm.al.q", lpparam.classLoader, "onSceneEnd",
//                            int.class,
//                            int.class,
//                            String.class,
//                            n,
//                            new XC_MethodHook() {
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    if (param.args != null) {
//                                        for (int i = 0; i < param.args.length; i++) {
//                                            LogUtils.log("q.onSceneEnd param " + i + "  : " + param.args[i]);
//                                            LogUtils.log("q.onSceneEnd param class" + i + "  : " + Utils.getObjectAllFiled(param.args[i], null));
//                                        }
//                                    }
//                                }
//                            }
//                    );

                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
}
