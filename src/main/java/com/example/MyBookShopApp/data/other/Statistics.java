package com.example.MyBookShopApp.data.other;

import com.example.MyBookShopApp.data.book.Book;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automaticaly")
    private Integer id;

    @ApiModelProperty("mnemonical identity sequence of characters")
    @Column(columnDefinition = "VARCHAR(255)")
    private String slug;

    @OneToOne(mappedBy = "statistics")
    private Book book;

    @Column(name = "average_value")
    @ApiModelProperty("average value  P =(P+P1...Pn)n maximum value 5. default 0")
    private Double averageValue;

    @Column(name = "average_number", columnDefinition = "INTEGER default 0")
    @ApiModelProperty("the number of people who voted")
    private Integer numberAverage;

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

    private String mapStatistics;

    public void setByNumberB(double byNumberB) {
        this.byNumberB = byNumberB;
        count();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setInCurtNumberC(double inCurtNumberC) {
        this.inCurtNumberC = inCurtNumberC;
        count();
    }

    public void setDelayedCountK(double delayedCountK) {
        this.delayedCountK = delayedCountK;
        count();
    }

    public void setByNumberBAutomatically() {
        this.byNumberB = byNumberB + 0.2d;
        count();
    }

    public void setInCurtCAutomatically() {
        this.inCurtNumberC = inCurtNumberC + 0.9d;
        count();
    }

    public void setDelayedCountKAutomatically() {
        this.delayedCountK = delayedCountK + 0.99d;
        count();
    }

    public void countAverage(int grade) {
        numberAverage = numberAverage + 1;
        this.averageValue=this.averageValue+grade;
        this.averageValue = this.averageValue  / numberAverage;
    }

    private void count() {
        this.coefficient = this.getByNumberB() + this.getInCurtNumberC() * 0.75 + this.delayedCountK * 0.4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getByNumberB() {
        return byNumberB;
    }

    public double getInCurtNumberC() {
        return inCurtNumberC;
    }

    public double getDelayedCountK() {
        return delayedCountK;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }

    public Integer getNumberAverage() {
        return numberAverage;
    }

    public void setNumberAverage(Integer numberAverage) {
        this.numberAverage = numberAverage;
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", book=" + book.getSlug() +
                ", averageValue=" + averageValue +
                ", numberAverage=" + numberAverage +
                ", byNumberB=" + byNumberB +
                ", inCurtNumberC=" + inCurtNumberC +
                ", delayedCountK=" + delayedCountK +
                ", coefficient=" + coefficient +
                '}';
    }
}