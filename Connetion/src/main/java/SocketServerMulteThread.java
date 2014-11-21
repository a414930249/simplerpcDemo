import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketServerMulteThread {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                Socket socket = server.accept();
                new ServerSocketThread(socket).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
