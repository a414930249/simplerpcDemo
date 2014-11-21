import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketServerSendObject {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(80);
        while (true) {
            Socket socket = server.accept();
            System.out.println("请求来了");
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Object object = objectInputStream.readObject();
            while (object != null && object instanceof Student){
                System.out.println(((Student) object).toString());
                object = objectInputStream.readObject();
            }
            socket.close();
        }
    }
}
