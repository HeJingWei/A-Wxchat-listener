package com.hjw.a_wxchat_listener.net.service;

import com.hjw.a_wxchat_listener.model.LoginModel;
import com.hjw.a_wxchat_listener.net.NetConstant;
import com.hjw.a_wxchat_listener.net.NetResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetApi {
    @POST(NetConstant.login)
    Observable<NetResponse<LoginModel>> login(@Body RequestBody body);

    @POST(NetConstant.newCollect)
    Observable<NetResponse<Object>> newCollect(@Body RequestBody body);
}
