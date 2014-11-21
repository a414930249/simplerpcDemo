import com.alibaba.dubbo.common.extension.ExtensionLoader;
import test.demo.dubbo.spi.Hello;

/**
 * Created by tianzhiwei on 2014/10/10.
 */
public class TestDubboSPI {

    public static void main(String[] args) {
        ExtensionLoader<Hello> extensionLoader = ExtensionLoader.getExtensionLoader(Hello.class);

        Hello hello = extensionLoader.getExtension("english");
        hello.say();
    }
}
