package com.ogasimov.labs.springcloud.microservices.stock;


import com.ogasimov.labs.springcloud.microservices.common.MinusStockCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @StreamListener(Sink.INPUT)
    public void consume(Object s) {
        MinusStockCommand minus = (MinusStockCommand) s;
        System.out.println("STOCK SERVICE - received command" + minus.getMenuItems());
        minusFromStock(minus.getMenuItems());
    }

    public void minusFromStock(List<Integer> menuItems) {
        menuItems.forEach(menuItemId -> {
            Stock stock = stockRepository.findOneByMenuItemId(menuItemId);
            if (stock == null) {
                throw new EntityNotFoundException("Stock not found: " + menuItemId);
            }
            if (stock.getCount() == 0) {
                throw new EntityNotFoundException("Stock empty: " + menuItemId);
            }
            stock.setCount(stock.getCount() - 1);
            stockRepository.save(stock);
        });

    }

    public void restoreStock() {
        List<Stock> itemList = stockRepository.findAll().stream().peek(s -> s.setCount(5)).collect(Collectors.toList());
        stockRepository.save(itemList);
        System.out.println("Added to Stock");
    }

}
