package com.startsmake.StreetDemo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.startsmake.StreetDemo.R;

import java.text.SimpleDateFormat;

public class register extends AppCompatActivity {
    private Button btR;
    private EditText et1,et2,et3,et4;
    private String t1,t2,t3,t4;//用户名，密码，密码验证，邮箱
    private CheckBox c1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btR = (Button)findViewById(R.id.RegisterButton);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);

        c1 = (CheckBox) findViewById(R.id.checkBox);

        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1 = et1.getText().toString();
                t2 = et2.getText().toString();
                t3 = et3.getText().toString();
                t4 = et4.getText().toString();


                if(t1==null){
                    Toast.makeText(register.this,"用户名不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(t2==null){
                    Toast.makeText(register.this,"密码不能为空",Toast.LENGTH_SHORT).show();

                }
                else if(!t2.equals(t3)){
                    Toast.makeText(register.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();

                }
                else if(!t4.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
                    Toast.makeText(register.this,"邮箱表示错误",Toast.LENGTH_SHORT).show();

                }
                else if (!c1.isChecked()){
                    Toast.makeText(register.this,"你必须接受e学协议才能完成注册",Toast.LENGTH_SHORT).show();
                }
                else {/*注册信息入库*/
                    User user=new User();
                    user.setUsername(t1);
                    user.setUserpwd(t2);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                    String date=sdf.format(new java.util.Date());
                    user.setUserdate(date);

                    int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);

                    if (result==1){
                        Toast.makeText(register.this,"注册成功，立即登录吧",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(register.this,LoginActivity.class);
                        startActivity(intent);
                    }else  if (result==-1)
                    {
                        Toast.makeText(register.this,"该用户名已被注册",Toast.LENGTH_SHORT).show();;
                    }
                    else
                    {
                        Toast.makeText(register.this,"！",Toast.LENGTH_SHORT).show();;
                    }


                }
            }
        });
    }

}