package com.startsmake.StreetDemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.startsmake.StreetDemo.MyGridView;
import com.startsmake.StreetDemo.MyOnPageChangeListener;
import com.startsmake.StreetDemo.R;
import com.startsmake.StreetDemo.strAdapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * Created by CharlesC on 2017/4/22.
 */

public class StreetFragment extends Fragment {
    private MyGridView gridView1;
    private SimpleAdapter gdv1_adapter;
    private List<Map<String, Object>> gridList;
    private String[] strNam = {"iPhone互换交流", "古法摄影器材租赁", "Leica相机出租", "复古胶片机置换", "复古小旁轴", "瑶湖闲置互换","7"};
    private String[] strNum = {"3498已发布", "12已发布", "134已发布", "632已发布", "221已发布", "821已发布","7"};
    private int[] icon = {R.drawable.street_g1, R.drawable.street_g2, R.drawable.street_g3, R.drawable.street_g4, R.drawable.street_g5,
            R.drawable.street_g6, R.mipmap.ic_launcher};
    /**
     * 第一个gridview用到
     */
    private ListView listView1;
    private SimpleAdapter lsv_adapter;
    private List<Map<String, Object>> List;
    private String[] t0 = {"玩相机不烧钱","租租皮"};

    /**
     * 第一个listview用到
     */
    private String[] t1 = {"123", "456"};
    private String[] t2 = {"123", "456"};
    private int[] tu = {R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private RecyclerView recyclerView,recyclerView1;
    private RecyclerAdapter recycleAdapter,recycleAdapter1;
    private List<Object> reData,reData1;
    //private TextView[] tv1=new TextView[selNam.length];
    //private TextView[] tv2=new TextView[selNum.length];
    //private ImageView[] iv=new ImageView[icon.length];
    private ViewPager mViewPaper,mViewPaper1;// mViewPaper用于滑动gridview,mViewPaper1用于广告轮播
    private List<View> mPageList;

    private LayoutInflater inflater;

    private int pageCount = 2 ;
    private int pageSize = 6;//页面元素数量
    private Handler handler=new Handler();

    private List<ImageView> images;
    private List<View> dots,dot;// dots用于滑动gridview,dot用于广告轮播
    private int currentItem,currentItem1;
    private int oldPosition =0,oldPosition1 = 0;
    private MyOnPageChangeListener mListener1,mListener2 = null;
    //存放轮播图片的id
    private int[] imageIds = new int[]{
            R.mipmap.comui_tab_city,
            R.mipmap.comui_tab_find,
            R.mipmap.comui_tab_home,

    };
    //存放图片的标题


    private   com.startsmake.StreetDemo.strAdapter.ViewPagerAdapter1 adapter;
    private ScheduledExecutorService scheduledExecutorService;


    private int i=0;
    private int j;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_street, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewPaper1=(ViewPager)getActivity().findViewById(R.id.strvp);
        images = new ArrayList<ImageView>();
        for(int x = 0; x < imageIds.length; x++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[x]);
            images.add(imageView);
        }

        dot = new ArrayList<View>();
        dot.add(getActivity().findViewById(R.id.strp_dot0));
        dot.add(getActivity().findViewById(R.id.strp_dot1));
        dot.add(getActivity().findViewById(R.id.strp_dot2));

        adapter = new com.startsmake.StreetDemo.strAdapter.ViewPagerAdapter1(images);
        mViewPaper1.setAdapter(adapter);


        mListener2 = new MyOnPageChangeListener(dot,currentItem1,oldPosition1);
        mViewPaper1.setOnPageChangeListener(mListener2);
        /*
        轮播部分
         */
        //recyclerView= (RecyclerView) getActivity().findViewById(R.id.recycler);

        listView1 = (ListView) getActivity().findViewById(R.id.ls_street1);

        Log.i("ls","1");

        List = new ArrayList<Map<String, Object>>();
        lsv_adapter = new SimpleAdapter(getActivity(), getData2(), R.layout.street2_ls_item,
            new String[]{"t0"}, new int[]{R.id.strls_t1});
        listView1.setAdapter(lsv_adapter);
      /*  recyclerView1= (RecyclerView) getActivity().findViewById(R.id.ls_street1);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(layoutManager1);
        reData1 = new ArrayList<Object>();
        for(int d=0;d<=t0.length;d++){
            reData1.add(t0[d]);
            reData1.add(recyclerView);
        }
        recycleAdapter1 = new RecyclerAdapter(getActivity(),reData1);
        recyclerView1.setAdapter(recycleAdapter1);*/

/*
listview部分
 */

