package com.example.MyBookShopApp.services;


import com.example.MyBookShopApp.data.Dto.TagDto;
import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.data.other.Tag;

import java.util.List;

public interface MappingService {

    TreeGenreDto mapToTreeGenreDto(GenreEntity entity);

    TagDto mapToTagDto(Tag tag);

    List<TagDto> mapToListTagDto(List<Tag> list);

    public List<Book> mapStatisticsToBook(List<Statistics> list);
}
