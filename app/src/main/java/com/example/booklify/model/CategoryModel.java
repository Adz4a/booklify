package com.example.booklify.model;

public class CategoryModel {
    int author;
    int category;
    private String image;
    double price;
    String title,library,content,id;
    boolean popularity;

    public boolean isBookmark() {
        return true;
    }

    boolean bookmark;
    String documentId;

    public String getImage() {
        return image;
    }


    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getAuthor(){return author;}

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public int getCategory() {
        return category;
    }


    public String getContent() {
        return content;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPopularity() {
        return popularity;
    }
}