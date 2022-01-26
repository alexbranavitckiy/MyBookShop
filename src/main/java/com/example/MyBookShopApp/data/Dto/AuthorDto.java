package com.example.MyBookShopApp.data.Dto;




public class AuthorDto {

    private String firstName;

    private String lastName;

    private String slug;


    public AuthorDto(String firstName, String lastName, String slug) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.slug = slug;
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
