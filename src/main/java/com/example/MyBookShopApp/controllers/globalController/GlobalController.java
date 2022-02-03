package com.example.MyBookShopApp.controllers.globalController;


import com.example.MyBookShopApp.myAnnotations.GlobalData;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;


@ApiOperation("global controller to provide shared elements")
@ControllerAdvice(annotations = GlobalData.class)
public class GlobalController {

    @ModelAttribute
    public void name(@CookieValue(name = "contents", required = false) String str, Model model){
        model.addAttribute("cartSize",str);
    }

}
