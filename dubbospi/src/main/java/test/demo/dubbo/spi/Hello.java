package test.demo.dubbo.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * Created by tianzhiwei on 2014/10/10.
 */
@SPI
public interface Hello {

    public void say();
}

