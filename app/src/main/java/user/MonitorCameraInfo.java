package user;

/**
 * Created by Administrator on 2018/2/2.
 */


public class MonitorCameraInfo {
    public MonitorCameraInfo() {}
    public String serverip = "";
    public int serverport = 0;
    public String username = "";
    public String userpwd = "";
    public int channel = 0;
    public String describe = "";

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public int getServerport() {
        return serverport;
    }

    public void setServerport(int serverport) {
        this.serverport = serverport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    /**
     * 登录帐号id
     */
    public int userId = 0;
    /**
     * 播放返回值
     */
    public int playNum = 0;
    /**
     * 抓图存放路劲
     */
//    public String filepath = getSDRootPath() + "/HCNetSDK";



}