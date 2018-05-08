package com.example.myapplication.model;


import android.os.Handler;
import android.os.Looper;

import com.example.myapplication.bean.LoginBean;
import com.example.myapplication.inner.ILoginModel;
import com.example.myapplication.inner.OnNetListener;
import com.example.myapplication.utils.OkHttpUtils;
import com.example.myapplication.utils.Urls;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by jiangkerun on 2018/5/8.
 */
public class LoginModel implements ILoginModel {

    private Handler handler=new Handler(Looper.getMainLooper());

    @Override
    public void login(String mobile, String password, final OnNetListener<LoginBean> onNetListener) {
        Map<String,String> params= new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",password);
        OkHttpUtils.getInstance().doPost(Urls.loginUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final LoginBean loginBean = new Gson().fromJson(string, LoginBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(loginBean);
                    }
                });
            }
        });

    }
}
