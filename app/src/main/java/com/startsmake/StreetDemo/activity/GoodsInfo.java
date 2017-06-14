package com.startsmake.StreetDemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.startsmake.StreetDemo.R;

public class GoodsInfo extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5;
    private Button bt1;
    private ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        bt1= (Button) findViewById(R.id.rent);

        img1= (ImageView) findViewById(R.id.g_image1);

        t1= (TextView) findViewById(R.id.g_info);
        t2= (TextView) findViewById(R.id.g_price);
        t3= (TextView) findViewById(R.id.g_location);
        t4=(TextView)findViewById(R.id.g_userName);
        t5=(TextView)findViewById(R.id.g_userInfo);
        Intent intent1=getIntent();
        Bundle bundle=getIntent().getExtras();

        Bitmap bitmap=intent1.getParcelableExtra("bitmap");
        String info=bundle.getString("info");
        String price=bundle.getString("price");
        String location=bundle.getString("location");
        String userName=bundle.getString("userName");
        String userInfo=bundle.getString("userInfo");
        //ImageView Iv=(ImageView) findViewById(R.id.Iv);
        //Iv.setImageResource(id);

        //img1.setImageBitmap(bitmap);
        t1.setText(info);
        t2.setText("￥"+price+"/天");
        t3.setText(location);
        t4.setText(userName);
        t5.setText(userInfo);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsInfo.this,RentPage.class);
                startActivity(intent);
            }
        });
    }
}
