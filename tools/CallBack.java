package tools;
import net.sf.json.JSONObject;

/**
 * Created by 63289 on 2017/6/21.
 */
public interface CallBack {
    public JSONObject paras = new JSONObject();
    void success(JSONObject result);
    void failed(JSONObject result);
    void complete();
}

