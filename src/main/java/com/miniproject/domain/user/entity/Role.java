package com.miniproject.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
* @package : com.miniproject.domain.user.entity
* @name : Role
* @create-date: 2022-09-15
* @author : 박수현
* @version : 1.0.0
* 
* @update-date :
* @update-author : 000
* @update-description : 
*/
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
