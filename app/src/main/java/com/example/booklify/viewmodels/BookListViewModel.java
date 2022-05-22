package com.example.booklify.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.booklify.request.Services;
import com.example.booklify.response.BookResponse;
import com.example.booklify.utils.BookApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListViewModel extends ViewModel {

    private MutableLiveData<BookResponse> bookList;

    public BookListViewModel() {
        bookList = new MutableLiveData<>();
    }


    public MutableLiveData<BookResponse> getBookListObserver() {
        return bookList;
    }

    public void makeApiCall() {
        BookApi apiService = Services.getBookApi();
        Call<BookResponse> call = apiService.getBooks();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                bookList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                bookList.postValue(null);
            }
        });
    }

}
