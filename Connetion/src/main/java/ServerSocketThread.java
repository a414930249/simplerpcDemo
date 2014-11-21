import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.List;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class ServerSocketThread implements Runnable {
    private Socket socket;
    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }
    int size = 1024;
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine;
            while ((readLine = br.readLine()) != null && readLine.length()>0){
                System.out.println(readLine);
            }

            // 向客户端端程序发送数据
            bufferedWriter.write("hello world" + this.hashCode() + "");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

}
