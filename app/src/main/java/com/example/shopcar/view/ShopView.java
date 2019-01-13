package com.example.shopcar.view;

import com.example.shopcar.bean.ShopBean;

public interface ShopView {
    //初始化v m 层的接口
    void OnSuccessful(ShopBean shopBean);

    //失败的方法
    void onFailderI(String str);
}
