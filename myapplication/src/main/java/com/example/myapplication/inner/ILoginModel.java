package com.example.myapplication.inner;

import com.example.myapplication.bean.LoginBean;

/**
 * author:Created by jiangkerun on 2018/5/8.
 */
public interface ILoginModel {
    void login(String mobile, String password, OnNetListener<LoginBean> onNetListener);
}
