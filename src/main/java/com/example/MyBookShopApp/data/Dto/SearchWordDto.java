package com.example.MyBookShopApp.data.Dto;

public class SearchWordDto {

    private String example;

    public SearchWordDto(String example) {
        this.example = example;
    }

    public SearchWordDto(){}

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


    @Override
    public String toString() {
        return "SearchWordDto{" +
                "example='" + example + '\'' +
                '}';
    }
}
