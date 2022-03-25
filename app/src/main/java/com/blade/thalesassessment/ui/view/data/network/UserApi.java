package com.blade.thalesassessment.ui.view.data.network;

import com.blade.thalesassessment.ui.view.data.model.LoggedInUser;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

    @FormUrlEncoded
    @POST("auth/v2/oauth/token")
    Single<LoggedInUser> loginUser(@Field("grant_type") String grantType,
                                   @Field("username") String username,
                                   @Field("password") String password);
}