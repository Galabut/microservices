package com.ogasimov.labs.springcloud.microservices.order;

import com.ogasimov.labs.springcloud.microservices.common.CreateBillCommand;
import com.ogasimov.labs.springcloud.microservices.common.MinusStockCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private BillClient billClient;

    @Autowired
    private MySources mySources;

    public Integer createOrder(Integer tableId, List<Integer> menuItems) {
        Order order = new Order();
        order.setTableId(tableId);
        orderRepository.save(order);

        final Integer orderId = order.getId();
        stockClient.minusFromStock(menuItems);
        billClient.createBill(tableId, orderId);

        return orderId;
    }

    public Integer createOrderCmd(Integer tableId, List<Integer> menuItems) {
        Order order = new Order();
        order.setTableId(tableId);
        orderRepository.save(order);

        final Integer orderId = order.getId();
        mySources.stock().send(MessageBuilder.withPayload(new MinusStockCommand(menuItems)).build());
        mySources.bill().send(MessageBuilder.withPayload(new CreateBillCommand(tableId, orderId)).build());
        return orderId;
    }

    public void deleteAll() {
        orderRepository.deleteAll();

    }
}
