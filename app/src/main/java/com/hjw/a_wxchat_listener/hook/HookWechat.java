package com.hjw.a_wxchat_listener.hook;

import android.content.ContentValues;
import android.text.TextUtils;

import com.hjw.a_wxchat_listener.service.WechatSendData;
import com.hjw.a_wxchat_listener.service.model.CollectBillModel;
import com.hjw.a_wxchat_listener.service.model.EmptyQrCodeModel;
import com.hjw.a_wxchat_listener.service.model.QrCodeModel;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.service.model.UserInfoModel;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;


import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import JSONJava.JSONArray;
import JSONJava.XML;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookWechat {

    public static ClassLoader mClassLoader;

    private static HashMap<String, Integer> mListenerMaps;

    public static void setClassLoader(XC_LoadPackage.LoadPackageParam lpparam) {
        mListenerMaps = new HashMap<>();
        mListenerMaps.put(HookConstant.EMPTY_QR_CODE, 0);
        mListenerMaps.put(HookConstant.QR_CODE, 0);
        mClassLoader = lpparam.classLoader;
    }

    public static void hookWechat() {
        Class<?> aeClass = XposedHelpers.findClass("com.tencent.mm.app.ae", mClassLoader);
        XposedHelpers.findAndHookMethod(aeClass, "b", StackTraceElement[].class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                return false;
            }
        });
    }

    public static void isLogin() {
        ResultData<Boolean> objectResultData;
        try {
            boolean isLogin = HookTargetMethod.getLoginState(mClassLoader);
            LogUtils.log("hook wechat 获取登录状态isLogin:" + isLogin);
            objectResultData = HandleResultData.handleResultDataSuccess(isLogin);
        } catch (Throwable e) {
            objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
        }
        WechatSendData.sendGetLoginState(objectResultData);
    }


    public static void getUserName() {
        ResultData<UserInfoModel> objectResultData;
        try {
            if (!HookTargetMethod.getLoginState(mClassLoader)) {
                objectResultData = HandleResultData.handleResultDataFailed("微信未登录,请重新登录");
            } else {
                String userName = HookTargetMethod.getUserId(mClassLoader);
                UserInfoModel userInfoModel = new UserInfoModel();
                userInfoModel.setUserName(userName);
                LogUtils.log("hook wechat 获取userName返回：" + userName);
                objectResultData = HandleResultData.handleResultDataSuccess(userInfoModel);
            }
        } catch (Throwable e) {
            LogUtils.log("getUserName error");
            LogUtils.logError(e);
            objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
        }
        WechatSendData.sendGetLoginUser(objectResultData);
    }

    public static void getEmptyQrCode() {
        try {
            Object o = HookTargetMethod.getEmptyQrCode(mClassLoader);
            mListenerMaps.put(HookConstant.EMPTY_QR_CODE, o.hashCode());
        } catch (Throwable e) {
            LogUtils.log("getEmptyQrCode error");
            LogUtils.logError(e);
            ResultData<EmptyQrCodeModel> objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
            WechatSendData.sendGetEmptyQrCode(objectResultData);
        }
    }

    public static void emptyQrCodeListener() {
        try {
            XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.collect.model.l", mClassLoader, "onGYNetEnd",
                    int.class, int.class, int.class, String.class,
                    XposedHelpers.findClass("com.tencent.mm.network.q", mClassLoader),
                    byte[].class,
                    new XC_MethodHook() {
                        ResultData<EmptyQrCodeModel> objectResultData;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            try {
                                if (param.thisObject != null) {
                                    if (param.args != null && param.args.length > 5 && param.args[4] != null) {
                                        if (TextUtils.equals("0", param.args[2].toString())) {
                                            Object b = param.args[4];
                                            Object gVo = XposedHelpers.getObjectField(b, "gVo");
                                            Object gVs = XposedHelpers.getObjectField(gVo, "gVs");
                                            Object url = XposedHelpers.getObjectField(gVs, "url");
                                            LogUtils.log("hook wechat 空二维码回调：" + url.toString());
                                            objectResultData = HandleResultData.handleResultDataSuccess(new EmptyQrCodeModel(url.toString()));
                                        } else {
                                            objectResultData = HandleResultData.handleResultDataFailed(param.args[3].toString());
                                        }
                                    } else {
                                        objectResultData = HandleResultData.handleResultDataFailed("empty qrcode param == null");
                                    }
                                } else {
                                    objectResultData = HandleResultData.handleResultDataFailed("empty qrcode param.thisObject == null");
                                }
                            } catch (Throwable e) {
                                objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
                                LogUtils.log("emptyQrCodeListener hook error");
                                LogUtils.logError(e);
                            } finally {
                                if (param.thisObject.hashCode() == mListenerMaps.get(HookConstant.EMPTY_QR_CODE)) {
                                    WechatSendData.sendGetEmptyQrCode(objectResultData);
                                }
                            }
                        }
                    }
            );
        } catch (Throwable e) {
            LogUtils.log("emptyQrCodeListener error");
            LogUtils.logError(e);
        }
    }

    public static void getQrCode(String money, String desc) {
        try {
            Object sModel = HookTargetMethod.getQrCode(money, desc, mClassLoader);
            mListenerMaps.put(HookConstant.QR_CODE, sModel.hashCode());
        } catch (Throwable e) {
            LogUtils.log("getQrCode error");
            LogUtils.logError(e);
            ResultData<QrCodeModel> objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
            WechatSendData.sendGetQrCode(objectResultData);
        }
    }

    public static void qrCodeListener() {
        try {
            Class<?> s = XposedHelpers.findClass("com.tencent.mm.plugin.collect.model.s", mClassLoader);
            XposedHelpers.findAndHookMethod(s, "onGYNetEnd", int.class, String.class, JSONObject.class, new XC_MethodHook() {
                ResultData<QrCodeModel> objectResultData;

                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    try {
                        if (param.thisObject != null) {
                            if (param.args != null && param.args.length > 2 && param.args[2] != null) {
                                if (TextUtils.equals("0", param.args[0].toString())) {
                                    JSONObject jsonObject = (JSONObject) param.args[2];
                                    LogUtils.log("hook wechat 二维码回调：" + jsonObject.optString("pay_url"));
                                    objectResultData = HandleResultData.handleResultDataSuccess(new QrCodeModel(jsonObject.optString("pay_url")));
                                } else {
                                    objectResultData = HandleResultData.handleResultDataFailed(param.args[1].toString());
                                }
                            } else {
                                objectResultData = HandleResultData.handleResultDataFailed("qrcode param == null");
                            }
                        } else {
                            objectResultData = HandleResultData.handleResultDataFailed("qrcode param.thisObject == null");
                        }
                    } catch (Throwable e) {
                        objectResultData = HandleResultData.handleResultDataFailed(e.getMessage());
                        LogUtils.log("qrCodeListener hook error");
                        LogUtils.logError(e);
                    } finally {
                        if (param.thisObject.hashCode() == mListenerMaps.get(HookConstant.QR_CODE)) {
                            WechatSendData.sendGetQrCode(objectResultData);
                        }
                    }
                }
            });
        } catch (Throwable e) {
            LogUtils.log("qrCodeListener error");
            LogUtils.logError(e);
        }
    }

    public static void setCollectMoneyListener() {
        try {
            XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", mClassLoader, "insert", String.class, String.class, ContentValues.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            try {
                                ContentValues contentValues = (ContentValues) param.args[2];
                                String tableName = (String) param.args[0];
                                if (TextUtils.equals("message", tableName) && param.args[2] != null && contentValues.getAsInteger("type") != null && contentValues.getAsInteger("type") == 318767153) {
                                    LogUtils.log(Utils.getObjectAllFiled(contentValues, null));
                                    JSONJava.JSONObject jsonObject = XML.toJSONObject(contentValues.getAsString("content"));
                                    LogUtils.log("收款content ：" + jsonObject.toString());
                                    if (jsonObject != null
                                            && jsonObject.getJSONObject("msg") != null
                                            && jsonObject.getJSONObject("msg").getJSONObject("appmsg") != null
                                            && jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader") != null
                                            && jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_header") != null) {
                                        Long time = jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_header").getLong("pub_time");
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String sdTime = sdf.format(new Date(time * 1000));
                                        CollectBillModel collectBillModel = new CollectBillModel(sdTime,
                                                jsonObject.getJSONObject("msg").getJSONObject("appmsg").getString("title"),
                                                jsonObject.getJSONObject("msg").getJSONObject("appmsg").getString("des"),
                                                time);
                                        if (jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("lines").getJSONArray("line") != null) {
                                            JSONArray jsonArray = jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("lines").getJSONArray("line");
                                            if (jsonArray.length() != 0) {
                                                List<CollectBillModel.Line> lines = new ArrayList<>();
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONJava.JSONObject jsb = jsonArray.getJSONObject(i);
                                                    lines.add(new CollectBillModel.Line(jsb.getJSONObject("key").getString("word"), jsb.getJSONObject("value").getString("word")));
                                                }
                                                collectBillModel.setLine(lines);
                                            }
                                        }
                                        ResultData<CollectBillModel> resultData = HandleResultData.handleResultDataSuccess(collectBillModel);
                                        WechatSendData.sendCollectInfo(resultData);
                                    }
                                }
                            } catch (Throwable e) {
                                LogUtils.log("setCollectMoneyListener Method error");
                                LogUtils.logError(e);
                            }
                        }
                    });
        } catch (Throwable e) {
            LogUtils.log("setCollectMoneyListener error");
            LogUtils.logError(e);
        }
    }

    public static void getCollectBill() {
        ResultData<List<CollectBillModel>> resultData = null;
        try {
            resultData = HandleResultData.handleResultDataSuccess(HookTargetMethod.getCollectBill(mClassLoader));
        } catch (Throwable e) {
            resultData = HandleResultData.handleResultDataFailed(e.getMessage());
            LogUtils.log("getCollectBill error");
            LogUtils.logError(e);
        } finally {
            WechatSendData.sendCollectBill(resultData);
        }
    }
}
