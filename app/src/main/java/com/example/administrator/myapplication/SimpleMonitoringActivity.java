package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import Utils.HikUtil;
import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import Utils.LineChartManager;
import Utils.MyXFormatter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleMonitoringActivity extends AppCompatActivity {
    private static final String TAG = "SimpleMonitoringActivity";
    //-------------------------------------------------------   ---------------------------------------
    @BindView(R.id.surfaceView)//@Bind报错
    SurfaceView surfaceView;
    private String address="http://192.168.61.1:8888/message/SelectTempFromDate.action";
    private String adress="http://39.106.15.198/GreenHouse/message/SelectTempFromDate.action";
    private user.Message message;
    final List<List<Float>> yValues = new ArrayList<>();
    final List<Float> yValue = new ArrayList<>();
    //----------------------------------------------------------------------------------------------
    //数据流码
    private static final int PLAY_HIK_STREAM_CODE = 1001;
    //摄像头ip地址
    private static final String IP_ADDRESS = "192.168.1.64";
    //摄像头端口号
    private static final int PORT = 8000;
    //账号
    private static final String USER_NAME = "admin";
    //密码
    private static final String PASSWORD = "zjut123456";
    //----------------------------------------------------------------------------------------------
    //Handler处理线程问题
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //获取数据流码
                case PLAY_HIK_STREAM_CODE:
                    //判断停止或开启数据流
                    HikUtil.playOrStopStream();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_monitoring);
        //开启黄油刀绑定ID
        ButterKnife.bind(this);
        //初始化toolbar
        initActionBar();

        //@Bind注解问题 获取surfaceview
        surfaceView=findViewById(R.id.surfaceView);
        //初始化SDK
        HikUtil.initSDK();
        //初始化view
        HikUtil.initView(surfaceView);

        HikUtil.setDeviceData(IP_ADDRESS,PORT,USER_NAME,PASSWORD);
        //注册
        HikUtil.loginDevice(mHandler,PLAY_HIK_STREAM_CODE);
        //初始化表格

        initChart();
        //初始化上拉框
        spinner();
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

protected void initChart(){

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
                values[i]=((JSON)list.get(i)).toJavaObject(user.Message.class).getTime();
            }
            LineChart lineChart1 = (LineChart) findViewById(R.id.chart);
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
                yValue.add(((JSON)list.get(j)).toJavaObject(user.Message.class).getTemp());
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
            names.add("折线一");
            names.add("折线二");
            names.add("折线三");
            names.add("折线四");
            lineChartManager1.setDescription("温度");
            lineChartManager1.setYAxis(50, -10, 50);
            lineChartManager1.setHightLimitLine(40, "高温报警", Color.RED);
            lineChartManager1.setLowLimitLine(-10,"低温报警",Color.BLUE);
            //创建多条折线的图表
            lineChartManager1.showLineChart(xValues, yValue, names.get(1), colours.get(3));



        }

        @Override
        public void onError(Exception e) {

        }
    });
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HikUtil.playOrStopStream();
    }
    //定义上拉框
    private void spinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = getResources().getStringArray(R.array.mySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] select = getResources().getStringArray(R.array.mySpinner);
                Toast.makeText(SimpleMonitoringActivity.this, "你选择了"+select[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
