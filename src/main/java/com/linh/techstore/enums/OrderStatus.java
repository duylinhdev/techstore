package com.linh.techstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("Chờ xác nhận"),
    CONFIRMED("Đã xác nhận"),
    SHIPPING("Đang giao"),
    DELIVERED("Đã giao thành công"),
    CANCELLED("Đã huỷ");

    private final String label;

    public String getCode() {
        return this.name();
    }

}
