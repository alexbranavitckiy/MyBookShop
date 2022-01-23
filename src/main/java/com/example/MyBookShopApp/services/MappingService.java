package com.example.MyBookShopApp.services;


import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;

public interface MappingService {

    TreeGenreDto mapToTreeGenreDto(GenreEntity entity);




}
