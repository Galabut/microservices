package com.ogasimov.labs.springcloud.microservices.guest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Bill")
public interface BillClient {

    @GetMapping("/get/{tableId}")
    void payBills(@PathVariable("tableId")Integer tableId);

    @DeleteMapping("bills/deleteAll")
    void deleteBills();
}
