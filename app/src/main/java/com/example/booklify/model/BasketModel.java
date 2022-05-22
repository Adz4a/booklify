package com.example.booklify.model;

public class BasketModel {
    int author;
    int category;
    private String image;
    int price;
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

    public int getPrice() {
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

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId(){return documentId;}
}
