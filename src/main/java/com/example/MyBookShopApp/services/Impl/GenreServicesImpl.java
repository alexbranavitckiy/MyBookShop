package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.repository.GenreEntityRepository;
import com.example.MyBookShopApp.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServicesImpl implements GenreServices {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GenreEntityRepository genreEntityRepository;

    @Override
    public List<GenreEntity> getAllGenre() {
        return genreEntityRepository.findAll();
    }
    @Override
    public List<TreeGenreDto> getAllGenreDto() {
        return genreEntityRepository.findAllDtoedBy();
    }

    @Override
    public boolean SaveGenre(GenreEntity genreEntity) {
        return false;
    }

    @Override
    public boolean SortGenre(GenreEntity genreEntity) {
        return false;
    }
}
