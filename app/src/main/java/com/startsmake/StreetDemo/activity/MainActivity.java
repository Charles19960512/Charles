
package com.startsmake.StreetDemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.startsmake.StreetDemo.R;
import com.startsmake.StreetDemo.fragment.StreetFragment;
import com.startsmake.StreetDemo.fragment.HomeFragment;
import com.startsmake.StreetDemo.fragment.MessageFragment;
import com.startsmake.StreetDemo.fragment.PersonFragment;
import com.startsmake.mainnavigatetabbar.widget.MainNavigateTabBar;
/**
 * Created by CharlesC on 2017/4/18.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_STREET = "租街";
    private static final String TAG_PAGE_PUBLISH = "发布";
    private static final String TAG_PAGE_MESSAGE = "消息";
    private static final String TAG_PAGE_PERSON = "我的";


    private MainNavigateTabBar mNavigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigateTabBar = (MainNavigateTabBar) findViewById(R.id.mainTabBar);

        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);

        mNavigateTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(StreetFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_city, R.mipmap.comui_tab_city_selected, TAG_PAGE_STREET));
        mNavigateTabBar.addTab(Publish.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_find, R.mipmap.comui_tab_find_selected, TAG_PAGE_PUBLISH));
        mNavigateTabBar.addTab(MessageFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_message, R.mipmap.comui_tab_message_selected, TAG_PAGE_MESSAGE));
        mNavigateTabBar.addTab(PersonFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }


    public void onClickPublish(View v) {
        Intent intent = new Intent(MainActivity.this,Publish.class);
        startActivity(intent);

    }
}