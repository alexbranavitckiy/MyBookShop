package com.example.MyBookShopApp.dtoModel.convector;


import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.MapperDto;
import com.example.MyBookShopApp.dtoModel.convector.AuthorConvectorImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BookСonvectorImpl implements MapperDto<BookDtoModel, Book> {

    private final ModelMapper modelMapper;
    private final AuthorConvectorImpl authorConvector;

    @Autowired
    public BookСonvectorImpl(AuthorConvectorImpl authorConvector, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.authorConvector = authorConvector;
    }

    public BookDtoModel convertToDto(Book entity) {
        return modelMapper.map(entity, BookDtoModel.class);
    }


    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Book.class, BookDtoModel.class)
                .addMappings(m -> m.skip(BookDtoModel::setAuthor)).setPostConverter(toDtoConverter());
    }

    public Converter<Book, BookDtoModel> toDtoConverter() {
        return context -> {
            Book source = context.getSource();
            BookDtoModel destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(Book source, BookDtoModel destination) {
        destination.setAuthor(Objects.isNull(source) || Objects.isNull(source.getAuthor()) ? null : authorConvector.toDto(source.getAuthor()));
    }

    @Override
    public Book toEntity(BookDtoModel dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Book.class);
    }

    @Override
    public BookDtoModel toDto(Book entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, BookDtoModel.class);
    }
}
