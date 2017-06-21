package API;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;
import tools.MySQL;
import tools.Utils;

/**
 * Created by 63289 on 2017/6/20.
 */
public class Login {
    Socket client;
    String[] paras;
    public Login(Socket client,String[] paras){
        this.client = client;
        this.paras = paras;
    }
    public void execute() {
        String wxOpenId = Utils.getPara("wxOpenId",paras);
        String account = Utils.getPara("account",paras);
        String password = Utils.getPara("password",paras);
        MySQL db = new MySQL();
        String sql = "";
        if (!wxOpenId.equals("")){
            sql = "SELECT objectId FROM `user` WHERE wxOpenId = '" + wxOpenId + "';";
        }
        else if(!account.equals("")&&!password.equals("")){
            sql = "SELECT objectId FROM `user` WHERE account = '" + account + "' and password = '" + password + "';";
        }
        if(sql.equals("")) {
            Utils.response(client,"{'state':0,'reason':'bad request'}");
        }
        else {
            System.out.println(sql);
            ResultSet rs = db.executeQuery(sql);
            try {
                String objectId = "";
                while (rs.next()) {
                    objectId = rs.getString("objectId");
                }
                JSONObject jsonObj = new JSONObject();
                if(!objectId.equals("")) {
                    jsonObj.put("objectId", objectId);
                    jsonObj.put("state","1");
                }
                else{
                    jsonObj.put("state","0");
                    jsonObj.put("reason","login certification error");
                }
                Utils.response(client,jsonObj.toString());
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            }
        }
    }
}
