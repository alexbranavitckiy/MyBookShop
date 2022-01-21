package com.example.MyBookShopApp.data.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



public class TreeGenreDto {

    private String idTag;

    private String parentId;

    private String slug;

    private String name;

    public TreeGenreDto(String idTag, String parentId, String slug, String name) {
        this.idTag = idTag;
        this.parentId = parentId;
        this.slug = slug;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreeGenreDto{" +
                "idTag='" + idTag + '\'' +
                ", parentId='" + parentId + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
