package com.example.MyBookShopApp.services.Impl;


import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.services.ToolCartAndPostponedServices;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.StringJoiner;

@Service
public class ToolCartAndPostponedServicesImpl implements ToolCartAndPostponedServices {


    public String[] generateCookieSlugsToSlugs(String contentsPostponed) {
        contentsPostponed = contentsPostponed.startsWith("/") ? contentsPostponed.substring(1) : contentsPostponed;
        contentsPostponed = contentsPostponed.endsWith("/") ? contentsPostponed.substring(0, contentsPostponed.length() - 1) :
                contentsPostponed;
        return contentsPostponed.split("/");
    }

    public Cookie addSlugCart(String cookieCart, String slug, String nameCoolie, String path) {
        StringJoiner stringJoiner = new StringJoiner("/");// string concatenation
        stringJoiner.add(cookieCart).add(slug);
        Cookie cookie = new Cookie(nameCoolie, stringJoiner.toString());
        cookie.setPath(path);
        return cookie;
    }



    public Cookie SetCookie(String path, String slug, String name) {
        Cookie cookie = new Cookie(name, slug);
        cookie.setPath(path);
        return cookie;
    }

    public Cookie defaultCartSize(String cookie, String nameCookie) throws EmptySearchExceprtion {
        Cookie cookieCont = new Cookie(nameCookie, "1");
        cookieCont.setPath("/");
        return cookieCont;
    }

    public Cookie plusSizeCart(String cookie, String nameCookie) throws EmptySearchExceprtion {
        if (cookie != null && !cookie.equals("")) {
            Cookie cookieCont;
            cookieCont = new Cookie(nameCookie, String.valueOf(Integer.parseInt(cookie) + 1));
            cookieCont.setPath("/");
            return cookieCont;
        }
        throw new EmptySearchExceprtion("Error type in  ToolCartAndPostponedServices method plusSizeCart");
    }

    public Cookie minesSizeCart(String cookie, String nameCookie) throws EmptySearchExceprtion {
        if (cookie != null && !cookie.equals("")) {
            Cookie cookieCont;
            cookieCont = new Cookie(nameCookie, String.valueOf(Integer.parseInt(cookie) - 1));
            cookieCont.setPath("/");
            return cookieCont;
        }
        throw new EmptySearchExceprtion("Error type in  ToolCartAndPostponedServices method minesSizeCart");
    }
}
