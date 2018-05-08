package com.example.myapplication.inner;

/**
 * author:Created by jiangkerun on 2018/5/8.
 */
public interface OnNetListener<T> {
    //成功回调
   void onSuccess(T t);
   //失败回调
    void onFailure(Exception e);
}
