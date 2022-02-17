package com.example.MyBookShopApp.data.book.review;

import com.example.MyBookShopApp.data.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book_review_like")
public class BookReviewLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    @JsonIgnore
    private BookReviewEntity bookReviewEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

    @Column(name = "number_average")
    private Integer numberAverage;

    @Column(name = "number_value")
    private Double averageValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookReviewEntity getBookReviewEntity() {
        return bookReviewEntity;
    }

    public void setBookReviewEntity(BookReviewEntity bookReviewEntity) {
        this.bookReviewEntity = bookReviewEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public Integer getNumberAverage() {
        return numberAverage;
    }

    public void setNumberAverage(Integer numberAverage) {
        this.numberAverage = numberAverage;
    }

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }
}
