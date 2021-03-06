package com.startsmake.StreetDemo;

    import android.content.Context;
    import android.util.AttributeSet;
    import android.widget.GridView;
/*
* 用MyGridView来消除GridView与ScrollView共用的冲突
* */

    public class MyGridView extends GridView {

        public MyGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyGridView(Context context) {
            super(context);
        }

        public MyGridView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
        //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ListView同理
        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }
