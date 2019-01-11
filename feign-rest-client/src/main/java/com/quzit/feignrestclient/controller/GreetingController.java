package com.quzit.feignrestclient.controller;

import com.quzit.feignrestclient.GreetingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    GreetingClient greetingClient;

    @GetMapping("/get-greeting")
    public String getGreeting() {
        return "siemka from: " + greetingClient.greating();
    }
}
