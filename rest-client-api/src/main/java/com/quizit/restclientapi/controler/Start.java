package com.quizit.restclientapi.controler;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Start {

    private int counter = 0;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private Environment environment;

    @GetMapping("/hello/{applicationName}")
    public String hello(@PathVariable String applicationName) {

        return "siemka "+ this.eurekaClient.getApplication(applicationName) ;
    }


    @GetMapping("/{applicationName}/instances")
    public List<InstanceInfo> getApplicationInstances(@PathVariable String applicationName){
        log.info("someone wants to know all instances");
        return this.eurekaClient.getApplication(applicationName).getInstances();
    }

    @GetMapping("/instance_id")
    public String hello(){
        counter=+1;
        log.info("XXXXXXXX============== someone wants to know instance id coubter: {}", counter);
        return "hello from "+ environment.getProperty("eureka.instance.instance-id");
    }

}
