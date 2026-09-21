package com.linh.techstore.controller;

import com.linh.techstore.service.BrandService;
import com.linh.techstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping({"/", "/trang-chu"})
    public String index(Model model) {

        model.addAttribute("newProducts", productService.getNewProducts());

        model.addAttribute("featureProducts", productService.getFeatureProducts());

        model.addAttribute("brands", brandService.listBrands());
        return "index";
    }

    @GetMapping("/dang-nhap")
    public String login() {
        return "login";
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
