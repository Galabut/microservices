package com.ogasimov.labs.springcloud.microservices.guest;

import com.netflix.ribbon.proxy.annotation.Hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


//TODO check of Hystrix commands
@RestController
@EnableHystrix
public class GuestController {

    @Autowired
    private DinnerService dinnerService;

    @PostMapping("/dinner")
    public Integer startDinner(@RequestBody List<Integer> menuItems) throws Exception {
        return dinnerService.startDinner(menuItems);
    }


    @DeleteMapping("/dinner/{tableId}")
    public void finishDinner(@PathVariable Integer tableId) throws Exception {
        dinnerService.finishDinner(tableId);
    }

    @DeleteMapping("dinner/reload")
    public void reloadDb (){
        dinnerService.reloadDb();
    }
}
