package com.startsmake.StreetDemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.startsmake.StreetDemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CharlesC on 2017/4/18.
 */
public class MessageFragment extends Fragment {

    Toolbar Mtoolbar;
    ActionBar MactionBar;
//    TextView centerTtitle;

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private List<String> mDatas2;
//    private List<Image> mDatas3;
    private HomeAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Mtoolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        centerTtitle = (TextView) getActivity().findViewById(R.id.title);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(Mtoolbar);
        Mtoolbar.setTitle(getString(R.string.myinfo));
        Mtoolbar.inflateMenu(R.menu.menu_list);
//        MactionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        MactionBar.setDisplayHomeAsUpEnabled(false);//设置返回箭头显示
//        MactionBar.setTitle("我的消息（21）");//父标题
//        MactionBar.setSubtitle("子标题");//子标题
//        centerTtitle.setText("联系人");//自定义标题

        initData();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_list,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    //处理menu事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            点击返回箭头结束activity
//            case android.R.id.home:
//                finish();
//                return true;
//            case android.R.id.home:
//                showToast("will be power off!!!");
//                return true;
//            case R.id.action_search:
//                showToast("搜索");
//                return true;
            case R.id.action_item1:
                showToast("待定");
                return true;
            case R.id.action_item2:
                showToast("待定");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //引入menu布局

    private void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("很酷不说话");
        mDatas.add("我帅我先来");
        mDatas.add("我丑我不来");
        mDatas.add("我蠢呆后面");
        mDatas.add("楼下是智障");
        mDatas.add("楼上说反话");
        mDatas.add("我才是智障");

        mDatas2 = new ArrayList<String>();
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");
        mDatas2.add("17/03/20");

//        mDatas3 = new ArrayList<Image>();
//        mDatas3.add();
    }

    private class HomeAdapter extends RecyclerView.Adapter<MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.recycleview_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
//            图片暂时留空
//            holder.iv.setImageDrawable(mImg.get(position));
            holder.tv.setText(mDatas.get(position));
            holder.tv2.setText(mDatas2.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView iv;
        TextView tv;
        TextView tv2;

        public MyViewHolder(View view)
        {
            super(view);
            iv = (ImageView) view.findViewById(R.id.img);
            tv = (TextView) view.findViewById(R.id.id_name);
            tv2 = (TextView) view.findViewById(R.id.date);
        }
    }
}
