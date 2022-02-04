package com.example.MyBookShopApp.controllers.globalController;


import com.example.MyBookShopApp.myAnnotations.GlobalData;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Objects;


@ControllerAdvice
public class GlobalController {

    @ModelAttribute(name = "cartSize")
    public void cartSize(@CookieValue(name = "contents", required = false) String str, Model model) {
        if (str != null) model.addAttribute("cartSize", str);
        else model.addAttribute("cartSize", "0");
    }

    @ModelAttribute(name = "cartPostponedSize")
    public void cartPostponed(@CookieValue(name = "cartPostponedSize", required = false) String str, Model model) {
        if (str != null) model.addAttribute("cartPostponedSize", str);
        else model.addAttribute("cartPostponedSize", "0");
    }
}
