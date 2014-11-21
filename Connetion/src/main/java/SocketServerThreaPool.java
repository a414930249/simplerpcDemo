import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketServerThreaPool {


    public static void main(String[] args) {

        ExecutorService executorService
                = Executors.newFixedThreadPool(2);
        int count = 1;
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                System.out.println(count++);
                Socket socket = server.accept();
                executorService.execute(new ServerSocketThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
