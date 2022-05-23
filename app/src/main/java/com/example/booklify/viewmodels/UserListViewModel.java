package com.example.booklify.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.booklify.model.UserModel;
import com.example.booklify.request.Services;
import com.example.booklify.response.BookResponse;
import com.example.booklify.utils.ProfileApi;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends ViewModel {

    private MutableLiveData<UserModel> userList;

    public UserListViewModel() {
        userList = new MutableLiveData<>();
    }


    public MutableLiveData<UserModel> getUserListObserver() {
        return userList;
    }



    public void postProfilePicture() {
        HashMap<Object,String> field = new HashMap<>();
        field.put("1","Adz4a");
        ProfileApi apiService = Services.getProfileApi();
        Call<UserModel> call = apiService.addProfilePicture(field);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                userList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                userList.postValue(null);
            }
        });
    }

    public void putProfilePicture() {
        UserModel post = new UserModel("adil","adil@gmail.com");
        ProfileApi apiService = Services.getProfileApi();
        Call<UserModel> call = apiService.putPicture(1,post);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                userList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                userList.postValue(null);
            }
        });
    }

}