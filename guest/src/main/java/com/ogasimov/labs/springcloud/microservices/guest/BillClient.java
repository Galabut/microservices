package com.ogasimov.labs.springcloud.microservices.guest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BillClient")
public interface BillClient {

    @GetMapping("/get/{tableId}")
    void payBills(Integer tableId);
}
