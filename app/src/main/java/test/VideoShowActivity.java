package test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import com.example.administrator.myapplication.R;

import user.MonitorCameraInfo;

public class VideoShowActivity extends AppCompatActivity {

    /** SurfaceView对象，用来显示视频 */
    private SjrsSurfaceView nowSjrsSurfaceView;
    /** 视频向上 */
    private Button btUp;
    /** 视频向下 */
    private Button btDown;
    /** 视频向左 */
    private Button btLeft;
    /** 视频向右 */
    private Button btRigth;
    /** 视频上左 */
    private Button btUpLeft;
    /** 视频上右 */
    private Button btUpRigth;
    /** 视频下左 */
    private Button btDownLeft;
    /** 视频下右 */
    private Button btDownRigth;
    /** button点击事件*/
    private ButtonListener btnListener;
    /**实例化网络库SDK*/
    private SjrsSurfaceView mSurface;
    /** 监控点信息类 */
    private MonitorCameraInfo cameraInfo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findView();
        setListener();
        init();
    }

    /**
     * 组件配置
     */
    private void findView() {
        // TODO Auto-generated method stub
        nowSjrsSurfaceView = (SjrsSurfaceView) findViewById(R.id.video);
        btUp = (Button) findViewById(R.id.bt_up);
        btDown = (Button) findViewById(R.id.bt_down);
        btLeft = (Button) findViewById(R.id.bt_left);
        btRigth = (Button) findViewById(R.id.bt_down_right);
        btUpLeft = (Button) findViewById(R.id.bt_up_left);
        btUpRigth = (Button) findViewById(R.id.bt_up_right);
        btDownLeft = (Button) findViewById(R.id.bt_down_left);
        btDownRigth = (Button) findViewById(R.id.bt_down_right);
        btnListener = new ButtonListener();
    }

    /**
     * 监听设置
     */
    private void setListener() {
        // TODO Auto-generated method stub
        btUp.setOnClickListener(btnListener);
        btDown.setOnClickListener(btnListener);
        btLeft.setOnClickListener(btnListener);
        btRigth.setOnClickListener(btnListener);
        btUp.setOnTouchListener(btnListener);
        btDown.setOnTouchListener(btnListener);
        btLeft.setOnTouchListener(btnListener);
        btRigth.setOnTouchListener(btnListener);
        btUpLeft.setOnClickListener(btnListener);
        btUpRigth.setOnClickListener(btnListener);
        btDownLeft.setOnClickListener(btnListener);
        btDownRigth.setOnClickListener(btnListener);
        btUpLeft.setOnTouchListener(btnListener);
        btUpRigth.setOnTouchListener(btnListener);
        btDownLeft.setOnTouchListener(btnListener);
        btDownRigth.setOnTouchListener(btnListener);
    }

    /**
     * 页面初始化
     */
    private void init() {
        // TODO Auto-generated method stub
        mSurface = new SjrsSurfaceView(VideoShowActivity.this);
    }

    /**
     * 显示
     */
    protected void onResume() {
        super.onResume();
        // 如果没有在播放的话
        if (!nowSjrsSurfaceView.playFlag) {
            // 监控点信息类
            cameraInfo = new MonitorCameraInfo();
            //192.168.1.64
            cameraInfo.serverip = "192.168.1.64";
            cameraInfo.serverport = 8000;
            cameraInfo.username = "admin";
            cameraInfo.userpwd = "zjut123456";
            cameraInfo.channel = 2;
            cameraInfo.describe = "测试点";

            nowSjrsSurfaceView.setMonitorInfo(cameraInfo);
            // 开始实时预览
            nowSjrsSurfaceView.startPlay();
        }
    }

    /**
     * 暂停
     */
    protected void onPause() {
        super.onPause();
        if (nowSjrsSurfaceView.playFlag) {
            nowSjrsSurfaceView.stopPlay(); // 停止实时预览
        }
    }

    /**
     * 方向按键监听
     * 注意：此处的通道号参数 实质为：2 但必须指定为：1(主通道)才可以做控制
     */
    public class ButtonListener implements View.OnTouchListener,View.OnClickListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.bt_up:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,21,0);
                    System.out.println("向上");
                    break;
                case R.id.bt_down:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,22,0);
                    System.out.println("向下");
                    break;
                case R.id.bt_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,23,0);
                    System.out.println("向左");
                    break;
                case R.id.bt_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,24,0);
                    System.out.println("向右");
                    break;
                case R.id.bt_up_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,25,0);
                    System.out.println("上左");
                    break;
                case R.id.bt_up_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,26,0);
                    System.out.println("上右");
                    break;
                case R.id.bt_down_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,27,0);
                    System.out.println("下左");
                    break;
                case R.id.bt_down_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,28,0);
                    System.out.println("下右");
                    break;
                        /*case R.id.bt_amplification:
                                boolean iss = mSurface.SjrsSurface().NET_DVR_PTZControlWithSpeed(cameraInfo.playNum,15,0,3);
                                System.out.println("异常："+mSurface.SjrsSurface().NET_DVR_GetLastError());
                                System.out.println("焦距放大"+iss);
                                break;
                        case R.id.bt_shrink:
                                boolean is = mSurface.SjrsSurface().NET_DVR_PTZControlWithSpeed(cameraInfo.playNum,16,0,3);
                                System.out.println("焦距缩小"+is);
                                break;*/
                default:
                    break;
            }
            return false;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_up:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,21,1);
                    System.out.println("结束向上移动");
                    break;
                case R.id.bt_down:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,22,1);
                    System.out.println("结束向下移动");
                    break;
                case R.id.bt_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,23,1);
                    System.out.println("结束向左移动");
                    break;
                case R.id.bt_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,24,1);
                    System.out.println("结束向右移动");
                    break;
                case R.id.bt_up_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,25,1);
                    System.out.println("结束上左移动");
                    break;
                case R.id.bt_up_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,26,1);
                    System.out.println("结束上右移动");
                    break;
                case R.id.bt_down_left:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,27,1);
                    System.out.println("结束下左移动");
                    break;
                case R.id.bt_down_right:
                    mSurface.SjrsSurface().NET_DVR_PTZControl_Other(cameraInfo.userId,1,28,1);
                    System.out.println("结束下右移动");
                    break;
                        /*case R.id.bt_amplification:
                                mSurface.SjrsSurface().NET_DVR_PTZControl_Other(0,1,13,1);
                                System.out.println("结束焦距放大");
                                break;
                        case R.id.bt_shrink:
                                mSurface.SjrsSurface().NET_DVR_PTZControl_Other(0,1,14,1);
                                System.out.println("结束焦距缩小");
                                break;*/
                default:
                    break;
            }
        }
    }
}


