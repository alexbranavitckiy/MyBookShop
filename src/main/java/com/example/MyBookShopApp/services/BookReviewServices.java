package com.example.MyBookShopApp.services;

public interface BookReviewServices {

    boolean saveReview(String text, String slug);

    boolean saveLikeReview(String text, String slug,int value);
}
