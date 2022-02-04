package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.erss.EmptySearchExceprtion;

import javax.servlet.http.Cookie;

public interface ToolCartAndPostponedServices {

     String[] generateCookieSlugsToSlugs(String contentsPostponed);

     Cookie addSlugCart(String cookieCart, String slug, String nameCoolie, String path);

     Cookie SetCookie(String path, String slug, String name);

     Cookie defaultCartSize(String cookie, String nameCookie) throws EmptySearchExceprtion;

     Cookie minesSizeCart(String cookie, String nameCookie) throws EmptySearchExceprtion;

     Cookie plusSizeCart(String cookie, String nameCookie) throws EmptySearchExceprtion;

}
