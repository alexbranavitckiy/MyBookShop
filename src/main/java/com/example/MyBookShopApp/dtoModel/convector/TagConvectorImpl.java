package com.example.MyBookShopApp.dtoModel.convector;

import com.example.MyBookShopApp.dtoModel.MapperDto;
import com.example.MyBookShopApp.dtoModel.tag.TagDtoModel;
import com.example.MyBookShopApp.data.other.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagConvectorImpl implements MapperDto<TagDtoModel, Tag> {

    @Autowired
    private ModelMapper mapper;


    @Override
    public Tag toEntity(TagDtoModel dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Tag.class);
    }

    @Override
    public TagDtoModel toDto(Tag entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TagDtoModel.class);
    }



}