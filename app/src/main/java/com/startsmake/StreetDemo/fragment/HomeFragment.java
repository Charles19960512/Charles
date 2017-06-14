package com.startsmake.StreetDemo.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.startsmake.StreetDemo.MyApplication;
import com.startsmake.StreetDemo.MyGridView;
import com.startsmake.StreetDemo.MyListView;
import com.startsmake.StreetDemo.R;
import com.startsmake.StreetDemo.activity.GoodsInfo;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by CharlesC on 2017/4/22.
 */

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE = 11;
    private ImageButton QRcodeScBtn;
    private SearchView searchView;
    private ScrollView scrollview;
    private LinearLayout linearLayout;
    private MyGridView gridView;
    private MyListView listView;
    private SimpleAdapter sim_adapter,list_adapter;
    private List<Map<String, Object>> gridList,homelist;
    private String[] selNam = {"江西师大租街", "南昌租街", "新闻科技", "南昌租车", "瑶湖租房"};
    private String[] selNum = {"1", "2", "3", "4", "5"};
    private int[] icon = {R.drawable.home_g1,R.drawable.home_g2,R.drawable.home_g3,R.drawable.home_g4,R.drawable.home_g5};

    //private TextView[] tv1=new TextView[selNam.length];
    //private TextView[] tv2=new TextView[selNum.length];
    //private ImageView[] iv=new ImageView[icon.length];
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.comui_tab_city,
            R.mipmap.comui_tab_find,
            R.mipmap.comui_tab_home,
            R.mipmap.comui_tab_message,

    };
    //存放图片的标题


    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;


    private int i;


    private Map<String, Object> lsmap = new HashMap<String, Object>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //searchView= (SearchView) view.findViewById(R.id.home_search);
        //searchView.setFocusable(false);
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i("tag","0");



        scrollview = (ScrollView) getActivity().findViewById(R.id.fragment_home);
        mViewPaper = (ViewPager)getActivity().findViewById(R.id.vp);
        QRcodeScBtn = (ImageButton)getActivity().findViewById(R.id.QRcodeScanBtn);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示的小点
        dots = new ArrayList<View>();
        dots.add(getActivity().findViewById(R.id.dot_0));
        dots.add(getActivity().findViewById(R.id.dot_1));
        dots.add(getActivity().findViewById(R.id.dot_2));
        dots.add(getActivity().findViewById(R.id.dot_3));




        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);


        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });



        /*final ActionBar actionBar =getActionBar();

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setCustomView(R.layout.menu_layout);*/

        gridView = (MyGridView) getActivity().findViewById(R.id.gdv_main1);
        gridList = new ArrayList<Map<String, Object>>();

        sim_adapter = new SimpleAdapter(getActivity(), getData(), R.layout.main1_gdv,
                new String[]{"selNam","selNum","selIcon"}, new int[]{R.id.selNam,R.id.selNum,R.id.selIcon});

      /*  for (i=0;i<selNum.length;i++){
            Log.i("开始设置一个按钮",i+"");
            bt[i].setOnClickListener(this);
            Log.i("设置点击事件之后",i+"");
        }*/

        gridView.setAdapter(sim_adapter);
        // gridView.setOnItemClickListener(this);

              /*
        ListView部分
        * */
        final MyApplication appState = ((MyApplication) getActivity().getApplication());
        homelist=appState.getlist();
        listView= (MyListView) getActivity().findViewById(R.id.ls_home);
        SharedPreferences share = getActivity().getSharedPreferences("publish", MODE_PRIVATE);
        boolean flag = share.getBoolean("first",true);//设置标识检测程序是否第一次运行

        if(!flag){
            initLSdata();
            SharedPreferences.Editor editor = share.edit();
            editor.putBoolean("first",true);
            editor.commit();

        }
        appState.setlist(homelist);
        list_adapter = new SimpleAdapter(getActivity(),homelist,R.layout.main2_lsv,
                new String[]{"lsi_userAvatar","lsi_userName","lsi_userInfo","lsi_price","lsi_image1",
                        "lsi_image2","lsi_image3","lsi_title","lsi_location","lsi_streetName"},
                new int[]{R.id.lsi_userAvatar,R.id.lsi_userName,R.id.lsi_userInfo,R.id.lsi_price,R.id.lsi_image1,
                        R.id.lsi_image2,R.id.lsi_image3,R.id.lsi_title,R.id.lsi_location,R.id.lsi_streetName});
        listView.setAdapter(list_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                //Map<String, Object> map = (HashMap<String, Object>) listView.getItemAtPosition(position);


                // TextView t1= (TextView) view.findViewById(R.id.lsi_title);
                Bundle bundle1=getActivity().getIntent().getExtras();

                //Intent intent1=getActivity().getIntent();
                String info=bundle1.getString("info");
                //Bitmap bitmap=intent1.getParcelableExtra("bitmap");

                ImageView img1= (ImageView) view.findViewById(R.id.lsi_image1);
               // img1.setImageBitmap(bitmap);

                TextView t2= (TextView) view.findViewById(R.id.lsi_price);
                String s2=t2.getText().toString();

                TextView t3= (TextView) view.findViewById(R.id.lsi_location);
                String s3=t3.getText().toString();

                TextView t4= (TextView) view.findViewById(R.id.lsi_userName);
                String s4=t4.getText().toString();

                TextView t5= (TextView) view.findViewById(R.id.lsi_userInfo);
                String s5=t5.getText().toString();

                Bundle bundle = new Bundle();

                bundle.putString("info",info);
                bundle.putString("price",s2);
                bundle.putString("location",s3);
                bundle.putString("userName",s4);
                bundle.putString("userInfo",s5);
                Intent intent = new Intent();
                //intent.putExtra("bitmap",bitmap);
                intent.putExtras(bundle);
                intent.setClass(getActivity(), GoodsInfo.class);
                startActivity(intent);
            }
        });
