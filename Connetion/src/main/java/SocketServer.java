import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                System.out.println("请求来了");
                Socket socket = server.accept();
                InputStream inputStream = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                while ((readLine = br.readLine()) != null){
                    System.out.println(readLine);
                }
                try {
                    Thread.sleep(100000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PrintWriter os=new PrintWriter(socket.getOutputStream());
                os.write("Hello");
                os.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
