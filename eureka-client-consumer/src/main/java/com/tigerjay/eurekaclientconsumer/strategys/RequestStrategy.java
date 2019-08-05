package com.tigerjay.eurekaclientconsumer.strategys;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

/**
 * 自定义策略，解决hystrix多线程调用情况下，request无法使用RequestContextHolder传递的问题
 */
@Component
public class RequestStrategy extends HystrixConcurrencyStrategy {
    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        RequestAttributes requestAttributes  = RequestContextHolder.getRequestAttributes();
        return new HystrixCallable(requestAttributes,callable);
    }

    class HystrixCallable<T> implements  Callable<T>{
        private Callable<T> handler;
        private RequestAttributes requestAttributes;
        public HystrixCallable(RequestAttributes requestAttributes,Callable<T> handler){
            this.requestAttributes = requestAttributes;
            this.handler = handler;
        }
        @Override
        public T call() throws Exception {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            return   handler.call();
        }
    }
}
