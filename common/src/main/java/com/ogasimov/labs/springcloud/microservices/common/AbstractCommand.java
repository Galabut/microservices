package com.ogasimov.labs.springcloud.microservices.common;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OccupyTableCommand.class, name = "occupyTableCommand"),
        @JsonSubTypes.Type(value = CreateOrderCommand.class, name = "createOrderCommand"),
        @JsonSubTypes.Type(value = MinusStockCommand.class, name = "minusStockCommand"),
        @JsonSubTypes.Type(value = PayBillCommand.class, name = "payBillCommand"),

})
public abstract class AbstractCommand {

}
