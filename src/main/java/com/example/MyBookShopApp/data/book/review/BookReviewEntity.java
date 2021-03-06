package com.example.MyBookShopApp.data.book.review;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntities;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    @OneToMany(mappedBy = "bookReviewEntitys")
    //@JsonIgnore
    private List<BookReviewLikeEntity> bookReviewLikeEntity;

    @JsonProperty
    public int getLikeSum(){
        return  this.bookReviewLikeEntity.stream().filter(x -> x.getValue() > 0).mapToInt(BookReviewLikeEntity::getValue).sum();
    }

    @JsonProperty
    public int getNotLikeSum(){
        return  this.bookReviewLikeEntity.stream().filter(x -> x.getValue() < 0).mapToInt(BookReviewLikeEntity::getValue).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserEntity getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(UserEntity userEntities) {
        this.userEntities = userEntities;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<BookReviewLikeEntity> getBookReviewLikeEntity() {
        return bookReviewLikeEntity;
    }

    public void setBookReviewLikeEntity(List<BookReviewLikeEntity> bookReviewLikeEntity) {
        this.bookReviewLikeEntity = bookReviewLikeEntity;
    }
}
