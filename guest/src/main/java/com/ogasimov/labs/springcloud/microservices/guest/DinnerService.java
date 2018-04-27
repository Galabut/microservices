package com.ogasimov.labs.springcloud.microservices.guest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogasimov.labs.springcloud.microservices.common.CreateOrderCommand;
import com.ogasimov.labs.springcloud.microservices.common.OccupyTableCommand;
import com.ogasimov.labs.springcloud.microservices.common.PayBillCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinnerService {

    @Autowired
    private TableClient tableClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private BillClient billClient;

    @Autowired
    private EventClient eventClient;

    public Integer startDinner(List<Integer> menuItems) throws Exception {
        //check free tables
        List<Integer> freeTables = tableClient.getFreeTables();
        if (freeTables.size() == 0) {
            throw new RuntimeException("No free tables available.");
        }

        //occupy a table
        final Integer tableId = freeTables.get(0);
        ObjectMapper mapper = new ObjectMapper();
        eventClient.sendEvent(new OccupyTableCommand(tableId));
        tableClient.occupyTable(tableId);

        //create the order
        eventClient.sendEvent(new CreateOrderCommand(tableId, menuItems));
        orderClient.createOrder(tableId, menuItems);

        return tableId;
    }

    public void finishDinner(Integer tableId) throws Exception {
        eventClient.sendEvent(new PayBillCommand(tableId));
        billClient.payBills(tableId);
    }

    public void reloadDb() {
        tableClient.freeAllTables();
        orderClient.deleteOrders();
        billClient.deleteBills();
        eventClient.replay();

    }
}
