package test.demo;

import org.springframework.context.ApplicationContext;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

/**
 * Created by tianzhiwei on 2014/10/15.
 */
public class RPCProcess {

    public static void processRequest(Socket socket) throws IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine;
            while ((readLine = br.readLine()) != null && readLine.length() > 0) {
                if (readLine.indexOf("GET") != -1 && readLine.indexOf("favicon.ico") == -1) {
                    String[] requestLine = readLine.split(" ");
                    if (requestLine.length == 3) {
                        String uri = requestLine[1];
                        String methodName = ClassLoadUtils.parseUriParamByPropertyName(uri, "method");
                        String argsType = ClassLoadUtils.parseUriParamByPropertyName(uri, "argsType");
                        String argsParam = ClassLoadUtils.parseUriParamByPropertyName(uri, "argsParam");
                        String className = ClassLoadUtils.parseClassName(uri);
                        if (className != null && className.trim().length() > 0) {
                            Class cl = ClassLoadUtils.loadClass(className);
                            if (cl != null && methodName != null && methodName.length() > 0) {
                                try {
                                    Class[] types = ClassLoadUtils.parseArgs(argsType);
                                    Method method = ClassLoadUtils.getMethod(cl, types, methodName);
                                    if (method != null) {
                                        Object object = method.invoke(cl.newInstance(),
                                                ClassLoadUtils.parseArgs(argsType, argsParam));
                                        // 向客户端端程序发送数据
                                        if (object != null)
                                            bufferedWriter.write(object.toString());
                                        bufferedWriter.flush();
                                    }
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                System.out.println(readLine);
            }
            System.out.println("");
            // 向客户端端程序发送数据
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processRpc(ApplicationContext applicationContext,Socket socket) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("请求来了");
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        while (object != null && object instanceof Invocation){
            Invocation invocation = (Invocation) object;
            Map<String,Object> beans = applicationContext.getBeansOfType(invocation.getInterfaceClass());
            Collection<Object> obs = beans.values();
            Object obj = obs.iterator().next();
            Method method = null;
            if(invocation.getParamTypes() != null && invocation.getParamTypes().length>0){
                method = obj.getClass().getMethod(invocation.getMethodName(),invocation.getParamTypes());
            }else {
                method = obj.getClass().getMethod(invocation.getMethodName());
            }
            Object result = method.invoke(obj,invocation.getParamValues());
            System.out.println((result).toString());
            // 向客户端端程序发送数据
            if (object != null)
                bufferedWriter.write(result.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            object = objectInputStream.readObject();
        }
        bufferedWriter.close();
    }
}
