import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.demo.ClassLoadUtils;
import test.demo.Invocation;
import test.demo.RPCProcess;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by tianzhiwei on 2014/10/15.
 */
public class SocketServerRPC {
    static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring_bean.xml");
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        ServerSocket server = new ServerSocket(80);
        while (true) {
            Socket socket = server.accept();
            RPCProcess.processRpc(classPathXmlApplicationContext,socket);
            socket.close();
        }
    }
}
