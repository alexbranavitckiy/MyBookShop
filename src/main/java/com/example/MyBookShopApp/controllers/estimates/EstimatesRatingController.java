package com.example.MyBookShopApp.controllers.estimates;

import com.example.MyBookShopApp.data.ApiResponse;
import com.example.MyBookShopApp.data.ResponseRating;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.StatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class EstimatesRatingController {

    private final StatisticsServices statisticsServices;
    private final BookService bookService;


    @Autowired
    private EstimatesRatingController(BookService bookService, StatisticsServices statisticsServices) {
        this.statisticsServices = statisticsServices;
        this.bookService = bookService;
    }

    @PostMapping("/changeBookStatus/rating")
    @ResponseBody
    public ResponseRating handleChangeBookStatus(@RequestParam("bookId") String BookSlug, @RequestParam("value") Integer value, Model model) {
        if (BookSlug != null && value != null) {
            this.statisticsServices.addStatisticsAverageValue(value, BookSlug);
        }
        ResponseRating responseRating = new ResponseRating();
        Optional<Book> bookOptional = bookService.getBookBySlug(BookSlug);
        if (bookOptional.isPresent()) {
            responseRating.setResult(true);
            Statistics statistics = bookOptional.get().getStatistics();
            if (statistics.getAverageValue() != 0) {
                responseRating.setAverageValue(statistics.getAverageValue()/statistics.getNumberAverage());
            } else responseRating.setAverageValue(1d);
            responseRating.setNumberAverage(statistics.getNumberAverage());
        } else responseRating.setResult(false);
        return responseRating;
    }
}
