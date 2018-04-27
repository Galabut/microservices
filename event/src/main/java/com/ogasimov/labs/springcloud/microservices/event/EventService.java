package com.ogasimov.labs.springcloud.microservices.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogasimov.labs.springcloud.microservices.common.AbstractCommand;
import com.ogasimov.labs.springcloud.microservices.common.CreateOrderCommand;
import com.ogasimov.labs.springcloud.microservices.common.OccupyTableCommand;
import com.ogasimov.labs.springcloud.microservices.common.PayBillCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventService {


    @Autowired
    EventRepository eventRepository;

    ObjectMapper mapper = new ObjectMapper();

    public boolean persistEvent(AbstractCommand cmd) throws Exception {
        Event event = new Event();
        ObjectMapper objectMapper = new ObjectMapper();
        event.setPayload(objectMapper.writeValueAsString(cmd));
//        event.setType(payload.getClass().getName());
        eventRepository.save(event);
        return true;
    }

    public void replayEvents() {
        eventRepository.findAll().stream().map(event -> {
            try {
                sendEvent(mapper.readValue(event.getPayload(), AbstractCommand.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void sendEvent(AbstractCommand event) {
        if (event instanceof OccupyTableCommand) {
            System.out.println((OccupyTableCommand) event);
        }
        if (event instanceof PayBillCommand) {
            System.out.println((PayBillCommand) event);
        }

        if (event instanceof CreateOrderCommand) {
            System.out.println((CreateOrderCommand) event);
        } else {
            System.out.println("Received unknown event");
        }
    }

}
