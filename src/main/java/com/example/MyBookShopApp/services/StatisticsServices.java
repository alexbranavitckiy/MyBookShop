package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;

import java.util.List;
import java.util.Optional;

public interface StatisticsServices {

    List<BookDtoModel>  getPageOfNameSortStatisticsBooksByPopularAndMapping(Integer offset, Integer limit, String nameSort);

    boolean addStatisticsAverageValue(int averageValue, String slug);

    Double getStatisticsAverageBySlug(String slug) throws EmptySearchExceprtion;

    boolean clearStatisticsAverageBySlug(String slug);

    Optional<Statistics> getStatistics(String slug);

    boolean addStatisticsAverageValue(int averageValue, Optional<Statistics> statistics);

    Statistics setStatisticsHistory(int x, Statistics statistics);
}
