package com.startsmake.StreetDemo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.startsmake.StreetDemo.R;
/**
 * Created by CharlesC on 2017/4/18.
 */

public class  PersonFragment extends Fragment {

//    Toolbar Ptoolbar;
//    ActionBar PactionBar;
    ImageButton Ibtn1;
//    TextView centerTtitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Ibtn1 = (ImageButton)getActivity().findViewById(R.id.imageButton);

        Ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.startsmake.StreetDemo.activity.PersonalCenter.class);
                startActivity(intent);
            }
        });

//        Ptoolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        centerTtitle = (TextView) getActivity().findViewById(R.id.title);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(Ptoolbar);
//        Ptoolbar.setTitle(getString(R.string.mine));
//        Ptoolbar.inflateMenu(R.menu.menu_list);
//        PactionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        PactionBar.setDisplayHomeAsUpEnabled(false);//设置返回箭头显示
//        PactionBar.setTitle("我的消息");//父标题
//        PactionBar.setSubtitle("子标题");//子标题
//        centerTtitle.setText("联系人");//自定义标题


    }

    private void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_list2,menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
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
//            case R.id.action_item1:
//                showToast("待定");
//                return true;
//            case R.id.action_item2:
//                showToast("待定");
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
