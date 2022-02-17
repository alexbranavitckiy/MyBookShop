package com.example.MyBookShopApp.services;

public interface BookReviewServices {

    boolean saveReview(String text, String slug);

    boolean saveLikeReview(int value, String reviewId);
}
