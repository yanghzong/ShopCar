package com.example.deletelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mrecycleView;
    private List<String> list;
    private MyRecycleViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecycleView=(RecyclerView)findViewById(R.id.mRecycleView);
        list=new ArrayList<>();
        for(int i=0;i<9;i++){
            list.add("条目"+i);
        }
        adapter=new MyRecycleViewAdapter(MainActivity.this,list);
        mrecycleView.setLayoutManager(new LinearLayoutManager(this));
        mrecycleView.setAdapter(adapter);
    }

}
