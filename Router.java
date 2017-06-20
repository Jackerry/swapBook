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

    public void run(){
        String str = "";
        try {
            str = Utils.streamToString(client.getInputStream());
            String path = Utils.getPath(str);
            String[] paras = Utils.getParas(str);
            if(path == "login"){new API.Login(client,paras).execute();}

        }
        catch(IOException IOE){
            IOE.printStackTrace();
        }
    }
}
