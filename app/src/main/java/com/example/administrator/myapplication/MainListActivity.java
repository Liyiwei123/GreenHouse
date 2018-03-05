package com.example.administrator.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import Utils.ListCallBackListener;
import adapt.ItemAdapter;
import adapt.TableAdapter;
import user.Account;
import user.Item;

public class MainListActivity extends AppCompatActivity {
    private String adress;
    private Button btn1;
    private Button btn2;
    private int year, month, day;
    private String start="ok";
    private String end="ok";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        adress = "http://192.168.61.1:8888/user/list.action?userlogin=admin&userpass=admin";

        btn1 = (Button) findViewById(R.id.btn1);
        //点击事件在主线程调用
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date();
                datePickerDialog1();
                // Log.d("ok",date);
                //System.out.println(start);
                System.out.println(Thread.currentThread().getName());

            }
        });
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date();
                datePickerDialog2();
                //System.out.println(end);
                System.out.println(Thread.currentThread().getName());
            }
        });

        new Thread(new Runnable() {
        @Override
        public void run () {
            HttpUtil.sendHttpRequest(adress, new HttpCallbackListener() {
                //private List<Account>list;
                @Override
                public void onFinish(Object response) {
                    //设置表格标题背景颜色
//                ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
//                tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
                    List<Account> list = new ArrayList<Account>();
                    System.out.println(response.toString().getClass());
                    Account shopInfo = JSON.parseObject((String) response, Account.class);
                    //Account acc=new Account(1,"2","3","4");
                    //shopInfo.setUsername(start);
                    list.add(shopInfo);
                    System.out.println("添加Account");
                    //list.add(acc);
                    //System.out.println(shopInfo+"shopInfo");
                    ListView tableListView = (ListView) findViewById(R.id.list);
                    ItemAdapter lis = new ItemAdapter(MainListActivity.this, list);
                    System.out.println("加入适配器");
                    tableListView.setAdapter(lis);


                }

                @Override
                public void onError(Exception e) {
                    System.out.println("onError");
                }
            });
        }
    }).start();

}



    private void datePickerDialog1() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = String.format("%d-%d-%d", year, monthOfYear, dayOfMonth);
                btn1.setText(date);
                start=date;
            }
        }, year, month, day).show();
    }
    private void datePickerDialog2() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = String.format("%d-%d-%d", year, monthOfYear, dayOfMonth);
                btn2.setText(date);
                end=date;
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
}
