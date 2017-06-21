package tools;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 63289 on 2017/6/17.
 */
public class Utils {
    public static String streamToString(InputStream is){
        String str = "";
        String line;
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        try {
            int i = 1;
            while((line = br.readLine())!= null){
                if(line.equals(""))break;
                str += line + "\n";
            }
        }
        catch(IOException IOE){
            IOE.printStackTrace();
        }
        return str;
    }

    public static String getString(String str, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            return matcher.group();
        }
        return "";
    }

    public static String getPath(String str){
        String firstLine = Utils.getString(str,".*?\n");
        String s;
        if(firstLine.contains("?"))
            s = Utils.getString(firstLine,"/.*?\\?");
        else {
            s = Utils.getString(firstLine, "/.*? ");
        }
        if(s.length()>1)s = s.substring(1, s.length() - 1);
        return  s;
    }

    public static String[] getParas(String str){
        String firstLine = Utils.getString(str,".*?\n");
        String s = "";
        if(firstLine.contains("?")) {
            s = Utils.getString(firstLine, "\\?.*? ");
            s = s.substring(1, s.length() - 1);
        }
        return  s.split("&");
    }

    public static String getPara(String index,String[] paras){
        for(String s:paras){
            if(s.startsWith(index))
                return s.substring(index.length()+1);
        }
        return "";
    }

    public static void response(Socket client,String json){
        try {
            OutputStream os = client.getOutputStream();
            os.write("HTTP/1.1 200 OK\r\ncontent-type:application/json\n\n".getBytes());
            os.write(json.getBytes());
            os.flush();
            os.close();
            client.close();
        }catch (IOException IOE){
            IOE.printStackTrace();
        }
    }

    public static char conbineChars(char a,char b,char c){
        int A,B,C;
        if(a<='9')A=a-'0';
        else A=a-'A'+10;
        if(b<='9')B=b-'0';
        else B=b-'A'+10;
        if(c<='9')C=c-'0';
        else C=c-'A'+10;
        int sum=A+B+C;
        if(sum<=9)return (char)(sum+'0');
        else if(sum<36) return (char)(sum-10+'A');
        else return (char)(sum-36+'a');
    }

    public static String shortifyStr(String str){
        String res ="";
        int i = 0;
        for(i = 0;i+2<str.length();i+=3){
            res+=conbineChars(str.charAt(i),str.charAt(i + 1),str.charAt(i + 2));
        }
        if(i==str.length()-2)res+=conbineChars(str.charAt(i - 2), str.charAt(i - 1),'0');
        else if(i==str.length()-1)res+=str.charAt(i - 1);
        return res;
    }

    public static String createPrimaryKey(){
        UUID uuid = UUID.randomUUID();
        String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return shortifyStr(s);
    }

    public static String isToStr(InputStream is){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            bufferedReader.close();
            is.close();
            return buffer.toString();
        }
        catch (IOException IOE){
            IOE.printStackTrace();
        }
        return "";
    }

    public static String setToString(HashSet<String> set){
        StringBuffer str ;
        str.append("[");
        Boolean first = true;
        for(String item:set){
            if(first){
                str.append(item);
                first=false;
            }
            else str.append(item);
        }
        return null;
    }

    public static HashSet<String> stringToSet(String arr){
        arr = arr.substring(1,arr.length()-1);
        HashSet<String> set = new HashSet<String>();
        for(String item:arr.split(","))set.add(item);
        return set;
    }
}
