package com.tigerjay.eurekaclientconsumer.rule;

import com.netflix.loadbalancer.AbstractServerListFilter;

import java.util.List;

public class ServerListFilterTest extends AbstractServerListFilter {
    @Override
    public List getFilteredListOfServers(List list) {
        list.remove(0);
        return list;
    }
}
