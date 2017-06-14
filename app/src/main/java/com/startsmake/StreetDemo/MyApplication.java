package com.startsmake.StreetDemo;

/**
 * Created by CharlesC on 2017/5/13.
 */
import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApplication extends Application{

    public List<Map<String, Object>> homelist= new ArrayList<Map<String,Object>>();;
    public Map<String, Object> lsmap = new HashMap<String, Object>();

        public List getlist() {
         return homelist;
        }

        public void setlist(List list) {
           this.homelist = list;
        }

    public Map<String, Object> getmap() {
        return lsmap;
    }

    public void setmap(Map<String, Object> map) {
        this.lsmap = map;
    }
}