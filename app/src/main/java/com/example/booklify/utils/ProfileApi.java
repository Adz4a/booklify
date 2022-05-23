package com.example.booklify.utils;

import com.example.booklify.model.UserModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileApi {

    @FormUrlEncoded
    @POST("profile/picture")
    Call<UserModel> addProfilePicture(@FieldMap Map<Object, String> fields);

    @PUT("profile/picture/{id}")
    Call<UserModel> putPicture(@Path("id") int id, @Body UserModel post);

}
