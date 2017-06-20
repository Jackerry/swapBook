import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 63289 on 2017/6/17.
 */
public class Utils {
    static String streamToString(InputStream is){
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

    static String getString(String str, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            return matcher.group();
        }
        return "";
    }

    static String getPath(String str){
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

    static String[] getParas(String str){
        String firstLine = Utils.getString(str,".*?\n");
        String s = "";
        if(firstLine.contains("?")) {
            s = Utils.getString(firstLine, "\\?.*? ");
            s = s.substring(1, s.length() - 1);
        }
        return  s.split("&");
    }

    static String getPara(String index,String[] paras){
        for(String s:paras){
            if(s.startsWith(index))
                return s.substring(index.length()+1);
        }
        return "";
    }
}
