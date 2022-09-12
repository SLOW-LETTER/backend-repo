package com.miniproject.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
//@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    Role(String value){
        this.value = value;
    }

    @Getter
    private final String value;
}
