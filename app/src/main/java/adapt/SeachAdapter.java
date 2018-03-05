package adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.myapplication.R;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

import user.Account;
import user.Message;

/**
 * Created by Administrator on 2018/2/5.
 */

public class SeachAdapter extends BaseAdapter {
    private List<Message>list;
    private LayoutInflater inflater;

    public SeachAdapter(Context context, List<Message>list) {
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null)
        {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //取出list中的JSONObject对象转换为javabean
        JSON jsonString=(JSON)JSONObject.toJSON(this.getItem(position));
        Message acc=JSON.toJavaObject(jsonString,Message.class);

        System.out.println("看看ACC收到没"+acc.toString());
        //初始化组件控制器
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            //加载该布局对象并套上一层外部容器 root,
            convertView = inflater.inflate(R.layout.listitem, null);
            //convertView = inflater.inflate(R.layout.list_tag, null);
            viewHolder.itemId = (TextView) convertView.findViewById(R.id.text_id);
            viewHolder.itemin = (TextView) convertView.findViewById(R.id.text_goods_name);
            viewHolder.itemiw = (TextView) convertView.findViewById(R.id.text_codeBar);
            viewHolder.itemout = (TextView) convertView.findViewById(R.id.text_num);

            //viewHolder.yn = (TextView) convertView.findViewById(R.id.text_money);
            //为view创建一个设置一个额外的数据
            convertView.setTag(viewHolder);     }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //大坑integer参数,需要调用toString方法变为字符串
        viewHolder.itemId.setText(acc.getId().toString());

        viewHolder.itemin.setText(Float.toString(acc.getTemp()));
        viewHolder.itemiw.setText(Float.toString(acc.getTempout()));
        viewHolder.itemout.setText(acc.getTime());
        return convertView;
    }
    public static class ViewHolder{
        public TextView itemId;
        public TextView itemin;
        public TextView itemiw;
        public TextView itemout;

         }
}


