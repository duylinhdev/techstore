package com.linh.techstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("Hoạt động"),
    INACTIVE("Không hoạt động"),
    BLOCKED("Đã khoá");
    
    private final String label;

    public String getCode() {
        return this.name();
    }
}
