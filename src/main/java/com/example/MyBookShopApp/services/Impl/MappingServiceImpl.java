package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.dtoModel.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.services.MappingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MappingServiceImpl implements MappingService {


    @Override
    public TreeGenreDto mapToTreeGenreDto(GenreEntity entity) {
        TreeGenreDto treeGenreDto = new TreeGenreDto();
        treeGenreDto.setParentId(entity.getParentId());
        treeGenreDto.setName(entity.getName());
        treeGenreDto.setSlug(entity.getSlug());
        treeGenreDto.setSizeBook(entity.getListBook().size());
        treeGenreDto.setId(entity.getIdTag());
        return treeGenreDto;
    }

    @Override
    public List<BookDtoModel> mapStatisticsToBook(List<Statistics> list) {
        return null;
    }

}
