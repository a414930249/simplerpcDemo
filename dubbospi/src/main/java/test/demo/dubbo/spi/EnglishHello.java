package test.demo.dubbo.spi;

/**
 * Created by tianzhiwei on 2014/10/10.
 */
public class EnglishHello implements Hello {


    @Override
    public void say() {
        System.out.println("Hello!");
    }
}
