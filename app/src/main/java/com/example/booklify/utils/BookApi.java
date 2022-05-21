package com.example.booklify.utils;

import com.example.booklify.model.BookModel;
import com.example.booklify.response.BookResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApi {

    @GET("book/")
    Call<BookResponse> getBooks();

    @GET("book/{id}/")
    Call<BookModel> getIdBooks(@Path("id") int id);

//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int postId);

    @POST("posts")
    Call<BookResponse> createBooks(@Body BookResponse post);

    @FormUrlEncoded
    @POST("posts")
    Call<BookResponse> createBooks(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<BookResponse> putBooks(@Path("id") int id, @Body BookResponse post);

    @DELETE("posts/{id}")
    Call<Void> deleteBooks(@Path("id") int id);
}
