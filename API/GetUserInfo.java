package API;

import net.sf.json.JSONObject;
import tools.MySQL;
import tools.Utils;

import javax.rmi.CORBA.Util;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by 63289 on 2017/6/20.
 */
public class GetUserInfo {
    Socket client;
    String[] paras;
    public GetUserInfo(Socket client,String[] paras){
        this.client = client;
        this.paras = paras;
    }

    public static JSONObject getInfo(String objectId){
        MySQL db = new MySQL();
        String sql = "SELECT * FROM `user` WHERE objectId = '" + objectId + "';";
        ResultSet rs = db.executeQuery(sql);
        JSONObject jsonObj = new JSONObject();
        try {
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String col_name = rsmd.getColumnName(i + 1);
                    Object value = rs.getObject(col_name);
                    jsonObj.put(col_name, value.toString());
                }
            }
        }
        catch (SQLException SQLE){
            SQLE.printStackTrace();
        }
        return jsonObj;
    }

    public void execute() {
        String userId = Utils.getPara("userId",paras);
        JSONObject jsonObj = getInfo(userId);
        Utils.response(client, jsonObj.toString());
    }
}
