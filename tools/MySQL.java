package tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.Iterator;

/**
 * Created by 63289 on 2017/6/20.
 */
public class MySQL {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swapBook?characterEncoding=utf8&useSSL=false";
    static final String USER = "root";
    static final String PASS = "h3849893";
    Connection conn = null;
    Statement stmt = null;

    public MySQL(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql){
        try{
            return stmt.executeQuery(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void execute(String sql){
        try{
            stmt.execute(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        }// 什么都不做
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void insert(JSONObject json,String table){
        String sql = "INSERT INTO `" + table + "` ";
        String keys = "(";
        String values = "(";
        boolean first = true;
        Iterator it = json.keys();
        while(it.hasNext()){
            String key = (String)it.next();
            String value = json.getString(key);
            if(first){
                keys += "`" + key + "`";
                values += "'" + value + "'";
                first =false;
            }
            else {
                keys += ", `" + key + "`";
                values += ", '" + value + "'";
            }
        }
        keys += ")";
        values += ")";
        sql = sql + keys + " VALUES " + values + ";";
        execute(sql);
    }

    public JSONArray select(JSONObject json,String table){

        return null;
    }
}
