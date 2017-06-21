package API;

import net.sf.json.JSONObject;
import tools.CallBack;
import tools.Http;
import tools.MySQL;
import tools.Utils;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 63289 on 2017/6/21.
 */
public class GetBookList {
    Socket client;
    String[] paras;
    static String isbnAPI = "https://api.douban.com/v2/book/isbn/";

    public GetBookList(Socket client,String[] paras){
        this.client = client;
        this.paras = paras;
    }

    public void execute(){

    }

    public static void createBookList(String isbn){
        MySQL db = new MySQL();
        ResultSet rs = db.executeQuery("SELECT * FROM booklist WHERE isbn = '" + isbn + "';");
        try{
            if(!rs.next()){
                CallBack cb = new CallBack() {
                    @Override
                    public void success(JSONObject result) {
                        JSONObject json = new JSONObject();
                        json.put("isbn", paras.getString("isbn"));
                        json.put("title", result.getString("title"));
                        json.put("tags", result.getString("tags"));
                        json.put("price", result.getString("price"));
                        json.put("origin_title", result.getString("origin_title"));
                        json.put("author", result.getString("author"));
                        json.put("publisher", result.getString("publisher"));
                        json.put("images", result.getString("images"));
                        MySQL db = new MySQL();
                        db.insert(json, "booklist");
                    }

                    @Override
                    public void failed(JSONObject result) {}

                    @Override
                    public void complete() {}
                };
                cb.paras.put("isbn",isbn);
                Http.httpsGet(isbnAPI+isbn, cb);
            }
        }
        catch (SQLException SQLE){
            SQLE.printStackTrace();
        }
    }
}
