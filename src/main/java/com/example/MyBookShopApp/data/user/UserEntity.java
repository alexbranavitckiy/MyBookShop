package com.example.MyBookShopApp.data.user;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hash",columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(name = "reg_time",columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(name = "balance",columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(name = "name",columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "userEntity")
    private List<BookReviewLikeEntity> bookReviewLikeEntity;

    @OneToMany(mappedBy = "userEntities")
    private List<BookReviewEntity> bookReviewEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookReviewLikeEntity> getBookReviewLikeEntity() {
        return bookReviewLikeEntity;
    }

    public void setBookReviewLikeEntity(List<BookReviewLikeEntity> bookReviewLikeEntity) {
        this.bookReviewLikeEntity = bookReviewLikeEntity;
    }

    public List<BookReviewEntity> getBookReviewEntities() {
        return bookReviewEntities;
    }

    public void setBookReviewEntities(List<BookReviewEntity> bookReviewEntities) {
        this.bookReviewEntities = bookReviewEntities;
    }
}
