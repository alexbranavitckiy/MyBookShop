package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@ApiModel(description = "data model of author enrity")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "author id generated by db", position = 1)
    private Integer id;

    @ApiModelProperty(value = "first name of author", example = "Bob", position = 2)
    private String firstName;

    @ApiModelProperty(value = "last name of author", example = "Blaskovits", position = 3)
    private String lastName;

    @ApiModelProperty("mnemonical identity sequence of characters")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(255)")
    private String photo;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
