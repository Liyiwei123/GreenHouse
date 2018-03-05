package Utils;

/**
 * Created by Administrator on 2018/1/28.
 */

public interface HttpCallbackListener {
    void onFinish(Object response);

    void onError(Exception e);
}
