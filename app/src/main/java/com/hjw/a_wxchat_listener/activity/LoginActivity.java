package com.hjw.a_wxchat_listener.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.hjw.a_wxchat_listener.R;
import com.hjw.a_wxchat_listener.constant.Constant;
import com.hjw.a_wxchat_listener.manager.ContextManager;
import com.hjw.a_wxchat_listener.model.EventModel;
import com.hjw.a_wxchat_listener.model.LoginModel;
import com.hjw.a_wxchat_listener.net.RequestManager;
import com.hjw.a_wxchat_listener.net.callback.OnLoadDataListener;
import com.hjw.a_wxchat_listener.utils.SpUtils;
import com.hjw.a_wxchat_listener.utils.Utils;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton mLoginBtn;
    private AppCompatEditText mLoginEt;
    private AppCompatEditText mPwdEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        checkIsInVx();
        mLoginBtn = findViewById(R.id.login_btn);
        mLoginEt = findViewById(R.id.user_name);
        mPwdEt = findViewById(R.id.password);
        mLoginBtn.setOnClickListener(this);
        String userName = SpUtils.getLoginUser();
        String password = SpUtils.getLoginPwd();
        mLoginEt.setText(userName);
        mPwdEt.setText(password);
    }

    private void checkIsInVx() {
        if (!Utils.isInVXApp(getApplicationContext())) {
            new AlertDialog.Builder(this)
                    .setMessage("请在VirtualXposed中运行")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                loginApp();
                break;
        }
    }

    private void loginApp() {
        if (TextUtils.isEmpty(Objects.requireNonNull(mLoginEt.getText()).toString())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(mPwdEt.getText()).toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestManager.login(mLoginEt.getText().toString(), mPwdEt.getText().toString(), new OnLoadDataListener<LoginModel>() {
            @Override
            public void onSuccess(LoginModel loginResponseModel) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(String code, String errorMsg) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
