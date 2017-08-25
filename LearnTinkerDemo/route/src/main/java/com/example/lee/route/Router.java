package com.example.lee.route;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Lee on 2017/8/25.
 */

public class Router {
    private Interceptor interceptor;

    public Router(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public static final class Builder {
        private Interceptor interceptor;

        public Builder interceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public Router build() {
            return new Router(interceptor);
        }
    }


    public <T> T create(final Class<T> service, final Context context) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        IntentWrapper intentWrapper = loadInterwrapper(context, method, args);
                        Class returnType = method.getReturnType();
                        if (returnType == void.class) {
                            if (interceptor == null || !interceptor.intercept(intentWrapper)) {
                                intentWrapper.start();
                                return null;
                            }
                        } else if (returnType == IntentWrapper.class) {
                            return intentWrapper;
                        }
                        throw new RuntimeException("method return type only support 'void' or 'interwrapper' !");
                    }


                });
    }

    private IntentWrapper loadInterwrapper(Context context, Method method, Object[] args) {
        return new IntentWrapper.Builder(context, method, args).build();
    }


}

