package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.myapplication.servicep.ForegroundService;

import Utils.VolleyUtil;

public class VolleyActivity extends AppCompatActivity{

    String address="http://192.168.61.1:8888/message/temperature.action?id=1";
    Button button;
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        final Intent intent = new Intent(this,ForegroundService.class);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("cmd",0);//0,开启前台服务,1,关闭前台服务
                startService(intent);
            }
        });


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                VolleyUtil volleyUtil=new VolleyUtil();
//                System.out.println("什么捏");
//                StringRequest request=volleyUtil.init(address,VolleyActivity.this);
//
//                mQueue.add(request);
//
//            }
//        });
//         mQueue = Volley.newRequestQueue(this);

    }


}
