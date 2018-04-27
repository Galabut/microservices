package com.ogasimov.labs.springcloud.microservices.event;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ogasimov.labs.springcloud.microservices.common.AbstractCommand;
import com.ogasimov.labs.springcloud.microservices.common.EventDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/{startId}/{count}")
    public List<EventDto> getEvents(@PathVariable("startId") Integer startId, @PathVariable("count") Integer count) {
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "sendEventFallback")
    @PostMapping("/events/send")
    public Boolean sendEvent(@RequestBody AbstractCommand cmd) throws Exception {
        System.out.println("EventController received cmd");
        return eventService.persistEvent(cmd);
    }

    public Boolean sendEventFallback(AbstractCommand cmd) {
        System.out.println("SendEventFallback error");
        return false;
    }

    @GetMapping("/events/replay")
    public void replay() {
        eventService.replayEvents();
    }
}
