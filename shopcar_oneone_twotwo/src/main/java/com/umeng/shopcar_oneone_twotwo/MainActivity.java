package com.umeng.shopcar_oneone_twotwo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.umeng.shopcar_oneone_twotwo.adapter.MyAdapter;
import com.umeng.shopcar_oneone_twotwo.bean.ShopBean;
import com.umeng.shopcar_oneone_twotwo.presenter.ShopPresenter;
import com.umeng.shopcar_oneone_twotwo.view.ShopView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ShopView,View.OnClickListener {

    private ExpandableListView elShow;
    private CheckBox cbAll;
    private TextView tvAllprice;
    private MyAdapter myAdapter;
    private List<ShopBean.DataBean> sellerData;
    private Button btnAddNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化adapter和list
        initData();
        //初始化p层
        initPresenter();


    }

    private void initPresenter() {
        ShopPresenter shopPresenter = new ShopPresenter();
        shopPresenter.attach(this);
        shopPresenter.getShopP();

    }

    private void initView() {
        elShow = findViewById(R.id.el_show);
        cbAll = findViewById(R.id.cb_all);
        tvAllprice = findViewById(R.id.tv_allprice);
        btnAddNum = findViewById(R.id.btn_allnum);
        cbAll.setOnClickListener(this);
    }


    private void initData() {
        sellerData = new ArrayList<>();
        myAdapter = new MyAdapter(sellerData, MainActivity.this);
        myAdapter.setOnCartListChangeListener(new MyAdapter.OnCartListChangeListener() {

            @Override
            public void SellerSelectedChange(int groupPosition) {
                //先得到 checkbox的状态
                boolean b = myAdapter.isCurrentSellerAllProductSelected(groupPosition);
                myAdapter.changeCurrentSellerAllProductSelected(groupPosition, !b);
                myAdapter.notifyDataSetChanged();
                refreshAllSelectedAndTotalPriceAndTotalNumber();

            }

            @Override
            public void changeCurrentProductSelected(int groupPosition, int childPosition) {
                myAdapter.changeCurrentProductSelected(groupPosition, childPosition);
                myAdapter.notifyDataSetChanged();
                refreshAllSelectedAndTotalPriceAndTotalNumber();
            }

            @Override
            public void ProductNumberChange(int groupPosition, int childPosition, int number) {
                myAdapter.changeCurrentProductNumber(groupPosition, childPosition, number);
                myAdapter.notifyDataSetChanged();
                refreshAllSelectedAndTotalPriceAndTotalNumber();
            }
        });

        elShow.setAdapter(myAdapter);
//
        for (int i = 0; i < sellerData.size(); i++) {
            elShow.expandGroup(i);

        }
    }





    @Override
    public void getShopV(ShopBean shopBean) {
        List<ShopBean.DataBean> data = shopBean.getData();
        if (data!=null){
            sellerData.clear();
            sellerData.addAll(data);
            myAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void failed(Exception e) {

    }








    private void  refreshAllSelectedAndTotalPriceAndTotalNumber(){

        boolean allProductsSelected = myAdapter.isAllProductsSelected();
        cbAll.setChecked(allProductsSelected);
        Double totalPrice = myAdapter.calculateTotalPrice();
        tvAllprice.setText("总价：￥"+totalPrice);
        int totalNumber = myAdapter.calculateTotalNumber();
        btnAddNum.setText("去结算("+totalNumber+")");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cb_all:
                boolean allProductsSelected = myAdapter.isAllProductsSelected();
                myAdapter.changeAllProductsSelected(!allProductsSelected);
                myAdapter.notifyDataSetChanged();
                //刷新底部的方法
                refreshAllSelectedAndTotalPriceAndTotalNumber();
                break;

        }
    }

}
