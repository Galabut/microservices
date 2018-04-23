package com.ogasimov.labs.springcloud.microservices.table;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @HystrixCommand(fallbackMethod = "getTablesFallback")
    @GetMapping("/tables")
    public List<Integer> getTables() {
        return tableService.getTableIds();
    }

    @HystrixCommand(fallbackMethod = "getFreeTablesFallback")
    @GetMapping("/tables/free")
    public List<Integer> getFreeTables() {
        return tableService.getFreeTableIds();
    }

    @PutMapping("/table/{id}/free")
    public void freeTable(@PathVariable Integer id) {
        tableService.updateTable(id, true);
    }

    @PutMapping("/table/{id}/occupy")
    public void occupyTable(@PathVariable Integer id) {
    }

    public List<Integer> getTablesFallback(Throwable th) {
        System.out.println("Fallback error. Reason: " + th.getMessage());
        return new ArrayList<>();
    }

    public List<Integer> getFreeTablesFallback(Throwable th) {
        System.out.println("Fallback error. Reason: " + th.getMessage());
        return new ArrayList<>();
    }
}
