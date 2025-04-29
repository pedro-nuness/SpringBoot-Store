package com.hype.application.infra.Security;

import lombok.Getter;

@Getter
public enum TokenExpirationTime {
    ACCESS(2),
    REFRESH(168);

    private final Integer hours;

    TokenExpirationTime(Integer hours) {
        this.hours = hours;
    }
}
