package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;

public class Main4Activity extends AppCompatActivity {
     private Intent intent1;
    private Intent intent2;
    private Intent intent3;
    private Intent intent4;
    private Intent intent5;
    private Intent intent6;
    @BindView(R.id.but2)
    private Button but2;
    @BindView(R.id.but3)
    private Button but3;
    @BindView(R.id.but4)
    private Button but4;
    @BindView(R.id.but5)
    private Button but5;
    @BindView(R.id.but6)
    private Button but6;
    @BindView(R.id.but7)
    private Button but7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        intent1=new Intent(this, Main2Activity.class);
        intent2=new Intent(this,MainNavigationActivity.class);
        intent3=new Intent(this, WebAppActivity.class);
        intent4=new Intent(this,MainListActivity.class);
        intent5=new Intent(this,SimpleMonitoringActivity.class);
        intent6=new Intent(this,WebAppActivity.class);
         but2=(Button)findViewById(R.id.but2);
         but2.setOnClickListener(new ButtonListener());
        but3=(Button)findViewById(R.id.but3);
        but3.setOnClickListener(new ButtonListener());
        but4=(Button)findViewById(R.id.but4);
        but4.setOnClickListener(new ButtonListener());
        but5=(Button)findViewById(R.id.but5);
        but5.setOnClickListener(new ButtonListener());
        but6=(Button)findViewById(R.id.but6);
        but6.setOnClickListener(new ButtonListener());
    }
    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.but2){
                startActivity(intent1);
            }
            if(view.getId()==R.id.but3){
                startActivity(intent2);

            }
            if(view.getId()==R.id.but4){
                startActivity(intent3);
            }
            if(view.getId()==R.id.but5){
                startActivity(intent4);
            }
            if(view.getId()==R.id.but6){
                startActivity(intent5);
            }
            if(view.getId()==R.id.but7){
                startActivity(intent6);
            }
        }
    }



}
