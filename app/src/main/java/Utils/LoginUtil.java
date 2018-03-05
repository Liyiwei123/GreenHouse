package Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import user.User;

/**
 * Created by Administrator on 2018/2/17.
 */

public class LoginUtil {
    /**
     * 获取当前用户
     * @return
     */

    public static User getCurrentUser(Context context) {
        //获取一个SharePreferenced对象
        SharedPreferences sp =context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if(sp.getString("password",null)!=null){
            //编程json对象
        JSONObject json = JSONObject.parseObject(sp.getString("jsonstr",null));
             return JSON.toJavaObject(json,User.class);}
             return null;
    }
    /**
     * 设置当前用户
     */
    public  static void setCurrentUser(Context context,User user){
            String jsonstr= JSONObject.toJSONString(user);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", user.getUserName());
        editor.putString("password",user.getPassword());
        editor.putInt("id",user.getId());
        editor.putString("jsonstr",jsonstr);
        editor.commit();
    }

    /**
     * 注销当前用户
     * @param context
     */
    public static void clearData(Context context) {
        SharedPreferences sp= context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
