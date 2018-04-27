package com.ogasimov.labs.springcloud.microservices.common;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.List;
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MinusStockCommand.class, name = "minusStockCommand"),
})
public abstract class AbstractStockCommand extends AbstractCommand {
    private List<Integer> menuItems;

    public AbstractStockCommand(List<Integer> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Integer> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Integer> menuItems) {
        this.menuItems = menuItems;
    }


    public AbstractStockCommand() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractStockCommand that = (AbstractStockCommand) o;

        return menuItems != null ? menuItems.equals(that.menuItems) : that.menuItems == null;
    }

    @Override
    public int hashCode() {
        return menuItems != null ? menuItems.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "menuItems=" + menuItems +
                '}';
    }
}
