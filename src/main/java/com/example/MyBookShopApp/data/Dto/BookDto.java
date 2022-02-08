package com.example.MyBookShopApp.data.Dto;

import com.example.MyBookShopApp.data.book.Author;

import java.util.Date;


public class BookDto {

    private int id;

    private Date pubDate;

    private Author author;

    public String authorsFullName() {
        return author.toString();
    }

    private String slug;

    private String title;

    private String image;

    private String description;

    private Integer priceOld;

    private Double price;

    public BookDto(int id, Date pubDate, Author author, String slug, String title, String image, String description, int priceOld, Double price) {
        this.id = id;
        this.pubDate = pubDate;
        this.author = author;
        this.slug = slug;
        this.title = title;
        this.image = image;
        this.description = description;
        this.priceOld = priceOld;
        this.price = price;
    }

    public Integer dicsountPrice() {//Math.round for rounding abd tiIntExact for transfer to int
        return Math.toIntExact((priceOld - Math.round((priceOld * price) / 100)));
    }

    public Date getPubDate() {
        return pubDate;
    }


    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "BookDto{" +
                "id=" + id +
                ", pubDate=" + pubDate +
                ", author=" + author +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }
}
