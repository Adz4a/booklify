package com.example.booklify.request;

import com.example.booklify.utils.BookApi;
import com.example.booklify.utils.Credentials;
import com.example.booklify.utils.ProfileApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static BookApi bookApi = retrofit.create(BookApi.class);

    public static BookApi getBookApi(){
        return bookApi;
    }

    private static ProfileApi profileApi = retrofit.create(ProfileApi.class);

    public static ProfileApi getProfileApi(){return profileApi;}
}
