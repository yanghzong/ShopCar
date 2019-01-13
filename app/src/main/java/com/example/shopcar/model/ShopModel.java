package com.example.shopcar.model;

import com.example.shopcar.Utils.RetrofitUtils;
import com.example.shopcar.inter.ICallBack;

import java.lang.reflect.Type;

public class ShopModel {
    public   void  getShop(String url, ICallBack callBack, Type type){
        RetrofitUtils.getInstance().getShopU(url,callBack,type);
    }
}
