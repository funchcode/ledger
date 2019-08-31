package com.funch.ledger.conf;

import org.springframework.data.repository.query.Param;

public enum Flow {

    IN('I'), OUT('O');

    private char flow;

    Flow(char flow) {
        this.flow = flow;
    }

    public char value() {
        return flow;
    }

    public static boolean validation(char flow) {
        Flow[] flows = Flow.values();
        for(Flow f : flows) {
            if (f.value() == flow) {
                return true;
            }
        }
        return false;
    }
}
