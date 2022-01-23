package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;

import java.util.List;

public interface GenreServices {


    String getGenreByName(String slug);

    List<GenreEntity> getAllGenre();

    //List<TreeGenreDto> generationTreeGenre();

    List<TreeGenreDto> getAllGenreDtoList();

    List<GenreEntity> getAllGenreAndSortByBook();

}

