import API.GetUserInfo;
import API.Login;
import API.Register;
import tools.Utils;

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
            System.out.println("Success connected");
            if(path.equals("login")){new Login(client,paras).execute();}
            else if(path.equals("register")){new Register(client,paras).execute();}
            else if(path.equals("getUserInfo")){new GetUserInfo(client,paras).execute();}
        }
        catch(IOException IOE){
            IOE.printStackTrace();
        }
    }
}
