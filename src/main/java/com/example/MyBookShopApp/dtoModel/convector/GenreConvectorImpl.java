package com.example.MyBookShopApp.dtoModel.convector;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.dtoModel.MapperDto;
import com.example.MyBookShopApp.dtoModel.author.AuthorsModel;
import com.example.MyBookShopApp.dtoModel.genre.GenreEntityModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class GenreConvectorImpl implements MapperDto<GenreEntityModel, GenreEntity> {

    @Autowired
    private ModelMapper mapper;


    @Override
    public GenreEntity toEntity(GenreEntityModel dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, GenreEntity.class);
    }

    @Override
    public GenreEntityModel toDto(GenreEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, GenreEntityModel.class);
    }

}