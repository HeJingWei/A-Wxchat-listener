package com.hjw.a_wxchat_listener.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.service.ConnectService;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Utils {
    public static String getObjectAllFiled(Object object, List<String> classNames) throws Throwable {
        if (object == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("class = ");
        stringBuilder.append(object.getClass().getName());
        stringBuilder.append("\n{");
        Class<?> clazz = object.getClass();
        if (clazz.getName().equals("java.lang.Object")) {
            stringBuilder.append("java.lang.Object}");
            return stringBuilder.toString();
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        while (declaredFields != null) {
            for (Field field : declaredFields) {
                stringBuilder.append("\n\t");
                int mod = field.getModifiers();
                stringBuilder.append((mod == 0) ? "" : (Modifier.toString(mod) + " "));
                stringBuilder.append(field.getType().getName());
                stringBuilder.append(" ");
                stringBuilder.append(field.getName());
                try {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(object);
                    if (value == null) {
                        stringBuilder.append(" == null");
                    } else if (classNames != null && containsString(classNames, value.getClass().getName())) {
                        stringBuilder.append(" = ");
                        stringBuilder.append(getObjectAllFiled(value, classNames));
                    } else {
                        stringBuilder.append(" = ");
                        stringBuilder.append(String.valueOf(value));
                    }
                } catch (Throwable e) {
                    stringBuilder.append("- err = ");
                    stringBuilder.append(e.toString());
                }
            }
            stringBuilder.append("\n---------\n");
            clazz = clazz.getSuperclass();
            if (clazz == null) {
                break;
            }
            if (clazz.getName().equals("java.lang.Object")) {
                stringBuilder.append("java.lang.Object");
                break;
            }
            declaredFields = clazz.getDeclaredFields();
        }
        stringBuilder.append("\n\b}\n--");
        return stringBuilder.toString();
    }

    private static boolean containsString(List<String> strs, String subStr) {
        if (strs == null) {
            return false;
        }
        for (String str : strs) {
            if (str.equals(subStr)) {
                return true;
            }
        }
        return false;
    }


    public static boolean checkRunning(Context context, String processname) {
        try {
            if (TextUtils.isEmpty(processname)) return false;

            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (manager == null) return false;

            List<ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo info : list) {
                if (processname.equals(info.processName)) return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInVXApp(Context context) {
        try {
            if (!checkRunning(context, "io.va.exposed")) {
                return false;
            }

            if (!context.getPackageResourcePath().contains("io.va.exposed")) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInstallApp(Context anyContext, String packageName) {
        try {
            PackageInfo packageInfo = getPackageInfo(anyContext, packageName);
            if (packageInfo == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static PackageInfo getPackageInfo(Context anyContext, String packageName) {
        try {
            PackageManager packageManager = anyContext.getPackageManager();
//            log("getPackageInfo - " + packageName + ", context = " + anyContext);

            return packageManager.getPackageInfo(packageName, 0);
        } catch (Exception e) {
            LogUtils.log("getPackageInfo error0 = " + e);
            return null;
        } catch (Throwable e) {
            LogUtils.log("getPackageInfo error = " + e);
            return null;
        }
    }

    public static boolean checkTargetVersion(Context context) {
        String ver = getPackageVersion(context, Constant.target_package_name);
        if (Constant.target_package_name_version.equals(ver)) return true;
        return false;
    }


    public static String getPackageVersion(Context anyContext, String packageName) {
        String name = "";
        try {
            PackageInfo packageInfo = getPackageInfo(anyContext, packageName);
            if (packageInfo != null) {
                name = packageInfo.versionName;
            }

            return name;
        } catch (Exception e) {
            return name;
        }
    }

    public static boolean isAppRun() {
        Context context = ConnectService.getInstance().getContext();
        if (context == null) {
            return false;
        }
        return Utils.checkRunning(context, Constant.target_package_name);
    }

    public static boolean openApp(Context anyContext, String packageName) {
//        log("openApp - " + packageName + ", context = " + anyContext);
        if (getPackageInfo(anyContext, packageName) == null) {
            LogUtils.log("没有安装: " + packageName);
            return false;
        } else {
            try {
                PackageManager packageManager = anyContext.getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                anyContext.startActivity(intent);
                return true;
            } catch (Throwable e) {
                LogUtils.log("openApp error = " + e);
                return false;
            }
        }
    }

    /**
     * 判断网络情况
     */
    @SuppressLint("NewApi")
    public static boolean isNetworkConnected() {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(ContextManager.getApplicationContext())
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null && connectivityManager.getActiveNetwork() != null;
    }
}
