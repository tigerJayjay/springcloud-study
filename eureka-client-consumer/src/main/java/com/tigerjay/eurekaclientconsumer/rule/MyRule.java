package com.tigerjay.eurekaclientconsumer.rule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class MyRule implements IRule {
    ILoadBalancer lb;
    @Override
    public Server choose(Object o) {
        List<Server> serverList = lb.getAllServers();
        System.out.println("myRule");
        return serverList.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
