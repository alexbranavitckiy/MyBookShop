package com.example.MyBookShopApp.dtoModel.book;

import com.example.MyBookShopApp.dtoModel.author.AuthorsModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;


public class BookDtoModel {

    private String slug;

    private String title;

    private AuthorsModel authors;

    private String image;

    private String description;

    private Date pubDate;

    @JsonProperty("price")
    private Integer priceOld;

    @JsonProperty("discount")
    private Double price;

    @JsonProperty
    public Integer dicsountPrice() {//Math.round for rounding abd tiIntExact for transfer to int
        return Math.toIntExact((priceOld - Math.round((priceOld * price) / 100)));
    }

    @JsonProperty
    public String authorsFullName() {
        return authors.getFirstName() + " " + authors.getLastName();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorsModel getAuthor() {
        return authors;
    }

    public void setAuthor(AuthorsModel author) {
        this.authors = author;
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

    public AuthorsModel getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorsModel authors) {
        this.authors = authors;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "BookDtoModel{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }
}
