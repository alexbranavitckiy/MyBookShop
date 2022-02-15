package com.example.MyBookShopApp.dtoModel.author;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorsModel {

    private String firstName;

    private String lastName;

    private String slug;

    @JsonProperty
    public String authorsFullName() {
        return firstName + " " + lastName;
    }


    @Override
    public String toString() {
        return firstName + ' ' + lastName;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
