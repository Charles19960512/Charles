package com.startsmake.StreetDemo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.startsmake.StreetDemo.R;

/**
 * Created by CharlesC on 2016/10/22.
 */
public class LoginActivity extends AppCompatActivity {

    private Button bt1, bt2, bt4,rootbtn;
    ImageButton bt3;
    EditText et1, et2;
    String UserNm, Pword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et1 = (EditText) findViewById(R.id.accoutbuf);
        et2 = (EditText) findViewById(R.id.passwordbuf);

        bt1 = (Button) findViewById(R.id.Loginbutton);
        bt2 = (Button) findViewById(R.id.forget);
        bt4 = (Button) findViewById(R.id.RButton);
        rootbtn = (Button) findViewById(R.id.root);

        rootbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, com.startsmake.StreetDemo.activity.MainActivity.class);
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserNm = et1.getText().toString();
                Pword = et2.getText().toString();

                int result = SqliteDB.getInstance(getApplicationContext()).Quer(Pword, UserNm);

                if (result == 1) {
                    Toast.makeText(LoginActivity.this, "用户登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, com.startsmake.StreetDemo.activity.MainActivity.class);
                    startActivity(intent);
                    finish();

                } else if (result == 0) {
                    Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();

                } else if (result == -1) {
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "暂时未开放", Toast.LENGTH_SHORT).show();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, register.class);
                startActivity(intent);
            }
        });

    }
}


