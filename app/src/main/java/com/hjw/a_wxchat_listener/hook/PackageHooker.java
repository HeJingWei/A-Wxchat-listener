package com.hjw.a_wxchat_listener.hook;

import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;

import dalvik.system.DexFile;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageHooker {
    private static final String METHOD_TAG = "PackageHooker-methodName:";
    private static final String CLASS_TAG = "PackageHooker-className:";
    private static final String PARAM_TAG = "PackageHooker-param:";
    private static final String RES_TAG = "PackageHooker-result:";

    public static void hookPackage(XC_LoadPackage.LoadPackageParam param) {
        try {
            hook(param);
        } catch (Throwable e) {
            LogUtils.logError(e);
        }
    }

    private static void hook(final XC_LoadPackage.LoadPackageParam lparam) throws Throwable {
        DexFile dexFile = new DexFile(lparam.appInfo.sourceDir);
        Enumeration<String> classNames = dexFile.entries();
        while (classNames.hasMoreElements()) {
            String className = classNames.nextElement();
            if (isClassNameValid(lparam, className)) {
                final Class<?> clazz = Class.forName(className, false, lparam.classLoader);
                for (Method method : clazz.getDeclaredMethods()) {
                    int mod = method.getModifiers();
                    if (!Modifier.isAbstract(mod) && !Modifier.isNative(mod) && !Modifier.isInterface(mod) && !Modifier.isStatic(mod)) {
                        XposedBridge.hookMethod(method, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                StringBuilder sb = new StringBuilder();
                                sb.append(" - - - - - - - - - -");
                                sb.append("\n");
                                sb.append(CLASS_TAG);
                                sb.append(clazz.getName());
                                sb.append("\n");
                                sb.append(METHOD_TAG);
                                sb.append(param.method.getName());
                                sb.append("\n");
                                if (param.args != null) {
                                    for (int i = 0; i < param.args.length; i++) {
                                        sb.append(PARAM_TAG);
                                        String modName = " [" + i + "] :  ";
                                        sb.append(modName);
                                        sb.append(String.valueOf(param.args[i]));
                                        sb.append("\n");
                                        sb.append(Utils.getObjectAllFiled(param.args[i],null));
                                    }
                                }
                                sb.append(RES_TAG);
                                sb.append(param.getResult());
                                sb.append("\n");
                                sb.append(Utils.getObjectAllFiled(param.getResult(),null));
                                sb.append(" - - - - - - - - - - - ");
                                LogUtils.log(sb.toString());
                            }
                        });
                    }
                }

            }
        }
    }

    private static boolean isClassNameValid(XC_LoadPackage.LoadPackageParam param, String className) {
//        return className.startsWith(param.packageName)
        return className.startsWith("com.tencent")
                && !className.contains("$")
                && !className.contains("BuildConfig")
                && !className.contains(".R");
    }
}

