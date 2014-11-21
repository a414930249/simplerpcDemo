import org.apache.commons.io.IOUtils;
import test.demo.ClassLoadUtils;
import test.demo.RPCProcess;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.List;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class ServerSocketThread1 implements Runnable {
    private Socket socket;
    public ServerSocketThread1(Socket socket) {
        this.socket = socket;
    }
    int size = 1024;
    public void run() {

        try {
            RPCProcess.processRequest(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
