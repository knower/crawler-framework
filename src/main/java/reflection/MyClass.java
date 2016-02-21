package reflection;

import sun.jvm.hotspot.debugger.dummy.DummyDebugger;
import sun.reflect.generics.scope.DummyScope;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mx on 16/2/21.
 */
public class MyClass {

    public int count;

    public List<String> myList;


    public MyClass(int start) {
        this.count = start;
        this.myList = new ArrayList<String>();
    }

    public void increase(int temp) {
        count += temp;
    }

    public List getList(final List<String> list) {
        list.add("test1");

        return (List) Proxy.newProxyInstance(MyClass.class.getClassLoader(), new Class[]{List.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("add".equals(method.getName())) {
                            throw new UnsupportedOperationException();
                        } else {
                            return method.invoke(list, args);
                        }
                    }
                });
    }
}
