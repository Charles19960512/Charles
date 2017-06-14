package com.startsmake.StreetDemo.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.startsmake.StreetDemo.MyApplication;
import com.startsmake.StreetDemo.R;
import com.startsmake.StreetDemo.login.register;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CharlesC on 2017/4/18.
 */

public class Publish extends AppCompatActivity {

    private String INTENT_TYPE  = "image/*";
    private int REQUESTCODE = 100;

    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private ImageButton bt1,bt2;
    private Button bt3;
    private ImageView imageView;
    private VideoView videoView;
    private Bitmap bitmap = null;
    private EditText t1,t2,t3,t4,t5;
    //private TextView t3;
    private String title,info,location,price,streetName;
    private List<Map<String, Object>> p_list;
    private Map<String, Object> p_map = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        bt1=(ImageButton)findViewById(R.id.addimage);
        bt2=(ImageButton)findViewById(R.id.addvideo);
        bt3=(Button)findViewById(R.id.publish) ;
        imageView = (ImageView)findViewById(R.id.publish_image1);
        videoView = (VideoView)findViewById(R.id.showvideo);

        t1 = (EditText) findViewById(R.id.publish_title);
        t2 = (EditText) findViewById(R.id.publish_info);
        t3 = (EditText) findViewById(R.id.publish_location);
        t4 = (EditText) findViewById(R.id.publish_price);
        t5 = (EditText) findViewById(R.id.publish_streetName);

        final MyApplication appState = ((MyApplication) getApplicationContext());
        p_list=appState.getlist();


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(INTENT_TYPE);
                startActivityForResult(intent,1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(INTENT_TYPE);
                startActivityForResult(intent,2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
将值传到List中
 */
                title = t1.getText().toString();
                info = t2.getText().toString();
                location = t3.getText().toString();
                price = t4.getText().toString();
                streetName = t5.getText().toString();
                if(title.length()==0){
                    Toast.makeText(Publish.this,"标题不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(info.length()==0){
                    Toast.makeText(Publish.this,"商品描述不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(price.length()==0){
                    Toast.makeText(Publish.this,"价格不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(streetName.length()==0){
                    Toast.makeText(Publish.this,"租街不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(location.length()==0){
                    Toast.makeText(Publish.this,"位置不能为空",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Toast.makeText(Publish.this, "发布成功", Toast.LENGTH_SHORT).show();
                p_map.put("lsi_userAvatar", R.mipmap.ic_launcher);
                p_map.put("lsi_userName", "测试用户");
                p_map.put("lsi_userInfo", "1天前来过");
                p_map.put("lsi_price", price+"元");

                p_map.put("lsi_image1", R.drawable.rent1);

                p_map.put("lsi_image2", imageView);
                p_map.put("lsi_image3", imageView);
                p_map.put("lsi_title", title);
                   // p_map.put("lsi_info", info);
                p_map.put("lsi_location", location);
                p_map.put("lsi_streetName", streetName);
                p_list.add(p_map);


                    Bundle bundle = new Bundle();
                    bundle.putString("info",info);
                    Intent intent = new Intent(Publish.this,MainActivity.class);
                    intent.putExtra("bitmap",bitmap);
                    intent.putExtras(bundle);

                startActivity(intent);
                    finish();
                }
            }
        });



        spinner = (Spinner) findViewById(R.id.spinner);

        //数据
        data_list = new ArrayList<String>();
        data_list.add(getString(R.string.way1));
        data_list.add(getString(R.string.way2));
        data_list.add(getString(R.string.way3));
        data_list.add(getString(R.string.way4));

        arr_adapter= new ArrayAdapter<String>(this, R.layout.myspinner, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            Log.e("TAG--->onresult","ActivityResult resultCode error");
            return;
        }

        //获得图片

        ContentResolver resolver = getContentResolver();
        if(requestCode == 1){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(resolver,uri);//获得图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      //  drawable = new BitmapDrawable(bitmap);

        imageView.setImageBitmap(bitmap);
        //imageView.setImageResource();

        Bitmap bitmap2 = null;
        ContentResolver resolver2 = getContentResolver();
        if(requestCode == 2){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(resolver,uri);//获得图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        暂时留空


        //获得路径
        if(requestCode == REQUESTCODE){
            Uri uri = data.getData();
            uri = geturi(data);//解决方案
            String[] pro = {MediaStore.Images.Media.DATA};
            //好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(uri,pro,null,null,null);
            Cursor cursor1 = getContentResolver().query(uri,pro,null,null,null);
            //拿到引索
            int index =  cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //移动到光标开头
            cursor.moveToFirst();
            //最后根据索引值获取图片路径
            String path = cursor.getString(index);
            Log.d("Tag--->path",path);

        }

    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     * @param intent
     * @return
     */
    public Uri geturi(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
}
