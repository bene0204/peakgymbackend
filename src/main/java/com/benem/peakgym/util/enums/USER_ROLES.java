package com.benem.peakgym.util.enums;

import lombok.Getter;

@Getter
public enum USER_ROLES {

    BASIC("BASIC"),

    EMPLOYEE("EMPLOYEE"),

    ADMIN("ADMIN");

    private final String value;

    USER_ROLES(String label) {
        this.value = label;
    }
}
