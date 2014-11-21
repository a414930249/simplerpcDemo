import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.demo.ClassLoadUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tianzhiwei on 2014/10/4.
 */
public class SocketHttpServer {

    static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring_bean.xml");
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                Socket socket = server.accept();
                new ServerSocketThread1(socket).run();
                if (socket != null) {
                    if (!socket.isClosed()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
