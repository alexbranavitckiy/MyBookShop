package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;

import java.util.List;

public interface GenreServices {


    List<GenreEntity> getAllGenre();

    boolean SaveGenre(GenreEntity genreEntity);

    boolean SortGenre(GenreEntity genreEntity);

    List<TreeGenreDto> getAllGenreDto();


}

