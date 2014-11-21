import test.demo.dubbo.spi.Hello;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by tianzhiwei on 2014/10/10.
 */
public class TestHello {

    public static void main(String[] args) {
        ServiceLoader<Hello> serviceLoader = ServiceLoader.load(Hello.class);
        Iterator<Hello> hellos = serviceLoader.iterator();
        while (hellos.hasNext()){
            Hello hello = hellos.next();
            hello.say();
        }
    }
}
