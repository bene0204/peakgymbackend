package com.benem.peakgym.util.enums;

import lombok.Getter;

@Getter
public enum PAYMENT_METHOD {

    CASH("KÉSZPÉNZ"),

    CARD("BANKKÁRTYA"),

    SZEP_CARD("SZÉP KÁRTYA");

    private final String label;

    PAYMENT_METHOD(String label) {
        this.label = label;
    }
}
