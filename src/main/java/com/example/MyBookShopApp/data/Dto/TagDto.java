package com.example.MyBookShopApp.data.Dto;


public class TagDto {

    private Integer id;

    private String nameTag;

    public TagDto(Integer id, String nameTag, Integer size) {
        this.id = id;
        this.nameTag = nameTag;
    }

    public TagDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

}
