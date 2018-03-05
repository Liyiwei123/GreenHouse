package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Color;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import Utils.LineChartManager;
import Utils.MyXFormatter;
import user.Message;

public class ChartActivity extends AppCompatActivity {
//private String address=getResources().getString(R.string.messageadress);
    private String address="http://192.168.61.1:8888/message/SelectTempFromDate.action";
    private String adress="http://39.106.15.198/GreenHouse/message/SelectTempFromDate.action";
    private Message message;
    private Toolbar toolbar;

    final List<List<Float>> yValues = new ArrayList<>();
    final List<Float> yValue = new ArrayList<>();
    public void initActionBar() {
        toolbar= (Toolbar) findViewById(R.id.activity_main_toolbar);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initActionBar();


        //设置y轴的数据()创建四条曲线的Y值,传入温度等值

//        for (int i = 0; i < 4; i++) {
//            List<Float> yValue = new ArrayList<>();
//            for (int j = 0; j <= 10; j++) {
//                yValue.add((float) (Math.random() * 50));
//            }
//            yValues.add(yValue);
//        }


        //匿名内部类开启一个新的线程,动画效果不能在子线程用
        HttpUtil.sendHttpRequest(adress, new HttpCallbackListener() {
             String[] values = new String[10];
            @Override
            public void onFinish(Object response) {
//                Looper.prepare();
//                Looper.loop();
                Map map= (Map)JSON.parse((String)response);
                List list=(List)map.get("rows");

                //message= JSON.parseObject((String)response,Message.class);
                for (int i=0;i<10;i++){
                    //values[i]=message.getTime();
                    values[i]=((JSON)list.get(i)).toJavaObject(Message.class).getTime();
                }
                LineChart lineChart1 = (LineChart) findViewById(R.id.line_chart1);
                //LineChart lineChart2 = (LineChart) findViewById(R.id.line_chart2);

                LineChartManager lineChartManager1 = new LineChartManager(lineChart1);
               // LineChartManager lineChartManager2 = new LineChartManager(lineChart2);

                MyXFormatter formatter = new MyXFormatter(values);
                XAxis xAxis = lineChart1.getXAxis();
                xAxis.setValueFormatter(formatter);

                //设置x轴的数据 传入时间日期
                ArrayList<Float> xValues = new ArrayList<>();
                for (int i = 0; i <=10; i++) {
                    //xValues.add((float) i);
                    xValues.add((float)i);
                }

                for (int j = 0; j <=10; j++) {
                    yValue.add(((JSON)list.get(j)).toJavaObject(Message.class).getTemp());
                    System.out.println("Y轴"+yValue.get(j));
            }
               // yValues.add(yValue);
                //颜色集合
                List<Integer> colours = new ArrayList<>();
                colours.add(Color.GREEN);
                colours.add(Color.BLUE);
                colours.add(Color.RED);
                colours.add(Color.CYAN);

                //线的名字集合
                List<String> names = new ArrayList<>();
                names.add("当天温度曲线");
                names.add("本周温度曲线");
                names.add("本月温度曲线");
                names.add("本季温度曲线");

                lineChartManager1.showLineChart(xValues, yValue, names.get(1), colours.get(3));
                //设置坐标轴
                lineChartManager1.setDescription("温度");
                lineChartManager1.setYAxis(50, -10, 40);
                lineChartManager1.setHightLimitLine(40, "高温报警", Color.RED);
                lineChartManager1.setLowLimitLine(-10,"低温报警",Color.BLUE);
                //创建多条折线的图表




            }

            @Override
            public void onError(Exception e) {

            }
        });


//
//        lineChartManager2.showLineChart(xValues, yValues, names, colours);
//        lineChartManager2.setYAxis(100, 0, 11);
//        lineChartManager2.setDescription("温度");

    }

}