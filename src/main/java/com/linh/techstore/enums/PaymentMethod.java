package com.linh.techstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    COD("Thanh toán khi nhận hàng"),
    VNPAY("Ví VNPAY"),
    MOMO("Ví MoMo"),
    BACK_TRANSFER("Chuyển khoản ngân hàng");

    private final String label;

    public String getCode() {
        return name();
    }
}
