package com.services.Auth_Service.constance;

public enum UserRoles {
//    ROLE_INDEX,
    ADMIN,
    USER;

    public String withPrefix() {
        return "ROLE_" + this.name();
    }

    }
