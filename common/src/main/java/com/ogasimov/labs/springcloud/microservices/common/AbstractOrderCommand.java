package com.ogasimov.labs.springcloud.microservices.common;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateOrderCommand.class, name = "createOrderCommand"),
})
public abstract class AbstractOrderCommand extends AbstractCommand {
    private Integer tableId;
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractTableCommand.class, name = "occupyTableCommand"),
        @JsonSubTypes.Type(value = AbstractOrderCommand.class, name = "createOrderCommand"),
        @JsonSubTypes.Type(value = AbstractStockCommand.class, name = "minusStockCommand"),
        @JsonSubTypes.Type(value = AbstractBillCommand.class, name = "payBillCommand"),
    })
    public abstract class AbstractCommand {

    }
    public AbstractOrderCommand(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractOrderCommand that = (AbstractOrderCommand) o;

        return tableId != null ? tableId.equals(that.tableId) : that.tableId == null;
    }

    @Override
    public int hashCode() {
        return tableId != null ? tableId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "tableId=" + tableId +
                '}';
    }
}
