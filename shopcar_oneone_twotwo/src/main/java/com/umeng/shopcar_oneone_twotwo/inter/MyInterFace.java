package com.umeng.shopcar_oneone_twotwo.inter;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyInterFace {
    @GET("product/getCarts?uid=")
    rx.Observable<ResponseBody> getShop(@Query("") int num);
}
