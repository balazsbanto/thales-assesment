package com.blade.thalesassessment.data;

import com.blade.thalesassessment.data.login.LoggedInUser;
import com.blade.thalesassessment.data.user_information.UserInformation;
import com.blade.thalesassessment.network.UserApi;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class UserRemoteDataSource {

    private UserApi userApi;

    public UserRemoteDataSource(UserApi userApi) {
        this.userApi = userApi;
    }

    public Single<LoggedInUser> login(String username, String password) {

//        return userApi.loginUser("password", username, password)
//                .subscribeOn(Schedulers.io());

        return userApi.loginUser("password", "assessment1", "Password@12")
                .subscribeOn(Schedulers.io());
    }

    public Single<UserInformation> getUserInformation(String token) {
        return userApi.getUserInformation("Bearer " + token).subscribeOn(Schedulers.io());
    }

}