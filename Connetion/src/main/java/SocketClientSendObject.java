import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketClientSendObject {

    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",80);
            OutputStream ops  = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ops);
            oos.writeObject(new Student("xiaomi",11,11));
            oos.writeObject(new Student("zhangsan", 12, 12));
            oos.writeObject(null);//主要是服务端在读取的时候不知道怎么判断是否还有对象，所以这里只能这么先处理一下，有好的办法的话换掉
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("socket close");
            if(socket != null && !socket.isClosed()){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
