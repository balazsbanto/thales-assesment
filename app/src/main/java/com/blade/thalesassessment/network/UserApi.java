package com.blade.thalesassessment.network;

import com.blade.thalesassessment.data.login.LoggedInUser;
import com.blade.thalesassessment.data.user_information.UserInformation;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApi {

    @FormUrlEncoded
    @POST("auth/v2/oauth/token")
    Single<LoggedInUser> loginUser(@Field("grant_type") String grantType,
                                   @Field("username") String username,
                                   @Field("password") String password);

    @GET("user/v2/account")
    Single<UserInformation> getUserInformation(@Header("Authorization") String token);
}