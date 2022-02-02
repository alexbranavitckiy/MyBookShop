package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler({EmptySearchExceprtion.class})
    public String handlerSearchException(EmptySearchExceprtion ex, RedirectAttributes redirectAttributes) {
        Logger.getLogger(this.getClass().getSimpleName()).warning(ex.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError", ex);
        return "redirect:/";
    }


    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public String handleMissingServletRequestParameterException(Exception exception,RedirectAttributes redirectAttributes){
        Logger.getLogger(this.getClass().getSimpleName()).warning(exception.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError","Missing required parameters " );
        return "redirect:/";
    }


}
