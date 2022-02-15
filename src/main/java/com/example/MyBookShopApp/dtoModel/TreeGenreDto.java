package com.example.MyBookShopApp.dtoModel;


import java.util.ArrayList;
import java.util.List;

public class TreeGenreDto {

    private String id;

    private String parentId;

    private String slug;

    private String name;

    private int sizeBookList;

    private List<TreeGenreDto> children = new ArrayList<>();

    public TreeGenreDto() {
    }

    public TreeGenreDto(String id, String parentId, String slug, String name, int sizeBookList) {
        this.id = id;
        this.parentId = parentId;
        this.slug = slug;
        this.name = name;
        this.sizeBookList = sizeBookList;
    }



    public void add(TreeGenreDto node) {
        if (node.parentId == null || "".equals(node.parentId) || "0".equals(node.parentId)) {
            // родительский узел
            this.children.add(node);
        } else if (node.parentId.equals(this.id)) {
            // дочерний узел
            this.children.add(node);
        } else {
            for (TreeGenreDto tmp_node : children) {
                tmp_node.add(node);
            }
        }
    }

    public int getSizeBook() {
        return sizeBookList;
    }

    public void setSizeBook(int sizeBook) {
        this.sizeBookList = sizeBook;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<TreeGenreDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeGenreDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeGenreDto{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", sizeBookList=" + sizeBookList +
                '}';
    }
}
