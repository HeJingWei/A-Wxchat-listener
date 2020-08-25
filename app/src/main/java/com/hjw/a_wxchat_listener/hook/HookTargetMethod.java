package com.hjw.a_wxchat_listener.hook;

import android.annotation.SuppressLint;
import android.database.Cursor;

import com.hjw.a_wxchat_listener.service.model.CollectBillModel;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import JSONJava.JSONArray;
import JSONJava.JSONObject;
import JSONJava.XML;
import de.robv.android.xposed.XposedHelpers;

public class HookTargetMethod {

    public static boolean getLoginState(ClassLoader classLoader) {
        Class<?> ajClass = XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.aj", classLoader);
        Object eCn = XposedHelpers.callStaticMethod(ajClass, "eCn");
        Object isLogin = XposedHelpers.callMethod(eCn, "getBoolean", "isLogin", false);
        return (boolean) isLogin;
    }

    public static String getUserId(ClassLoader classLoader) {
        Class<?> userClass = XposedHelpers.findClass("com.tencent.mm.model.u", classLoader);
        Object userName = XposedHelpers.callStaticMethod(userClass, "arp");
        return userName.toString();
    }

    public static Object getEmptyQrCode(ClassLoader classLoader) {
        Class<?> gClass = XposedHelpers.findClass("com.tencent.mm.kernel.g", classLoader);
        Object q = XposedHelpers.callStaticMethod(gClass, "afF");
        Object o = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.plugin.collect.model.l", classLoader));
        XposedHelpers.callMethod(q, "a", o, 0);
        return o;
    }

    public static Object getQrCode(String money, String desc, ClassLoader classLoader) {
        Class<?> s = XposedHelpers.findClass("com.tencent.mm.plugin.collect.model.s", classLoader);
        Object doubleMoney = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bt", classLoader), "getDouble", money, 0.0d);
        Class<?> gClass = XposedHelpers.findClass("com.tencent.mm.kernel.g", classLoader);
        Object q = XposedHelpers.callStaticMethod(gClass, "afF");
        Object sModel = XposedHelpers.newInstance(s, doubleMoney, "1", desc);
        XposedHelpers.callMethod(q, "a", sModel, 0);
        return sModel;
    }

    @SuppressLint("SimpleDateFormat")
    public static List<CollectBillModel> getCollectBill(ClassLoader classLoader) {
        Class<?> cClass = XposedHelpers.findClass("com.tencent.mm.model.c", classLoader);
        Object bl = XposedHelpers.callStaticMethod(cClass, "aqw");
        Cursor cursor = null;
        cursor = (Cursor) XposedHelpers.callMethod(bl, "n", "gh_3dfda90e39d6",100, 0);//最近100条
        List<CollectBillModel> collectBillModels = new ArrayList<>();
        while (cursor.moveToNext()) {
//                LogUtils.log("rawQueryWithFactory column 8 :" + cursor.getString(8)); //xml解析
            JSONObject jsonObject = XML.toJSONObject(cursor.getString(8));


            if (jsonObject != null
                    && jsonObject.getJSONObject("msg") != null
                    && jsonObject.getJSONObject("msg").getJSONObject("appmsg") != null
                    && jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader") != null
                    && jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_header") != null
                    && jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_header").getString("title").startsWith("收款到账通知")) {
                Long time = jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_header").getLong("pub_time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sdTime = sdf.format(new Date(time * 1000));
                CollectBillModel collectBillModel = new CollectBillModel(sdTime,
                        jsonObject.getJSONObject("msg").getJSONObject("appmsg").getString("title"),
                        jsonObject.getJSONObject("msg").getJSONObject("appmsg").getString("des"),
                        time);
                if (jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("lines").getJSONArray("line") != null) {
                    JSONArray jsonArray = jsonObject.getJSONObject("msg").getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("lines").getJSONArray("line");
//                    LogUtils.log("jsonArray  " + jsonArray.toString());
                    if (jsonArray.length() != 0) {
                        List<CollectBillModel.Line> lines = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsb = jsonArray.getJSONObject(i);
                            lines.add(new CollectBillModel.Line(jsb.getJSONObject("key").getString("word"), jsb.getJSONObject("value").getString("word")));
                        }
                        collectBillModel.setLine(lines);
                    }
                }

                collectBillModels.add(0, collectBillModel);

            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return collectBillModels;
    }
}
