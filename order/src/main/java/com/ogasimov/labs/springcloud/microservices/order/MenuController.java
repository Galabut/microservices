package com.ogasimov.labs.springcloud.microservices.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public Map<Integer, String> getMenu() {
        return menuService.getMenuItems();
    }
}
