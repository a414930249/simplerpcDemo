import test.demo.ClassLoadUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by tianzhiwei on 2014/10/4.
 */
public class TestClassLoadUtils {

    private static String uri = "/test.demo.HelloWorld?method=sayHello&argsType=[String]&argsParam=[Tom]";
    public static void main(String[] args) {
        try {
            File file = new File(".");
            System.out.println(file.getCanonicalPath());
            Class cl = ClassLoadUtils.loadClass(file.getCanonicalPath()+"\\Connetion\\src\\main\\resources","test.demo.HelloWorld");

            if(cl != null){
                Object obj = cl.newInstance();
                System.out.println(obj.toString());
            }


            String className = ClassLoadUtils.parseClassName(uri);

            String method = ClassLoadUtils.parseUriParamByPropertyName(uri,"method");

            String argsType = ClassLoadUtils.parseUriParamByPropertyName(uri,"argsType");
            String argsParam = ClassLoadUtils.parseUriParamByPropertyName(uri,"argsParam");


            System.out.println(className);
            System.out.println(method);
            System.out.println(argsType);
            System.out.println(argsParam);
            System.out.println(argsParam.split(",").length);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
