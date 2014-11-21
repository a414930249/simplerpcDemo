package test.demo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tianzhiwei on 2014/10/4.
 */
public class ClassLoadUtils {

    public static Class loadClass(String path,String className) throws IOException {
        Class cl = null;

        URLClassLoader loader = null;
        URLStreamHandler streamHandler = null;
        File classPath = new File(path);

        String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();

        URL[] urls = new URL[1];
        urls[0] = new URL(null,repository,streamHandler);
        loader = new URLClassLoader(urls);
        try {
            cl = loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cl;
    }
    public static String parseClassName(String uri){
        String className = "";
        if(uri == null || uri.trim().length()<1){
            return className;
        }
        int index = uri.indexOf("?");
        uri = uri.substring(1);
        if(index != -1){
            className = uri.substring(0,index-1);
        }else{
            className = uri;
        }
        return className;
    }
    public static String parseUriParamByPropertyName(String uri,String propertyName){

        String propertyValue = "";
        if(uri == null || uri.trim().length()<1){
            return null;
        }
        Map<String,String> params = parseRequestParam(uri);
        if(params != null){
            propertyValue = params.get(propertyName);
        }
        return propertyValue;
    }

    public static Class[] parseArgs(String argsType){
        if(argsType == null || argsType.trim().length()<1){
            return null;
        }
        if(argsType != null){
            if(argsType.indexOf("[") != -1){
                int index = argsType.indexOf("[");
                argsType = argsType.substring(index+1);
            }

            if(argsType.indexOf("]") != -1){
                int index = argsType.indexOf("]");
                argsType = argsType.substring(0,index);
            }

        }

        String[] tempType = argsType.split(",");
        Class[] classes = new Class[tempType.length];
        for(int i = 0;i<tempType.length;i++){
            Class cl=null;
            String argType = tempType[i];
            if(argType != null && argType.equals("String")){
                cl = String.class;
            }
            classes[i] = cl;
        }
        return classes;
    }

    public static Object[] parseArgs(String argsType,String args){
        if(argsType == null || argsType.trim().length()<1){
            return null;
        }
        if(argsType != null){
            if(argsType.indexOf("[") != -1){
                int index = argsType.indexOf("[");
                argsType = argsType.substring(index+1);
            }

            if(argsType.indexOf("]") != -1){
                int index = argsType.indexOf("]");
                argsType = argsType.substring(0,index);
            }

        }
        if(args != null){
            if(args.indexOf("[") != -1){
                int index = args.indexOf("[");
                args = args.substring(index+1);
            }

            if(args.indexOf("]") != -1){
                int index = args.indexOf("]");
                args = args.substring(0,index);
            }
        }
        String[] tempType = argsType.split(",");
        String[] tempArgs = args.split(",");
        Object[] objects = new Object[tempType.length];
        for(int i = 0;i<tempType.length;i++){
            String argType = tempType[i];
            if(argType != null && argType.equals("String")){
                if(tempArgs.length>=i) {
                    objects[i] = tempArgs[i];
                }else{
                    objects[i] = null;
                }
            }
        }
        return objects;
    }
    public static Map<String ,String> parseRequestParam(String uri){
        Map<String ,String> params = null;
        if(uri == null || uri.trim().length()<1){
            return params;
        }
        int index = uri.indexOf("?");
        if(index != -1){
            String paramStr = uri.substring(index+1);
            if(paramStr != null && paramStr.length()>0){
                int and = paramStr.indexOf("&");
                if(and != -1){
                    String[] keyValues = paramStr.split("&");
                    if(keyValues != null && keyValues.length>0){
                        params = new HashMap<String,String>();
                        for(String keyValue : keyValues){
                            if(keyValue != null && keyValue.trim().length()>0){
                                int equal = keyValue.indexOf("=");
                                if(equal != -1){
                                    String[] temp = keyValue.split("=");
                                    if(temp != null && temp.length>0){
                                        String key= temp[0];
                                        String value = null;
                                        if(temp.length == 2){
                                            value = temp[1];
                                        }
                                        params.put(key,value);
                                    }
                                }
                            }
                        }

                    }
                }else{
                    int equal = paramStr.indexOf("=");
                    if(equal != -1){
                        String[] temp = paramStr.split("=");
                        if(temp != null && temp.length>0){
                            params = new HashMap<String, String>();
                            String key= temp[0];
                            String value = null;
                            if(temp.length == 2){
                                value = temp[1];
                            }
                            params.put(key,value);
                        }
                    }


                }
            }

        }
        return params;
    }

    public static Class loadClass(String className){
        File file = new File(".");
        Class cl =  null;
        try {
            cl = ClassLoadUtils.loadClass(file.getCanonicalPath()+"\\Connetion\\src\\main\\resources",className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cl;
    }
    public static Method getMethod(Class object,Class[] types,String methodName){
        if(object == null){
            return null;
        }

        Method method = null;
        if(types == null){
            try {
                method = object.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else{
            try {
                method = object.getMethod(methodName,types);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }
}
