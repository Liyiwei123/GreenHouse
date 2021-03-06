package adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

import user.Goods;

/**
 * Created by Administrator on 2018/1/30.
 */

public class TableAdapter extends BaseAdapter {
    private List<Goods> list;
    private LayoutInflater inflater;
    public TableAdapter(Context context, List<Goods> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        @Override  public Object getItem(int position) {     return list.get(position);   }
        @Override  public long getItemId(int position) {     return position;   }

        @Override  public View getView(int position, View convertView, ViewGroup parent) {

            Goods goods = (Goods) this.getItem(position);
            ViewHolder viewHolder;
            if(convertView == null){
            viewHolder = new ViewHolder();
            //加载该布局对象并套上一层外部容器 root,
            convertView = inflater.inflate(R.layout.list_tag, null);
            viewHolder.goodId = (TextView) convertView.findViewById(R.id.text_id);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.text_goods_name);
            viewHolder.goodCodeBar = (TextView) convertView.findViewById(R.id.text_codeBar);
            viewHolder.goodNum = (TextView) convertView.findViewById(R.id.text_num);
            viewHolder.goodCurrPrice = (TextView) convertView.findViewById(R.id.text_curPrice);
            viewHolder.goodMoney = (TextView) convertView.findViewById(R.id.text_money);
            //为view创建一个设置一个额外的数据
            convertView.setTag(viewHolder);     }
            else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.goodId.setText(goods.getId());
        viewHolder.goodId.setTextSize(13);     viewHolder.goodName.setText(goods.getGoodsName());
        viewHolder.goodName.setTextSize(13);     viewHolder.goodCodeBar.setText(goods.getCodeBar());
        viewHolder.goodCodeBar.setTextSize(13);     viewHolder.goodNum.setText(goods.getNum()+"");
        viewHolder.goodNum.setTextSize(13);     viewHolder.goodCurrPrice.setText(goods.getCurPrice()+"");
        viewHolder.goodCurrPrice.setTextSize(13);     viewHolder.goodMoney.setText(goods.getMoney()+"");
        viewHolder.goodMoney.setTextSize(13);

        return convertView;   }


        public static class ViewHolder{
        public TextView goodId;
        public TextView goodName;
        public TextView goodCodeBar;
        public TextView goodNum;
        public TextView goodCurrPrice;
        public TextView goodMoney;   }
}
