package test.demo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by tianzhiwei on 2014/10/15.
 */
public class Invocation implements Serializable{
    public Class interfaceClass;
    public String methodName;
    public Class[] paramTypes;

    public Object[] paramValues;

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}
