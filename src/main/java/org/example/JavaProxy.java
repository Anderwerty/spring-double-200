package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//cglib
public class JavaProxy {
    public static void main(String[] args) {
        MyInterface myClass = new MyClass();
        TransactionManager transactionManager = new TransactionManager() {
            @Override
            public void openTransaction() {
                System.out.println("Open transaction");
            }

            @Override
            public void rollBack() {
                System.out.println("rollback ");
            }

            @Override
            public void commit() {
                System.out.println("commit");
            }
        };

        MyInterface myClassProxy = (MyInterface) Proxy.newProxyInstance(myClass.getClass().getClassLoader(),
                myClass.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] arguments) -> {
                    if (method.isAnnotationPresent(Transactional.class)) {
                        try {
                            transactionManager.openTransaction();
                            Object result = method.invoke(myClass, arguments);
                            transactionManager.commit();
                            return result;
                        } catch (Exception e) {
                            transactionManager.rollBack();
                            throw new RuntimeException(e);
                        }
                    }
                    return method.invoke(myClass, arguments);
                });
        System.out.println(myClassProxy.print(5));

    }
}

interface MyInterface {
    @Transactional
    String print(int times);
}

class MyClass implements MyInterface {

    @Override
    public String print(int times) {
        return "*".repeat(times);
    }
}

class MyStaticProxyMyClass implements MyInterface {
    private final MyInterface myInterface;
    private final TransactionManager transactionManager;

    MyStaticProxyMyClass(MyInterface myInterface,
                         TransactionManager transactionManager) {
        this.myInterface = myInterface;
        this.transactionManager = transactionManager;
    }

    @Override
    public String print(int times) {
        try {
            transactionManager.openTransaction();
            String result = myInterface.print(times);
            transactionManager.commit();
            return result;

        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException();
        }
    }
}

interface TransactionManager {
    void openTransaction();

    void rollBack();

    void commit();


}
