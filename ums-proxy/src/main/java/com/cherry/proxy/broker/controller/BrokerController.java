package com.cherry.proxy.broker.controller;


import com.cherry.api.broker.pojo.BrokerNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class BrokerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/broker/list")
    public List<BrokerNode> getBrokerList(){
        List<ServiceInstance> list = discoveryClient.getInstances("ums-mc");
        return restTemplate.getForObject(list.get(0).getUri().toString()+"/broker/list",List.class);
    }


}