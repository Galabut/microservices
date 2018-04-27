package com.ogasimov.labs.springcloud.microservices.common;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.WRAPPER_OBJECT,
    property = "class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = OccupyTableCommand.class, name = "occupyTableCommand"),
    @JsonSubTypes.Type(value = CreateOrderCommand.class, name = "createOrderCommand"),
    @JsonSubTypes.Type(value = MinusStockCommand.class, name = "minusStockCommand"),
    @JsonSubTypes.Type(value = PayBillCommand.class, name = "payBillCommand"),
    @JsonSubTypes.Type(value = AbstractTableCommand.class, name = "occupyTableCommand"),
    @JsonSubTypes.Type(value = AbstractOrderCommand.class, name = "createOrderCommand"),
    @JsonSubTypes.Type(value = AbstractStockCommand.class, name = "minusStockCommand"),
    @JsonSubTypes.Type(value = AbstractBillCommand.class, name = "payBillCommand")

})
public abstract class AbstractCommand {

}
