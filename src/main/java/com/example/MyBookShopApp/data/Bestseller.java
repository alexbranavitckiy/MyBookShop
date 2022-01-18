package com.example.MyBookShopApp.data;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "besteller")
//@ApiModel(description = "data model of besteller enrity")
public class Bestseller {


    @Id
    @ApiModelProperty(value = "", position = 1)
    private Integer id;


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


    public Bestseller() {
    }

    public Bestseller(double byNumberB, double inCurtNumberC, double delayedCountK) {
        this.id = id;
        this.byNumberB = byNumberB;
        this.inCurtNumberC = inCurtNumberC;
        this.delayedCountK = delayedCountK;
        this.coefficient = byNumberB + inCurtNumberC * 0.75 + delayedCountK * 0.4;
    }

    @Override
    public String toString() {
        return "Bestseller{" +
                "id=" + id +
                ", byNumberB=" + byNumberB +
                ", inCurtNumberC=" + inCurtNumberC +
                ", delayedCountK=" + delayedCountK +
                ", coefficient=" + coefficient +
                '}';
    }


    private void count() {
        System.out.println("count()");
        this.coefficient = this.getByNumberB() + this.getInCurtNumberC() * 0.75 + this.delayedCountK * 0.4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        count();
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

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
        count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestseller that = (Bestseller) o;
        return
                Double.compare(that.coefficient, coefficient) == 0 &&
                        Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coefficient);
    }
}
