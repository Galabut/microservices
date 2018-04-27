package com.ogasimov.labs.springcloud.microservices.common;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

public class PayBillCommand extends AbstractBillCommand {

    public PayBillCommand() {
    }

    public PayBillCommand(Integer tableId) {
        super(tableId);
    }
}
