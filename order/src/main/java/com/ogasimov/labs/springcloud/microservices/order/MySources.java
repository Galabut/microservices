package com.ogasimov.labs.springcloud.microservices.order;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySources {

    @Output
    MessageChannel stock();

    @Output()
    MessageChannel bill();
}

