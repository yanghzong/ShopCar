package com.umeng.shopcar_oneone_twotwo.view;

import android.view.View;

import com.umeng.shopcar_oneone_twotwo.bean.ShopBean;

public interface ShopView {
    void getShopV(ShopBean shopBean);
    void failed(Exception e);
}
