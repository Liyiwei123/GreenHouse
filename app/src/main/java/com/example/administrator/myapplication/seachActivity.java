package com.example.administrator.myapplication;

import android.app.DatePickerDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import adapt.ItemAdapter;
import adapt.SeachAdapter;
import user.Account;
import user.Message;

public class seachActivity extends AppCompatActivity implements View.OnClickListener{
    private String adress;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private int year, month, day;
    private  String start="ok";
    private  String end="ok";
    private Handler handler=null;
    private ListView tableListView=null;
    private SeachAdapter lis;
    private List<Message> list=new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        initActionBar();
        adress="http://192.168.61.1:8888/message/selectSupplier.action";
        String defaultadress="http://192.168.61.1:8888/message/temperature.action?id=1";
        HttpUtil.sendHttpRequest(defaultadress, new HttpCallbackListener() {


            @Override
            public void onFinish(Object response) {
                Message message= JSON.parseObject((String)response, Message.class);
                Log.d("nullpoint",message.toString());
                list.add(message);
                lis= new SeachAdapter(seachActivity.this,list);
                Log.d("nullpoint","更新ui");
                tableListView=findViewById(R.id.list);
                tableListView.setAdapter(lis);
                Log.d("nullpoint",lis.toString());
                lis.notifyDataSetChanged();//更新UI
            }

            @Override
            public void onError(Exception e) {

            }
        });
//        HttpUtil.sendHttpRequest(adress, new HttpCallbackListener() {
//            //private List<Account>list;
//            @Override
//            public void onFinish(Object response) {
//                //设置表格标题背景颜色
////                ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
////                tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
//                //List<Message> list=new ArrayList<Message>();
//                //System.out.println(response.toString().getClass());
//                //Message shopInfo = JSON.parseObject((String) response, Message.class);
//                Map map= (Map) JSONObject.parse((String)response);
//
//                List<Message> list=(List)map.get("rows");
//
////                for (Message message:list1) {
////                    list.add(message);
////
////                }
//                //Account acc=new Account(1,"2","3","4");
//                //list.add(shopInfo);
//                System.out.println("添加Account");
//                //list.add(acc);
//                //System.out.println(shopInfo+"shopInfo");
//                ListView tableListView = (ListView) findViewById(R.id.list);
//                SeachAdapter lis= new SeachAdapter(seachActivity.this,list);
//                System.out.println("加入适配器");
//                tableListView.setAdapter(lis);
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//                System.out.println("onError");
//            }
//        });


                handler=new Handler();
                btn1 = (Button) findViewById(R.id.btn1);
                btn2 = (Button) findViewById(R.id.btn2);
                btn3 = (Button) findViewById(R.id.btn3);
                btn1.setOnClickListener(this);
                btn2.setOnClickListener(this);
                btn3.setOnClickListener(this);






    }
    public void initActionBar() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.activity_main_toolbar);

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
    private void datePickerDialog1() {
        //这里月份不知道为什么少1
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                start = String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
                btn1.setText(start);
                Log.d("ok",start);
            }
        }, year, month, day).show();
    }
    private void datePickerDialog2() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                end= String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
                btn2.setText(end);
                Log.d("ok",end);

            }
        }, year, month, day).show();
    }

    //获取当前系统时间
    private void date() {
        Calendar c = Calendar.getInstance();
        //年
        year = c.get(Calendar.YEAR);
        //月
        month = c.get(Calendar.MONTH);
        //日
        day = c.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                date();
                datePickerDialog1();

                break;
            case R.id.btn2:
                date();
                datePickerDialog2();

                break;
            case R.id.btn3:
                if (!start.equals("ok")){
                    String adress1=adress+"?start="+start+"&end="+end;
                    //Get方法
                HttpUtil.sendHttpRequest(adress1,new HttpCallbackListener() {
                    @Override
                    public void onFinish(Object response) {
                        //Map map= (Map) JSONObject.parse((String)response);
                        Log.d("date",start);
                        Log.d("date",end);
                        Log.d("date",response.toString());
                        list.clear();

                      list=(List<Message>) JSONArray.parse((String)response);
                        System.out.println(list.size());
                        tableListView = (ListView) findViewById(R.id.list);
                        handler.post(udpUIRunnable);
                        Log.d("thread",Thread.currentThread().getName());
                        System.out.println("加入适配器");
                    }

                    @Override
                    public void onError(Exception e) {

                    }});}
                    else{
                    Log.d("nullpoint","没赋值");
                }
                   break;

        }
    }
    Runnable   udpUIRunnable=new  Runnable(){
        @Override
        public void run() {
            lis= new SeachAdapter(seachActivity.this,list);
            //Log.d("nullpoint",list.get(0).toString());
            tableListView.setAdapter(lis);//更新UI
            Log.d("nullpoint",lis.toString());
            lis.notifyDataSetChanged();

        }
    };
}