      /*  StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        reData=new ArrayList<Object>();
        for(int d=0;d<=t1.length;d++){
            reData.add(tu[d]);
            reData.add(t1[d]);
            reData.add(t2[d]);
        }
        recycleAdapter = new RecyclerAdapter(getActivity(),reData);
        recyclerView.setAdapter(recycleAdapter);*/

/*
recyclerView部分
 */

        //gridview显示的小点
        dots = new ArrayList<View>();
        dots.add(getActivity().findViewById(R.id.strg_dot0));
        dots.add(getActivity().findViewById(R.id.strg_dot1));


        //gridView1 = (MyGridView) getActivity().findViewById(R.id.gdv_street1);
        //gridList = new ArrayList<Map<String, Object>>();
        //gdv1_adapter = new SimpleAdapter(getActivity(), getData1(), R.layout.street1_gdv, new String[]{"strIcon","strNam","strNum"}, new int[]{R.id.strIcon,R.id.strNam,R.id.strNum});
        //gridView1.setAdapter(gdv1_adapter);
        //gridview的适配

        mViewPaper = (ViewPager)getActivity(). findViewById(R.id.strPage);
        mListener1 = new MyOnPageChangeListener(dots,currentItem,oldPosition);
        mViewPaper.setOnPageChangeListener(mListener1);

        //初始化数据源
        //initDatas();
        inflater = LayoutInflater.from(getActivity());
        //总的页数=总数/每页数量，并取整
       // pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPageList = new ArrayList<View>();

        for ( j=0; j < pageCount; j++) {
            // 每个页面都是inflate出一个新实例
            gridList=new ArrayList<Map<String, Object>>();
            MyGridView gridView = (MyGridView) inflater.inflate(R.layout.strgrid,mViewPaper,false);
            SimpleAdapter gdv_adapter = new SimpleAdapter(getActivity(), getData1(), R.layout.street1_gdv, new String[]{"strIcon","strNam","strNum"}, new int[]{R.id.strIcon, R.id.strNam, R.id.strNum});
            Log.i("i1",i+"");
            gridView.setAdapter(gdv_adapter);
            mPageList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position;
                    Log.i("tag","4");
                    Toast.makeText(getActivity(), strNam[pos], Toast.LENGTH_SHORT).show();
                }
            });
            Log.i("i2",i+"");
        }
        //设置适配器

        mViewPaper.setAdapter(new com.startsmake.StreetDemo.strAdapter.ViewPagerAdapter(mPageList));
        Log.i("最后","0");

    }

    /**
     * 初始化数据源
     */
 /*   private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            mDatas.add(new Model(titles[i], imageId));
        }
    }*/
    private List<Map<String, Object>> getData1() {

        for (; i < strNam.length; i++) {

            //if((i!=0)&&(i%pageSize==0)) break;
            if(i==pageSize&&j==0) break;
            //tv1[i].setText(selNam[i]);
           // Log.i("1",i+"");

            // tv2[i].setText(selNum[i]);

            // iv[i].setImageResource(icon[i]);



            Map<String, Object> map = new HashMap<String, Object>();
            map.put("strIcon", icon[i]);
            map.put("strNam", strNam[i]);
            map.put("strNum", strNum[i]);



            Log.i("i3",i+"");
            gridList.add(map);
        }
        return gridList;
    }
    private List<Map<String, Object>> getData2() {

        for (int n=0; n < t0.length; n++) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("t0", t0[n]);
           // map.put("r0",recyclerView);

            List.add(map);
        }
        return List;
    }


    /**
     * 自定义Adapter协助轮播
     *
     */
    /*public class ViewPagerAdapter1 extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }
    }*/
    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                4,
                4,
                TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable{

        /*
         * 接收子线程传递过来的数据
         * */

        private Handler mHandler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                mViewPaper1.setCurrentItem(currentItem1);
            };
        };
        @Override
        public void run() {
            currentItem1 = (currentItem1 + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }


}
