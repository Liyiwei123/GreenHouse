package com.example.administrator.myapplication.servicep;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.myapplication.ChartActivity;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

import Utils.HttpCallbackListener;
import Utils.HttpUtil;
import user.Message;

/**
 * Created by Administrator on 2018/2/25.
 */
public class PollingService extends Service {
    public static final String ACTION = "com.example.administrator.myapplication.MainActivity.servicep.PollingService";
    int count = 0;
    private Notification mNotification;
    private NotificationManager mManager;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        initNotifiManager();
    }
    @Override
    public int onStartCommand(Intent intent, int flag,int startId) {
        new PollingThread().start();
        return super.onStartCommand(intent, flag, startId);
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = new Notification.Builder(this).//
                setContentTitle("title1").//
                setContentText("Content Text").//
                setSmallIcon(R.drawable.ic_menu_camera).//
                setAutoCancel(true).//
                build();
    }
    //弹出Notification
    private void showNotification(Message message) {
        mNotification.when = System.currentTimeMillis();
        //Navigator to the new activity when click the notification title
        Intent i = new Intent(this, ChartActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
                0);

       mNotification= new Notification.Builder(this).//
                setContentTitle("title"+count).//
                setContentText("温度异常"+Float.toString(message.getTemp())+"摄氏度").//
                setSmallIcon(R.drawable.ic_menu_manage).//
                setAutoCancel(true).//
                setContentIntent(pendingIntent).build();
                mManager.notify(1, mNotification);
//        mNotification.setLatestEventInfo(this,
//                getResources().getString(R.string.app_name), "You have new message!", pendingIntent);
//        mManager.notify(0, mNotification);
    }
    /**
     * Polling thread
     * 模拟向Server轮询的异步线程
     * @Author liyiwei
     *
     */

    class PollingThread extends Thread {
        @Override
        public void run() {
//            System.out.println("Polling...");
//            count ++;
//            //当计数能被5整除时弹出通知
//            if (count % 5 == 0) {
//                showNotification();
//                System.out.println("New message!");
//            }
            String address="http://192.168.61.1:8888/message/temperature.action?id=1";
            HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                @Override
                public void onFinish(Object response) {
                    Message message= JSON.parseObject((String)response, Message.class);
                   if(message.getTemp()<10||message.getTemp()>40){
                       Log.d("LIYIWEI",Float.toString(message.getTemp()));
                       showNotification(message);
                   }
                }

                @Override
                public void onError(Exception e) {

                }
            });
    }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }
}