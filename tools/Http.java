package tools;

import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 63289 on 2017/6/21.
 */
public class Http {
    public static void httpsGet(String url,CallBack cb){
        new Thread(new HttpsGet(url,cb)).start();
    }
}

class HttpsGet implements Runnable{
    String url;
    CallBack cb;
    HttpsGet(String url,CallBack cb){
        this.url = url;
        this.cb = cb;
    }
    @Override
    public void run(){
        try {
            URL Url = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(2000);
            if(conn.getResponseCode() == 200){
                String s = Utils.isToStr(conn.getInputStream());
                cb.success(JSONObject.fromObject(s));
            }
            else {
                throw new IOException();
            }
        }
        catch (MalformedURLException URLE){
            URLE.printStackTrace();
            cb.failed(JSONObject.fromObject("{state:0,reason:'url error'}"));
        }
        catch (ProtocolException PE){
            PE.printStackTrace();
            cb.failed(JSONObject.fromObject("{state:0,reason:'Protocol error'}"));
        }
        catch (IOException IOE){
            IOE.printStackTrace();
            cb.failed(JSONObject.fromObject("{state:0,reason:'not found'}"));
        }
        catch (Exception e){
            e.printStackTrace();
            cb.failed(JSONObject.fromObject("{state:0,reason:'connect timeout'}"));
        }
        finally{cb.complete();}
    }
}
