package com.ogasimov.labs.springcloud.microservices.guest;

import com.ogasimov.labs.springcloud.microservices.common.AbstractCommand;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Event")
public interface EventClient {

    @PostMapping("/events/send")
    Boolean sendEvent(@RequestBody AbstractCommand cmd) throws Exception;

    @GetMapping("/events/replay")
    void replay();
}
