package test.demo;

/**
 * Created by tianzhiwei on 2014/10/4.
 */
public class HelloWorld implements IHelloWorld{
    @Override
    public String sayHello(){
        return "Hello!";
    }
    @Override
    public String sayHello(String name){
        return "Hello "+name;
    }
}
