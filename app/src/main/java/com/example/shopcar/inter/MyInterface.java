package com.example.shopcar.inter;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyInterface {

    @GET("product/getCarts")
    Observable<ResponseBody> getShop(@Query("uid") int num);
}
