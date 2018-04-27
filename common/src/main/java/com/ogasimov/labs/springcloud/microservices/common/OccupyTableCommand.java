package com.ogasimov.labs.springcloud.microservices.common;

public class OccupyTableCommand extends AbstractTableCommand {

    public OccupyTableCommand() {
    }

    public OccupyTableCommand(Integer tableId) {
        super(tableId);
    }

}
