package com.example.shopcar.Utils;

import android.util.Log;

import com.example.shopcar.inter.ICallBack;
import com.example.shopcar.inter.MyInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtils {
    //使用单例模式
    private static volatile RetrofitUtils instance;
    private final Retrofit.Builder mBuilder;
    private MyInterface myInterface;

    //构造方法
    public RetrofitUtils() {
        //日志拦截器
        //1 设置日志拦截器级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //2 创建一个日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印信息
                Log.i("http信息打印:", "log: " + message);
            }
        });
        //3 为日志拦截器设置日志级别
        httpLoggingInterceptor.setLevel(level);
        //4 创建HttpClient对象  添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        //5 将HttpClient对象加入到Retrofit中


        //创建Retrifit对象
        mBuilder = new Retrofit.Builder();
        //设置 Json 转换器
        mBuilder.addConverterFactory(GsonConverterFactory.create());
        mBuilder.client(okHttpClient);
    }
    //双重锁机制
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(instance==null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    //
    private void getShop(String url) {
        Retrofit retrofit = mBuilder.baseUrl(url)//添加请求网址,以/结尾
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //RxJava 适配器
                .build();
        myInterface = retrofit.create(MyInterface.class);
    }


    public void getShopU(String url, final ICallBack iCallBack, final Type type ){

        //Retrofit+Rxjava 设置方法
        getShop(url);

        //通过接口对象，调用抽象方法
        Observable<ResponseBody> weather = myInterface.getShop(90);
        //通过对象，进行设置
        weather.subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String str = "网络请求访问失败";
                        iCallBack.onFailed(str);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            //注意：这儿不用response.body了，它已经是responsebody
                            String result = responseBody.string();
                            Gson gson = new Gson();
                            Object o = gson.fromJson(result, type);

                            if(o!=null){
                                iCallBack.onSuccess(o);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
