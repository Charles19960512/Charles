package com.startsmake.StreetDemo.strAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.startsmake.StreetDemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class RecyclerAdapter extends RecyclerView. Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Object>mData;

    public RecyclerAdapter(Context context, List<Object>datas){

        this.mContext=context;
        this.mData=datas;
        mInflater =LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = mInflater.inflate(R.layout.grid_hor_item,arg0,false);
        MyViewHolder holder=new MyViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int pos) {


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView tu;
    TextView t1,t2;

    public MyViewHolder(View arg0){
        super(arg0);
        tu=(ImageView)arg0.findViewById(R.id.strls_tu);

        t1= (TextView) arg0.findViewById(R.id.strls_t2);
        t2= (TextView) arg0.findViewById(R.id.strls_t3);
    }
}