package com.azp.phttp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BProxy {

    public <T> T create(Class<T> service) {
        Utils.validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return 8;
            }
        });
    }
}
