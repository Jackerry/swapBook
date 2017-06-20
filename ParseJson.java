package baz.parse;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ParseJson {

    //将json字符串转换为java对象
    public void JSON2Object(){
        //接收{}对象，此处接收数组对象会有异常
        //JSONObject obj = new JSONObject().fromObject(jsonStr);//将json字符串转换为json对象

    }



    public static void main(String[] argv){
        String jsonStr = "{\"address\":[\"a\",\"b\"],\"age\":\"23\",\"name\":\"JSON\"}";
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        System.out.println(jsonObject.get("address2"));

        int[] a = {1,2,3,4};
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 1);
        jsonObj.put("name","张勇");
        jsonObj.put("sex","男");
        jsonObj.put("age",a);
        String str = jsonObj.toString();//将json对象转换为
        System.out.println(str);
    }

}