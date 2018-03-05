package com.example.administrator.myapplication.servicep;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;

import com.example.administrator.myapplication.R;


/**
 * Created by Administrator on 2018/2/23.
 */

public class NotificationService extends Service {
    // 获取消息线程
    private MessageThread messageThread = null;

    // 点击查看
    private Intent messageIntent = null;
    private PendingIntent messagePendingIntent = null;

    // 通知栏消息
    private int messageNotificationID = 1000;
    private  NotificationCompat messageNotification = null;
    private NotificationManager notifyManager = null;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("服务启动");

        //获取NotificationManager实例
        notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"lyw")
                //设置小图标
                .setSmallIcon(R.mipmap.sort_bangong)
                //设置通知标题
                .setContentTitle("最简单的Notification")
                //设置通知内容
                .setContentText("只有小图标、标题、内容")
                .setColor(getResources().getColor(R.color.background))
                .setWhen(System.currentTimeMillis());
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());



        messageIntent = new Intent(this, MainMessageActivity.class);
        // 开启线程
        messageThread = new MessageThread();
        messageThread.isRunning = true;

          messageThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 从服务器端获取消息
     *
     */

    class MessageThread extends Thread {
        // 设置是否循环推送
        public boolean isRunning = true;

        public void run() {
            // while (isRunning) {

                // 间隔时间
            try {
                Thread.sleep(1000);
                sendSimplestNotificationWithAction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    messageNotificationID++;
                }

        }


    @Override
    public void onDestroy() {
        // System.exit(0);
        System.out.println("服务已经被销毁");
        messageThread.isRunning = false;
        super.onDestroy();
    }

    /**
     * 模拟发送消息
     *
     * @return 返回服务器要推送的消息，否则如果为空的话，不推送
     */
    public String getServerMessage() {
        return "NEWS!";
    }
    private void sendSimplestNotificationWithAction() {
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //获取PendingIntent
        Intent mainIntent = new Intent(this, MainMessageActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建 Notification.Builder 对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"什么捏")
                .setSmallIcon(R.mipmap.ic_launcher)
                //点击通知后自动清除
                .setAutoCancel(true)
                .setContentTitle("我是带Action的Notification")
                .setContentText("点我会打开MainActivity")
                .setContentIntent(mainPendingIntent)
                .setColor(getResources().getColor(R.color.background));
        //发送通知
        notifyManager.notify(3, builder.build());
    }
}

