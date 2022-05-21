package com.example.booklify.response;

import com.example.booklify.model.BookModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {


    @SerializedName("results")
    @Expose
    private List<BookModel> books;

    public List<BookModel> getBooks(){
        return books;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movieModel=" + books +
                '}';
    }

}



