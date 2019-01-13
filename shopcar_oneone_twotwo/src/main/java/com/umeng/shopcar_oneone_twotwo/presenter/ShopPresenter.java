package com.umeng.shopcar_oneone_twotwo.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.shopcar_oneone_twotwo.bean.ShopBean;
import com.umeng.shopcar_oneone_twotwo.inter.Apis;
import com.umeng.shopcar_oneone_twotwo.inter.ICallBack;
import com.umeng.shopcar_oneone_twotwo.model.ShopModel;
import com.umeng.shopcar_oneone_twotwo.view.ShopView;

import java.lang.reflect.Type;

public class ShopPresenter {
    private ShopView shopView;
    private ShopModel shopModel;


    public  void attach(ShopView shopView){
        this.shopView=shopView;
        shopModel=new ShopModel();

    }
    public  void getShopP(){
        Type type=new TypeToken<ShopBean>(){}.getType();
        shopModel.getShopm(Apis.URL_ShopCar, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                ShopBean shopBean= (ShopBean) obj;
                if (shopBean!=null){
                    shopView.getShopV(shopBean);
                }
            }

            @Override
            public void onFailed(Exception e) {
                 shopView.failed(e);
            }
        },type);
    }

    public void datach(){
        if(shopView!=null){
            shopView = null;
        }
    }
}
