package com.ogasimov.labs.springcloud.microservices.bill;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @HystrixCommand(fallbackMethod = "createBillFallback")
    @PostMapping("/bill/{tableId}/{orderId}")
    public void createBill(@PathVariable Integer tableId, @PathVariable Integer orderId) {
        billService.createBill(tableId, orderId);
    }

    @DeleteMapping("/bills/{tableId}")
    public void payBills(@PathVariable Integer tableId) {
        billService.payBills(tableId);
    }

    @GetMapping
    public String getBills() {
        return "BILLS";
    }

}
