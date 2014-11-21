import test.demo.HelloWorld;
import test.demo.IHelloWorld;
import test.demo.Invocation;

import java.io.*;
import java.net.Socket;

/**
 * Created by tianzhiwei on 2014/10/15.
 */
public class SocketClientRPC {

    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",80);

            OutputStream ops  = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ops);
            Invocation invocation = new Invocation();
            invocation.setInterfaceClass(IHelloWorld.class);
            invocation.setMethodName("sayHello");
            invocation.setParamTypes(new Class[]{String.class});
            invocation.setParamValues(new Object[]{"Tom"});
            oos.writeObject(invocation);

            invocation = new Invocation();
            invocation.setInterfaceClass(IHelloWorld.class);
            invocation.setMethodName("sayHello");
            oos.writeObject(invocation);
            oos.writeObject(null);//主要是服务端在读取的时候不知道怎么判断是否还有对象，所以这里只能这么先处理一下，有好的办法的话换掉
            oos.flush();

            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine;
            while ((readLine = br.readLine()) != null){
                System.out.println(readLine);
            }

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
