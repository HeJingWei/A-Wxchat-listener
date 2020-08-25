package com.hjw.a_wxchat_listener.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjw.a_wxchat_listener.R;
import com.hjw.a_wxchat_listener.adapter.HomeAdapter;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.BusManager;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.service.AppSendData;
import com.hjw.a_wxchat_listener.service.ConnectService;
import com.hjw.a_wxchat_listener.service.ConnectServiceMessage;
import com.hjw.a_wxchat_listener.service.model.ResultData;
import com.hjw.a_wxchat_listener.service.model.UserInfoModel;
import com.hjw.a_wxchat_listener.tcp.TcpClient;
import com.hjw.a_wxchat_listener.utils.LogUtils;
import com.hjw.a_wxchat_listener.utils.Utils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;

    private AppCompatCheckBox mWxLoginCheck;
    private AppCompatCheckBox mServiceCheck;
    private TextView mUserName;

    private Button goService;
    public static boolean isOnService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setKeepScreenOn(true);//提示开发者选项 -> 不锁定屏幕开启
        EventModel.postLog("请在开发者选项 -> 开启不锁定屏幕，以防止APP停止工作");
        BusManager.getInstance().register(this);
        goService = findViewById(R.id.go_service);
        goService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnService) {
                    if (checkTargetApp()) {
                        checkWxIsRunning();
                    }
                } else {
                    if (ConnectServiceMessage.mTcpClient != null) {
                        ConnectServiceMessage.mTcpClient.disConnectSocket();
                        mUserName.setText("微信ID：");
                        mWxLoginCheck.setChecked(false);
                        goService.setText("上线");
                    }
                }
            }
        });
        mServiceCheck = findViewById(R.id.service_is_login);
        mWxLoginCheck = findViewById(R.id.wechat_is_login);
        mUserName = findViewById(R.id.wx_username);
        mRecyclerView = findViewById(R.id.recyclerview);
        List<String> emptyStrs = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(emptyStrs);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHomeAdapter);
        //test
        findViewById(R.id.empty_qrcode).setOnClickListener(this);
        findViewById(R.id.collect_qrcode).setOnClickListener(this);
        findViewById(R.id.collect_bill).setOnClickListener(this);
    }


    private boolean checkTargetApp() {
        if (!Utils.isInstallApp(getApplicationContext(), Constant.target_package_name)) {
            LogUtils.log("没有安装微信");
            return false;
        }
        if (!Utils.checkTargetVersion(getApplicationContext())) {
            LogUtils.log("微信版本错误");
            return false;
        }
        return true;
    }

    private void checkWxIsRunning() {
        if (!ConnectService.getInstance().isConnected()) {
            EventModel.postLog("准备启动微信");
            LogUtils.log("启动微信,建立连接");
            Utils.openApp(this, Constant.target_package_name);
        } else {
            AppSendData.sendGetLoginState();
        }
    }

    @Subscribe
    public void receiveBusEvent(final EventModel eventModel) {
        runOnUiThread(() -> {
            ResultData resultData = eventModel.getResultData();
            switch (eventModel.getTag()) {
                case EventModel.LOG_LIST:
                    mHomeAdapter.refreshItem(eventModel.getLogStr());
                    break;
                case EventModel.WX_LOGIN:
                    boolean isLogin = (boolean) resultData.getData();
                    mWxLoginCheck.setChecked(isLogin);
                    if (!isLogin) {
                        mUserName.setText("微信ID：");
                    }
                    break;
                case EventModel.TCP_LOGIN:
                    isOnService = (boolean) resultData.getData();
                    mServiceCheck.setChecked(isOnService);
                    if ((boolean) resultData.getData()) {
                        goService.setText("下线");
                    }
                    break;
                case EventModel.WX_USERNAME:
                    UserInfoModel userInfoModel = (UserInfoModel) resultData.getData();
                    mUserName.setText("微信ID：" + userInfoModel.getUserName());
                    break;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectService.getInstance().setMessenger(null);
        BusManager.getInstance().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.empty_qrcode:
                AppSendData.sendGetEmptyQrCode();
                break;
            case R.id.collect_qrcode:
                AppSendData.sendGetQrCode("1.0", "这是收款备注");
                break;
            case R.id.collect_bill:
                AppSendData.sendGetCollectBill();
                break;

        }
    }

}
