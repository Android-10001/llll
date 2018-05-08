package com.example.myapplication.utils;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author:Created by jiangkerun on 2018/5/8.
 */
public class OkHttpUtils {
    private static volatile OkHttpUtils instance;
    private OkHttpClient okHttpClient;
    private OkHttpUtils(){
        //1.创建OKhttpClient对象
         okHttpClient = new OkHttpClient();
    }
    public static OkHttpUtils getInstance() {
        if (instance==null){
            synchronized (OkHttpUtils.class){
                if (null==instance){
                    instance=new OkHttpUtils();
                }
            }
        }
        return instance;
    }
   public  void doGet(String url,Callback callback){
       //2.创建一个Request
       Request request = new Request.Builder()
               .url(url)
               .build();
       //3.创建一个call对象
       Call call = okHttpClient.newCall(request);
       //4.将请求添加到调度中
       call.enqueue(callback);
   }
   public  void doPost(String url, Map<String,String> params,Callback callback){
        //可以加入网络判断

       //判断参数
       if (params==null||params.size()==0){
           //运行时异常
           throw  new RuntimeException("params is null!");
       }
       //创建Request,post请求以表单的方式呈现的
       FormBody.Builder builder = new FormBody.Builder();
       //遍历
       for (Map.Entry<String,String> entry:params.entrySet()){
           builder.add(entry.getKey(),entry.getValue());
       }
       FormBody formBody = builder.build();
       Request request = new Request.Builder()
               .url(url)
               .post(formBody)
               .build();
       okHttpClient.newCall(request).enqueue(callback);
   }
}
