package reflection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mx on 16/2/21.
 */
public class TestMyClass {
    public static void main(String[] args) throws Exception {
//        MyClass myClass = new MyClass(0); //一般做法
//        myClass.increase(2);
//        System.out.println("Normal -> " + myClass.count);

        /**
         * 反射 实例化
         */
        Constructor<MyClass> constructor = MyClass.class.getConstructor(int.class);
//        //创建对象
        MyClass myclassReflection = constructor.newInstance(10);
//
//        //创建方法(获取方法)
//        Method method = MyClass.class.getMethod("increase", int.class);
//
//        method.invoke(myclassReflection, 5); //调用方法
//
//        //获取域
//        Field field = MyClass.class.getField("count");
//
//        System.out.println("reflection --> " + field.getInt(myclassReflection));


        /**
         * Array 实例化
         */
//        Object object = Array.newInstance(String.class, 10);
//        Array.set(object, 0, "Hello");
//        Array.set(object, 1, "World");
//
//        System.out.println(Array.get(object, 1));


        /**
         *
         */
//        Field field = MyClass.class.getDeclaredField("myList");
//        Type type = field.getGenericType();
//        if (type instanceof ParameterizedType) {
//            ParameterizedType parameterizedType = (ParameterizedType) type;
//
//            Type[] actualTypes = parameterizedType.getActualTypeArguments();
//            for (Type aType : actualTypes) {
//                if (aType instanceof Class) {
//                    Class clazz = (Class) aType;
//                    System.out.println(clazz.getName());
//                }
//            }
//        }

        Field fieldList = MyClass.class.getField("myList");
        List<String> listStr = (List<String>) fieldList.get(myclassReflection);

//        List<String> listTest = new ArrayList<String>();

        Method methodList = MyClass.class.getMethod("getList", List.class);
        listStr = (List<String>) methodList.invoke(myclassReflection, listStr);

        System.out.println(listStr);

//        listStr.add("test2");

    }

}
