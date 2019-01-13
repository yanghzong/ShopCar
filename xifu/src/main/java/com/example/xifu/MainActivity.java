package com.example.xifu;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.gavin.com.library.listener.OnGroupClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_shop)
    RecyclerView  rvShop;
    StickyAdapter mStickyAdapter;
   private List<City> dataList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);
        initView();
    }

    private void initView() {
        dataList=new ArrayList<>();
        //模拟数据
        dataList.addAll(CityUtils.getCityList());
        dataList.addAll(CityUtils.getCityList());

        //------------- StickyDecoration 使用部分  ----------------
        StickyDecoration decoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回调
                        if (position < dataList.size() && position > -1) {
                            //获取组名，用于判断是否是同一组
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }
                })
                //背景色
                .setGroupBackground(ContextCompat.getColor(this, R.color.color_group_background))
                //高度
                .setGroupHeight(getResources().getDimensionPixelOffset(R.dimen.dp_35))
                //分割线颜色
                .setDivideColor(ContextCompat.getColor(this, R.color.color_divide))
                //分割线高度 (默认没有分割线)
                .setDivideHeight(getResources().getDimensionPixelOffset(R.dimen.dp_2))
                //字体颜色 （默认黑色）
                .setGroupTextColor(Color.RED)
                //字体大小
                .setGroupTextSize(getResources().getDimensionPixelOffset(R.dimen.sp_15))
                // 边距   靠左时为左边距  靠右时为右边距
                .setTextSideMargin(getResources().getDimensionPixelOffset(R.dimen.dp_10))
                //点击事件，返回当前分组下的第一个item的position
                .setOnClickListener(new OnGroupClickListener() {
                    @Override
                    public void onClick(int position, int id) {
                        //Group点击事件
                        String content = "onGroupClick --> " + dataList.get(position).getProvince();
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        //------------- StickyDecoration 使用部分  ----------------

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvShop.setLayoutManager(manager);
        rvShop.addItemDecoration(decoration);
        mStickyAdapter = new StickyAdapter(this,dataList);
        rvShop.setAdapter(mStickyAdapter);
    }
}
