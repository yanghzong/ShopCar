package com.umeng.shopcar_oneone_twotwo;



import com.google.gson.Gson;
import com.umeng.shopcar_oneone_twotwo.inter.ICallBack;
import com.umeng.shopcar_oneone_twotwo.inter.MyInterFace;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManage {
    private static  volatile  RetrofitManage  instance;
    private final Retrofit.Builder mbuilder;
    private MyInterFace myInterFace;


    private RetrofitManage(){
        OkHttpClient client=new OkHttpClient
                .Builder()
                .build();

        //将client对象放进Retrofit中

        //创建Retrofit
        mbuilder = new Retrofit.Builder();
        //设置json转换器
        mbuilder.addConverterFactory(GsonConverterFactory.create());
        mbuilder.client(client);


    }

    //双重锁
    public  static  RetrofitManage  getInstance(){
        if (instance==null){
            synchronized (RetrofitManage.class){
                if (instance==null){
                    instance=new RetrofitManage();
                }
            }
        }
        return instance;
    }

    public  void getShop(String url){
        Retrofit retrofit = mbuilder.baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        myInterFace = retrofit.create(MyInterFace.class);
    }
    public  void  get(String  url, final ICallBack callBack, final Type type){
        getShop(url);

        Observable<ResponseBody> shop = myInterFace.getShop(90);
        shop.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String result = responseBody.string();
                            Gson gson=new Gson();
                            Object o = gson.fromJson(result, type);
                            if(o!=null){
                                callBack.onSuccess(o);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }
}
