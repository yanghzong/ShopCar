package com.example.shopcar.presenter;

import com.example.shopcar.bean.ShopBean;
import com.example.shopcar.inter.Apis;
import com.example.shopcar.inter.ICallBack;
import com.example.shopcar.model.ShopModel;
import com.example.shopcar.view.ShopView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ShopPresenter {
    private ShopView sv;
    private ShopModel mModel;

    //关联方法
    public void attach(ShopView sv){
        this.sv = sv;
        mModel = new ShopModel();
    }

    //定义一个北京天气请求方法
    public void getShopP(){

        //泛型初始化
        Type type = new TypeToken<ShopBean>(){}.getType();

        //通过M层对象调用网络请求方法
        mModel.getShop(Apis.URL_ShopCar, new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                //强zhuan
                 ShopBean shopBean= (ShopBean) o;
                 if (shopBean!=null){
                     sv.OnSuccessful(shopBean);
                 }

            }

            @Override
            public void onFailed(String e) {
                sv.onFailderI(e);
            }


        },type);


    }


    //取消关联
    public void datach(){
        if(sv!=null){
            sv = null;
        }
    }

}
