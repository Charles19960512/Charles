package com.startsmake.StreetDemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.startsmake.StreetDemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by CharlesC on 2017/4/22.
 */
public class PersonalCenter extends AppCompatActivity {

    private ListView listView;
    private List<Map<String,Object>>list;
    private SimpleAdapter adapter;
    private String[] pera={"用户名","性别","地区","收货地址","账号绑定"};
    private String[] perb={"奔跑的租街","男","中国 江西省 南昌市","江西省南昌市紫阳大道99号",""};
    private TextView[] tvb=new TextView[perb.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        listView = (ListView) findViewById(R.id.pc_lv);
        list= new ArrayList<Map<String,Object>>();
        adapter = new SimpleAdapter(this, getData(), R.layout.per_lsv, new String[]{"pera","perb"}, new int[]{R.id.pera,R.id.perb});
        listView.setAdapter(adapter);
    }

    private List<Map<String,Object>> getData() {
        for (int i = 0; i < pera.length; i++) {

            //tvb[i].setText(selNam[i]);
            Log.i("1",i+"");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pera", pera[i]);
            map.put("perb", perb[i]);

            list.add(map);
        }
        return list;
    }


}
