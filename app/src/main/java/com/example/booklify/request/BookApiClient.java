package com.example.booklify.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booklify.model.BookModel;

import java.util.List;

public class BookApiClient {

    private static BookApiClient instance;

    private MutableLiveData<List<BookModel>> mBooks;

    public static BookApiClient getInstance(){
        if (instance == null){
            new BookApiClient();
        }
        return instance;
    }

    private BookApiClient(){
        mBooks = new MutableLiveData<>();
    }

    public LiveData<List<BookModel>> getBooks(){
        return mBooks;
    }




}

