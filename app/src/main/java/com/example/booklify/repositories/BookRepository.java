package com.example.booklify.repositories;

import androidx.lifecycle.LiveData;

import com.example.booklify.model.BookModel;
import com.example.booklify.request.BookApiClient;

import java.util.List;

public class BookRepository {

    private static BookRepository instance;

    private BookApiClient bookApiClient;

    public static BookRepository getInstance(){
        if (instance == null){
            new BookRepository();
        }
        return instance;
    }

    private BookRepository(){
        bookApiClient = BookApiClient.getInstance();
    }

    public LiveData<List<BookModel>> getBooks(){
        return bookApiClient.getBooks();
    }

}