//二维码部分
        QRcodeScBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCamera(booleanisCameraUseable());
            }
        });
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    public void textCamera(boolean cU){
        if(cU == false){
            new AlertDialog.Builder(getActivity())
                    .setTitle("开启相机权限")
                    .setMessage("相机权限已被禁止，请在权限管理中开启。现在设置？")
                    .setPositiveButton("设置", listener)
                    .setNegativeButton("取消", listener)
                    .show();
        }
        else {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            Log.i("sb1","5");
            startActivityForResult(intent,REQUEST_CODE);
        }
    }

    public boolean booleanisCameraUseable() {
        boolean canUse =true;
        Camera mCamera =null;
        try{
            mCamera = Camera.open();
// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        }catch(Exception e) {
            canUse =false;
        }
        if(mCamera !=null) {
            mCamera.release();
        }
        return canUse;
    }


    private void initLSdata() {

        lsmap.put("lsi_userAvatar", R.mipmap.ic_launcher);
        lsmap.put("lsi_userName", "z44");
        lsmap.put("lsi_userInfo", "1天前来过");
        lsmap.put("lsi_price", 120);
        lsmap.put("lsi_image1", R.mipmap.ic_launcher);
        lsmap.put("lsi_image2", R.mipmap.ic_launcher);
        lsmap.put("lsi_image3", R.mipmap.ic_launcher);
        lsmap.put("lsi_title", "s");
        lsmap.put("lsi_location", "来自南昌");
        lsmap.put("lsi_streetName", "TCG");

        homelist.add(lsmap);

    }


 /*   @Override
    public boolean onTouch(View v, MotionEvent event) {
        linearLayout= (LinearLayout) getActivity().findViewById(R.id.homlinlayout);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                //index++;
                break;
            default:
                break;
        }
        if (event.getAction() == MotionEvent.ACTION_UP ) {
            //index = 0;
            int mLastY = scrollview.getScrollY();
            if (mLastY == (linearLayout.getHeight() - scrollview.getHeight())) {
                //TODO
                //滑动到底部，你要做的事
                list_adapter.notifyDataSetChanged();
            }
            if (mLastY!=scrollview.getScrollY()){ Toast.makeText(getActivity(), "没有更多", Toast.LENGTH_SHORT).show();}
        }
        return false;
    }*/

    /**
     * 自定义Adapter
     *
     */
    public class ViewPagerAdapter extends PagerAdapter {

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

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }
    }
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

        /**
         * 接收子线程传递过来的数据
         */
        private Handler mHandler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                mViewPaper.setCurrentItem(currentItem);
            };
        };
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }


    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "index is && menu text is "+item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    private List<Map<String, Object>> getData() {

        for (i = 0; i < selNam.length; i++) {


            //tv1[i].setText(selNam[i]);

            // tv2[i].setText(selNum[i]);

            // iv[i].setImageResource(icon[i]);



            Map<String, Object> map = new HashMap<String, Object>();
            map.put("selNam", selNam[i]);
            map.put("selNum", selNum[i]);
            map.put("selIcon", icon[i]);


            gridList.add(map);
        }
        return gridList;
    }

    /*   @Override
       public void onClick(View view) {
           Log.i("开始设置一个按钮",i+"");
           Intent intent = new Intent(Test123.this, conversation.class);
           startActivity(intent);
           Toast.makeText(Test123.this,"666",Toast.LENGTH_SHORT).show();

           Log.i("设置点击事件之后",i+"");
       }*/
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // Intent intent = new Intent(getActivity(), conTest.class);//Fragment中用getActivity()获取
        // startActivity(intent);
    }
}


