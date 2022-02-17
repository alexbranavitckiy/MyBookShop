package com.example.MyBookShopApp.controllers.review;

import com.example.MyBookShopApp.data.Responseses;
import com.example.MyBookShopApp.dtoModel.convector.TagConvectorImpl;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@GlobalData
public class ReviewAndLikeController {


    private final BookService bookService;
    private final TagService tagService;
    private final ResourceStorage storage;
    private final MappingService mappingService;
    private final StatisticsServices statisticsServices;
    private final TagConvectorImpl tagConvector;
    private final BookReviewServices bookReviewServices;

    @Autowired
    public ReviewAndLikeController(BookReviewServices bookReviewServices, TagConvectorImpl tagConvector, StatisticsServices statisticsServices, MappingService mappingService, ResourceStorage storage, BookService bookService, TagService tagService) {
        this.storage = storage;
        this.bookReviewServices = bookReviewServices;
        this.tagConvector = tagConvector;
        this.statisticsServices = statisticsServices;
        this.mappingService = mappingService;
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @PostMapping("/bookReview/save")
    @ResponseBody// rewrite!
    public Responseses saveNewbookReview(@RequestParam(value = "text", required = false) String text, @RequestParam(value = "bookId", required = false) String slug) {
        bookReviewServices.saveReview(text, slug);
        Responseses response = new Responseses(true);
        return response;
    }


    @PostMapping("rateBookReview/like")
    @ResponseBody// rewrite!
    public Responseses savelikeReview(@RequestParam(value = "value", required = false) int value, @RequestParam(value = "reviewid", required = false) String reviewid) {
        bookReviewServices.saveLikeReview(value, reviewid);
        Responseses response = new Responseses(true);
        return response;
    }


}
