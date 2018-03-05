package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.administrator.myapplication.servicep.PollingService;

import java.util.HashMap;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import Utils.LoginUtil;
import Utils.PollingUtils;
import user.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mPhoneNumberEditText;
    private EditText mPassWordEditText;
    private Button mLoginButton;
    private Intent intent1;
    private Intent intent2;
    private LoginUtil loginUtil;
    private  User user;

    //用于接收Http请求的servlet的URL地址，请自己定义
    private String originAddress = "http://39.106.15.198/GreenHouse/user/login.action";
    //用于处理消息的Handler
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";

            if ("OK".equals(msg.obj.toString())){
                result = "success";
                result = msg.obj.toString()+"登陆成功";
            }else if ("Wrong".equals(msg.obj.toString())){
                result = "fail";
                result = msg.obj.toString()+"登陆失败";}
            else {
                result = msg.obj.toString();
                System.out.println(msg.obj);
            }
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar= (Toolbar) findViewById(R.id.mytoolbar);
//        setSupportActionBar(toolbar);

        intent1=new Intent(this,MainNavigationActivity.class);
        intent2=new Intent(this,MainActivity.class);
        System.out.println("正在登陆");


            System.out.println("初始化view");
            initView();
            System.out.println("初始化事件");
            initEvent();

//        System.out.println("Start polling service...");
//        PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("登陆界面开始");
        if(LoginUtil.getCurrentUser(this)!=null){
            Message message=new Message();
            message.obj="用户已经登陆";
            mHandler.sendMessage(message);
            startActivity(intent1);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("登陆页面销毁");
        LoginUtil.clearData(this);
        //Stop polling service

    }
    @Override
    protected void onStop(){
        super.onStop();
//        System.out.println("Stop polling service...");
//        PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
    }
    private void initView() {

        mPhoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        mPassWordEditText = (EditText) findViewById(R.id.passwordEditText);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        Toolbar toolbar= (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initEvent() {
        mLoginButton.setOnClickListener(this);
    }

    public void login() {
        //检查用户输入的账号和密码的合法性
        if (!isInputValid()){
            Message message = new Message();
            message.obj ="太短了，至少5个数admin";
            mHandler.sendMessage(message);
            return;
        }
        //构造HashMap
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(User.PHONENUMBER, mPhoneNumberEditText.getText().toString());
        params.put(User.PASSWORD, mPassWordEditText.getText().toString());

        try {
            //构造完整URL
            String compeletedURL = HttpUtil.getURLWithParams(originAddress, params);
            //发送请求
            HttpUtil.sendHttpRequest(compeletedURL, new HttpCallbackListener() {
                @Override
                public void onFinish(Object response) {
                    Message message = new Message();
                    message.obj = response;
                    mHandler.sendMessage(message);
                    System.out.println(response+"响应");
                    if(response.equals("OK")){
                        //设计当前用户
                        user=new User(mPhoneNumberEditText.getText().toString(),mPassWordEditText.getText().toString(),"愣头青");
                     loginUtil.setCurrentUser(MainActivity.this,user);

                     intent1.putExtra("username",user.getUserName());
                     startActivity(intent1);
                   //开启报警服务
                    }
                    else{
                        Log.i("fail","账号或密码错误");  }
                }



                @Override
                public void onError(Exception e) {
                    Message message = new Message();
                    message.obj = e.toString();
                    mHandler.sendMessage(message);
                    System.out.println(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        //检查用户输入的合法性，这里暂且默认用户输入合法
        if(mPhoneNumberEditText.getText().toString().length()<5||mPassWordEditText.getText()
                .toString().length()<5){

            return false;
        }
        else{
        return true;}
    }

    @Override
    public void onClick(View v) {

            if(v.getId()==R.id.loginButton){
                login();
        }
//        switch (v.getId()){
//            case R.id.loginButton:
//                login(); break;
//
//        }
    }
}