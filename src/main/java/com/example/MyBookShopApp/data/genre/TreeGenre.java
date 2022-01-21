package com.example.MyBookShopApp.data.genre;


import java.util.ArrayList;
import java.util.List;

public class TreeGenre {

    private String id;

    private String parentId;

    private String slug;

    private String name;

    private List<TreeGenre> children = new ArrayList<>();


    public TreeGenre() {
    }

    public TreeGenre(String id, String parentId, String slug, String name) {
        this.id = id;
        this.parentId = parentId;
        this.slug = slug;
        this.name = name;
    }

    public boolean ifChildrenEmpty(TreeGenre node) {
        return node.children.size() > 0;
    }

    public void add(TreeGenre node) {
        if (node.parentId == null || "".equals(node.parentId) || "0".equals(node.parentId)) {
            // родительский узел
            this.children.add(node);
        } else if (node.parentId.equals(this.id)) {
            // дочерний узел
            this.children.add(node);
        } else {
            for (TreeGenre tmp_node : children) {
                tmp_node.add(node);
            }
        }
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

    public List<TreeGenre> getChildren() {
        return children;
    }

    public void setChildren(List<TreeGenre> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeGenre{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
