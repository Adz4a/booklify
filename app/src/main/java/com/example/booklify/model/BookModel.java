package com.example.booklify.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BookModel implements Parcelable {

    int author;
    @SerializedName("genr")
    int category;
    @SerializedName("photo")
    private String image;
    double price;
    String title,library,content,id;
    @SerializedName("popularity")
    boolean popularity;



    public BookModel(String image, double price, String title, String library) {
        this.image = image;
        this.price = price;
        this.title = title;
        this.library = library;
    }

    public BookModel(String image, String title) {
        this.image = image;
        this.title = title;
    }


    protected BookModel(Parcel in) {
        image = in.readString();
        category = in.readInt();
        author = in.readInt();
        price = in.readDouble();
        title = in.readString();
        library = in.readString();
        content = in.readString();
        id = in.readString();
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String header) {
        this.title = header;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public void setPopularity(boolean popularity) {
        this.popularity = popularity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeInt(category);
        dest.writeInt(author);
        dest.writeDouble(price);
        dest.writeString(title);
        dest.writeString(library);
        dest.writeString(content);
        dest.writeString(id);
    }
}
