package com.example.administrator.myapplication.servicep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import Utils.PollingUtils;

public class MainMessageActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button btnStart;
    private Button btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        initView();

    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题
        Toolbar toolbar= (Toolbar) findViewById(R.id.activity_main_toolbar);
        //在setSupportActionBar(toolbar);之前调用不然会被重置为label属性
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(ChartActivity.this,MainNavigationActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnStart) {
            // 启动Service
//            Intent intent = new Intent(this,NotificationService.class);
//
//            startService(intent);
            System.out.println("Start polling service...");
            PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
        }
        if (id == R.id.btnStop) {
            // 关闭Service
//            Intent intent = new Intent(this,NotificationService.class);
//            //intent.setAction("fuckyouserver");
//            stopService(intent);
            System.out.println("Stop polling service...");
        PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        System.out.println("后退");
        super.onBackPressed();
    }
}
