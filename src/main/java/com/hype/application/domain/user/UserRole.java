package com.hype.application.domain.user;

import com.hype.application.infra.Authority;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
public enum UserRole {

    USER("user", 0, List.of(new Authority("ROLE_USER"))),
    ADMIN("admin", 1, List.of(
            new Authority("ROLE_USER"),
            new Authority("ROLE_ADMIN")
    )),
    MASTER("master", 2, List.of(
            new Authority("ROLE_USER"),
            new Authority("ROLE_MASTER")
    ));

    private final String role;
    private final int roleInteger;
    private final List<Authority> authorities;

    UserRole(String role,int roleInteger, List<Authority> authorities) {
        this.role = role;
        this.roleInteger = roleInteger;
        this.authorities = authorities;
    }
}

