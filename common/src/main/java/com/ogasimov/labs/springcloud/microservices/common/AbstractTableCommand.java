package com.ogasimov.labs.springcloud.microservices.common;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.WRAPPER_OBJECT,
    property = "class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = OccupyTableCommand.class, name = "occupyTableCommand"),
})
public abstract class AbstractTableCommand extends AbstractCommand {

    private Integer tableId;

    public AbstractTableCommand(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public AbstractTableCommand() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractTableCommand that = (AbstractTableCommand) o;

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
