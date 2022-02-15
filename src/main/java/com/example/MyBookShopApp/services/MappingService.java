package com.example.MyBookShopApp.services;


import com.example.MyBookShopApp.dtoModel.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

import java.util.List;

public interface MappingService {

    TreeGenreDto mapToTreeGenreDto(GenreEntity entity);
    List<BookDtoModel> mapStatisticsToBook(List<Statistics> list);
}
