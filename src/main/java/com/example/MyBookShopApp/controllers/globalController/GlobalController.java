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
    public void name(@CookieValue(name = "contents", required = false) String str, Model model) {
        if (str != null) model.addAttribute("cartSize", str);
        else model.addAttribute("cartSize", "0");
    }

}
