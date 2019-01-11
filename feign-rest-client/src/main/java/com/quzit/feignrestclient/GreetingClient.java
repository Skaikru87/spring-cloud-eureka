package com.quzit.feignrestclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("rest-client-api")
public interface GreetingClient {
    @RequestMapping("/greeting")
    String greating();
}
