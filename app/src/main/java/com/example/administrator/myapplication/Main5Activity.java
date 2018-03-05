package com.example.administrator.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import adapt.TableAdapter;
import user.Account;
import user.Goods;

public class Main5Activity extends AppCompatActivity {
    public  String adress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        adress="http://192.168.61.1:8888/account/login.action?accPass=admin&accLogin=admin";
        HttpUtil.sendHttpRequest(adress, new HttpCallbackListener() {
            @Override
            public void onFinish(Object response) {
                //接收返回数据解析
                ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
                tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
                List<Goods> list = new ArrayList<Goods>();
                list.add(new Goods("01", "4", "4",34,23,24));

                ListView tableListView = (ListView) findViewById(R.id.list);
                TableAdapter adapter = new TableAdapter(Main5Activity.this, list);
                tableListView.setAdapter(adapter);
                System.out.println(response.toString());
            }

            @Override
            public void onError(Exception e) {

            }
        });
        //设置表格标题的背景颜色


    }
}

