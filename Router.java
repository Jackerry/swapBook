import java.io.*;
import java.net.Socket;

/**
 * Created by 63289 on 2017/6/17.
 */
public class Router extends Thread {
    Socket client;
    Router(Socket client){
        this.client = client;
    }

    String getPath(String str){
        String s = Utils.getString(str,"/.*?\\?");
        s = s.substring(1,s.length()-1);
        return  s;
    }

    String[] getParas(String str){
        String s = Utils.getString(str,"\\?.*? ");
        s = s.substring(1,s.length()-1);
        return  s.split("&");
    }

    String getPara(String index,String[] paras){
        for(String s:paras){
            if(s.startsWith(index))
                return s.substring(index.length()+1);
        }
        return "";
    }

    public void run(){
        String str = "";
        try {
            str = Utils.streamToString(client.getInputStream());
            System.out.println(str);
            String[] s = getParas(str);
            System.out.println(getPara("a",s));

            getPath(str);
            OutputStream os = client.getOutputStream();
            os.write("HTTP/1.1 200 OK\r\n\r\n ".getBytes());
            os.write("hello ".getBytes());
            os.flush();
            os.close();
            client.close();
        }
        catch(IOException IOE){
            IOE.printStackTrace();
        }
    }
}
