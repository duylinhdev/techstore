package com.linh.techstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dang-nhap")
    public String login() {
        return "login";
    }

    @GetMapping("/san-pham")
    public String showProducts() {
        return "shop";
    }

    @GetMapping("/lien-he")
    public String contact() {
        return "contact";
    }

    @GetMapping("/gioi-thieu")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/yeu-thich")
    public String showWishlist() {
        return "wishlist";
    }

    @GetMapping("/gio-hang")
    public String showCart() {
        return "cart";
    }

    @GetMapping("/thanh-toan")
    public String showCheckout() {
        return "checkout";
    }

    @GetMapping("/tai-khoan")
    public String showAccount() {
        return "my-account";
    }
}
