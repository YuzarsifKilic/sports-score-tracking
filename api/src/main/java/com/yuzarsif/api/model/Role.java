package com.yuzarsif.api.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_FOOTBALL_FAN, ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
