package com.example.MyBookShopApp.dtoModel.genre;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

import javax.persistence.*;
import java.util.List;

public class GenreEntityModel {

    private String idTag;

    private String parentId;

    private String slug;

    private String name;

    private List<BookDtoModel> listBook;

    public GenreEntityModel() {
    }

    public GenreEntityModel(String idTag, String parentId, String slug, String name, List<BookDtoModel> listBook) {
        this.idTag = idTag;
        this.parentId = parentId;
        this.slug = slug;
        this.name = name;
        this.listBook = listBook;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDtoModel> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookDtoModel> listBook) {
        this.listBook = listBook;
    }
}
