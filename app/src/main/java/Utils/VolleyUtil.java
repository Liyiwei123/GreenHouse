package Utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.myapplication.servicep.MainMessageActivity;
import com.example.administrator.myapplication.servicep.NotificationService;


import user.Message;

/**
 * Created by Administrator on 2018/2/23.
 */

public class VolleyUtil {
   public StringRequest init(String url, final Context context){
       StringRequest stringRequest = new StringRequest(url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.d("TAG", response);
//                       Message message = (Message)JSONObject.parse(response);
//                       Log.d("TAG",message.toString());
                      Message message= JSON.toJavaObject(JSONObject.parseObject(response),Message.class);
                       Log.d("TAG",message.toString());
                       if (message.getTemp()>30||message.getTemp()<10){
                           Intent intent = new Intent(context,NotificationService.class);
                           ContextWrapper contextWrapper=new ContextWrapper(context);
                           contextWrapper.startService(intent);

                       }
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("TAG", error.getMessage(), error);
           }
       });

       return  stringRequest;
   }
}
