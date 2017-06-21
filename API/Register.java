package API;

import net.sf.json.JSONObject;
import tools.MySQL;
import tools.Utils;

import java.net.Socket;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 63289 on 2017/6/20.
 */
public class Register {
    Socket client;
    String[] paras;
    public Register(Socket client,String[] paras){
        this.client = client;
        this.paras = paras;
    }
    public void execute() {
        String account = Utils.getPara("account", paras);
        String password = Utils.getPara("password",paras);
        String email = Utils.getPara("email",paras);
        String phone = Utils.getPara("phone",paras);
        String nickName = Utils.getPara("nickName",paras);
        String objectId = Utils.createPrimaryKey();
        MySQL db = new MySQL();
        String sql = "";
        if (!account.equals("")&&!password.equals("")&&!email.equals("")&&!phone.equals("")){
            sql = "INSERT INTO `user` (`objectId`,`nickName`, `password`, `account`, `email`, `phone`) VALUES ('"+objectId+"', '"+nickName+"','"+password+"', '"+account+"', '"+email+"', '"+phone+"');";
        }
        if(sql.equals("")) {
            Utils.response(client,"{'state':0,'reason':'bad request'}");
        }
        else {
            boolean success = false;
            db.execute(sql);
            JSONObject userInfo = GetUserInfo.getInfo(objectId);
            if(userInfo!=null){
                String userId = userInfo.get("objectId").toString();
                if(userId.equals(objectId))success=true;
            }
            if(success)Utils.response(client,"{'state':1,'userInfo':'"+userInfo.toString()+"'}");
            else Utils.response(client,"{'state':0,'reason':'register failed'}");
        }
    }
}
