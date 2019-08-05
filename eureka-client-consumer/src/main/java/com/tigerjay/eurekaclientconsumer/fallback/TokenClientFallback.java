package com.tigerjay.eurekaclientconsumer.fallback;

import com.tigerjay.eurekaclientconsumer.interfaces.TokenClient;


public class TokenClientFallback implements TokenClient {
    @Override
    public String getToken() {
        return "失败了";
    }
}
