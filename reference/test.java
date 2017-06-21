package reference;

import API.GetBookList;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.CallBack;
import tools.Http;
import tools.MySQL;
import tools.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by 63289 on 2017/6/20.
 */
public class test {
    public static void main(String[] agvs){
        HashSet<String> set = new HashSet<String>();
        set.add("asdas");
        set.add("asdasdfgf");
        System.out.println(set);
    }
}
