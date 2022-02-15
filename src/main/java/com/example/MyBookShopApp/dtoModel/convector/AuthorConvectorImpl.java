package com.example.MyBookShopApp.dtoModel.convector;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.dtoModel.author.AuthorsModel;
import com.example.MyBookShopApp.dtoModel.MapperDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorConvectorImpl implements MapperDto<AuthorsModel, Author> {

    @Autowired
    private ModelMapper mapper;


    @Override
    public Author toEntity(AuthorsModel dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Author.class);
    }

    @Override
    public AuthorsModel toDto(Author entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AuthorsModel.class);
    }


}
