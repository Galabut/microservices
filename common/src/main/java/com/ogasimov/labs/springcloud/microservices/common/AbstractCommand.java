package com.ogasimov.labs.springcloud.microservices.common;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = OccupyTableCommand.class, name = "occupyTableCommand"),
    @JsonSubTypes.Type(value = CreateOrderCommand.class, name = "createOrderCommand"),
    @JsonSubTypes.Type(value = MinusStockCommand.class, name = "minusStockCommand"),
    @JsonSubTypes.Type(value = PayBillCommand.class, name = "payBillCommand")

})
public abstract class AbstractCommand {

}
