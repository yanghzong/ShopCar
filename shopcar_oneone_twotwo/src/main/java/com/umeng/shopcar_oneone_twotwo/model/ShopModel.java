package com.umeng.shopcar_oneone_twotwo.model;

import com.umeng.shopcar_oneone_twotwo.RetrofitManage;
import com.umeng.shopcar_oneone_twotwo.inter.ICallBack;

import java.lang.reflect.Type;

public class ShopModel {
    public  void  getShopm(String url, ICallBack callBack, Type  type){
        RetrofitManage.getInstance().get(url,callBack,type);
    }
}
