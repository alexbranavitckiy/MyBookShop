package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.Dto.BookDto;
import com.example.MyBookShopApp.data.Dto.TagDto;
import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Statistics;
import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.services.MappingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TagDto> mapToListTagDto(List<Tag> list) {
        List<TagDto> ListTagDto = new ArrayList<>();
        list.forEach(x -> ListTagDto.add(this.mapToTagDto(x)));
        return ListTagDto;
    }

    @Override
    public TagDto mapToTagDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setNameTag(tag.getNameTag());
        tagDto.setId(tag.getId());
        return tagDto;
    }


    public List<Book> mapStatisticsToBook(List<Statistics> list) {
        List<Book> books = new ArrayList<>();
        list.stream().forEach(x -> books.add(x.getBook()));
        return books;
    }

    public List<BookDto> mapBookToBookDto(List<Book> books){
       // BookDto bookDto=new BookDto();
        return  null;

    }

}
