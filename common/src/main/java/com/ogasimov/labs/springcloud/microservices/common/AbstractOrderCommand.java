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

    public AbstractOrderCommand(Integer tableId) {
        this.tableId = tableId;
    }

    public AbstractOrderCommand() {
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

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
