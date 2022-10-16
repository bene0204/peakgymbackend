package com.benem.peakgym.util.enums;

import lombok.Getter;

@Getter
public enum USER_ROLE {

    BASIC("BASIC"),

    EMPLOYEE("EMPLOYEE"),

    ADMIN("ADMIN");

    private final String label;

    USER_ROLE(String label) {
        this.label = label;
    }
}
