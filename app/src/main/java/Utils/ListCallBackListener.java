package Utils;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import adapt.TableAdapter;
import user.Account;
import user.Goods;

/**
 * Created by Administrator on 2018/2/5.
 */

public class ListCallBackListener implements HttpCallbackListener {
    private List<Account>list;

    public List<Account> getList() {
        return list;
    }

    @Override
    public void onFinish(Object response) {
        System.out.println(response.toString().getClass());

        Account shopInfo = JSON.parseObject((String) response, Account.class);
        list=new ArrayList<Account>();
        list.add(shopInfo);
        System.out.println(shopInfo);

    }

    @Override
    public void onError(Exception e) {

    }
}
