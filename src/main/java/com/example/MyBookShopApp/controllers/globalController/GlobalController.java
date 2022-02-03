package com.example.MyBookShopApp.controllers.globalController;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void name(@CookieValue(name = "contents", required = false) String str, Model model){
        model.addAttribute("cartSize",str);
    }

}
