package API;

import java.net.Socket;

/**
 * Created by 63289 on 2017/6/20.
 */
public class Login {
    Socket socket;
    String[] paras;
    public Login(Socket socket,String[] paras){
        this.socket = socket;
        this.paras = paras;
    }
    public void execute(){

    }
}
