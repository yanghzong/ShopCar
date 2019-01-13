package com.example.deletelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.List;

public class MyRecycleViewAdapter  extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.surface.setText(list.get(position));
        viewHolder.bottom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SwipeLayout swipeLayout;
        private Button bottom;
        private TextView surface;

        public MyViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe_layout);
            bottom = (Button) itemView.findViewById(R.id.bottom);
            surface = (TextView) itemView.findViewById(R.id.surface);
        }

    }
}
