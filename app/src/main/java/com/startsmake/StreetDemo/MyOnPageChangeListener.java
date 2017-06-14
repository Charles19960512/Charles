package com.startsmake.StreetDemo;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import java.util.List;

/*
设置圆点随viewpage滑动而改变
 */

public class MyOnPageChangeListener implements OnPageChangeListener {
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    public MyOnPageChangeListener(List<View> dots, int currentItem, int oldPosition ){

        this.dots = dots;
        this.currentItem = currentItem;
        this.oldPosition=oldPosition;

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        dots.get(position).setBackgroundResource(R.drawable.dot_focused);
        dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

        oldPosition = position;
        currentItem = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
