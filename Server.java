import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.xml.ws.spi.http.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by 63289 on 2017/6/17.
 */
public class Server {
    static int maxThreadNum =40;
    public static void main(String [] args)
    {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(maxThreadNum);
            while(true){
                Socket socket = serverSocket.accept();
                if(socket.isConnected()) fixedThreadPool.execute(new Router(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
