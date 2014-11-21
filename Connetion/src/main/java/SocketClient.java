import sun.misc.IOUtils;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",80);
        try {
            OutputStream ops  = socket.getOutputStream();
            OutputStreamWriter opsw = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opsw);
            for(int i = 0;i<3;i++){
                bw.write("向服务端第一次发送=========="+i+",");
                bw.flush();
            }
            Thread.sleep(1000L);
            for(int i = 0;i<3;i++){
                bw.write("向服务端第二次发送=========="+i+",");
                bw.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("socket close");
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        }

    }


//    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("127.0.0.1", 80);
//        // 向服务端程序发送数据
//        OutputStream ops  = socket.getOutputStream();
//        OutputStreamWriter opsw = new OutputStreamWriter(ops);
//        BufferedWriter bw = new BufferedWriter(opsw);
//        for(int i = 0;i<3;i++){
//            bw.write("向服务端第一次发送=========="+i+",");
//            bw.flush();
//        }
//        bw.flush();
//
//
//        // 从服务端程序接收数据
//        InputStream ips = socket.getInputStream();
//        InputStreamReader ipsr = new InputStreamReader(ips);
//        BufferedReader br = new BufferedReader(ipsr);
//        String s = "";
//        while((s = br.readLine()) != null)
//            System.out.println(s);
//
//        for(int i = 0;i<3;i++){
//            bw.write("向服务端第一次发送=========="+i+",");
//            bw.flush();
//        }
//        bw.flush();
//
//        socket.close();
//    }
}
