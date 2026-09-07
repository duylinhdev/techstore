package com.linh.techstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ROLE_USER("Người dùng"),
    ROLE_MANAGER("Quản lý"),
    ROLE_STAFF("Nhân viên");
    
    private final String label;

    public String getCode() {
        return this.name();
    }
}
