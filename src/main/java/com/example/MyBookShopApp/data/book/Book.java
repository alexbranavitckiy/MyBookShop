package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "books")
@ApiModel(description = "entity representing a book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automaticaly")
    private Integer id;

    @Column(name = "pub_date")
    @ApiModelProperty("date of book publication")
    private Date pubDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tags_id")
    @JsonIgnore
    private List<Tag> tags;

    @ManyToMany()
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private List<GenreEntity> genreEntities;

    @Column(name = "by_number_b")
    @ApiModelProperty("the number of users who bought the book")
    private double byNumberB;

    @Column(name = "in_curt_number_c")
    @ApiModelProperty("the number of users who have the book in their cart")
    private double inCurtNumberC;

    @Column(name = "delayed_count_k")
    @ApiModelProperty("the number of users who have the book delayed.")
    private double delayedCountK;

    @Column(name = "coefficient")
    @ApiModelProperty("Book popularity P = B + 0,7*C + 0,4*K")
    private double coefficient;

    @ApiModelProperty("mnemonical identity sequence of characters")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String slug;

    @ApiModelProperty("book title")
    private String title;
    @ApiModelProperty("image url")
    private String image;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    private String description;

    @Column(name = "price")
    @JsonProperty("price")
    @ApiModelProperty("book price without discount")
    private Integer priceOld;

    @Column(name = "discount")
    @JsonProperty("discount")
    @ApiModelProperty("discount value for book")
    private Double price;

    public Date getPubDate() {
        return pubDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    private void count() {
        this.coefficient = this.getByNumberB() + this.getInCurtNumberC() * 0.75 + this.delayedCountK * 0.4;
    }

    public double getByNumberB() {
        return byNumberB;
    }

    public void setByNumberB(double byNumberB) {
        count();
        this.byNumberB = byNumberB;
    }

    public double getInCurtNumberC() {
        return inCurtNumberC;
    }

    public void setInCurtNumberC(double inCurtNumberC) {
        this.inCurtNumberC = inCurtNumberC;
        count();
    }

    public double getDelayedCountK() {
        return delayedCountK;
    }

    public void setDelayedCountK(double delayedCountK) {
        this.delayedCountK = delayedCountK;
        count();
    }

    public List<GenreEntity> getGenreEntities() {
        return genreEntities;
    }

    public void setGenreEntities(List<GenreEntity> genreEntities) {
        this.genreEntities = genreEntities;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }
}