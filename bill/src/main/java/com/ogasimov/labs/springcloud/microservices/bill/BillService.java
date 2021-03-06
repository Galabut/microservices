package com.ogasimov.labs.springcloud.microservices.bill;

import com.ogasimov.labs.springcloud.microservices.common.CreateBillCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @StreamListener(Sink.INPUT)
    public void consume(Object s) {
        CreateBillCommand bill = (CreateBillCommand) s;
        System.out.println("BILL SERVICE - received command" + bill.getOrderId());
        createBill(bill.getTableId(), bill.getOrderId());
    }

    public void createBill(Integer tableId, Integer orderId) {
        Bill bill = new Bill();
        bill.setTableId(tableId);
        bill.setOrderId(orderId);
        billRepository.save(bill);
    }

    public void payBills(Integer tableId) {
        List<Bill> bills = billRepository.findAllByTableId(tableId);
        if (bills.isEmpty()) {
            throw new EntityNotFoundException("Bills not found");
        }
        billRepository.delete(bills);
    }

    public void deleteAll() {
        billRepository.deleteAll();
    }
}
