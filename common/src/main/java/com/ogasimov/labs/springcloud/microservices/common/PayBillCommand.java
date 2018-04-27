package com.ogasimov.labs.springcloud.microservices.common;

public class PayBillCommand extends AbstractBillCommand {

    public PayBillCommand() {
    }

    public PayBillCommand(Integer tableId) {
        super(tableId);
    }
}
