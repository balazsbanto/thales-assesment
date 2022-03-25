package com.blade.thalesassessment.ui.view.data;

import com.blade.thalesassessment.ui.view.data.model.LoginResponse;
import com.blade.thalesassessment.ui.view.data.network.UserApi;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginRemoteDataSource {

    private UserApi userApi;

    public LoginRemoteDataSource(UserApi userApi) {
        this.userApi = userApi;
    }

    public Single<LoginResponse> login(String username, String password) {

//        return userApi.loginUser("password", username, password)
//                .subscribeOn(Schedulers.io());

        return userApi.loginUser("password", "assessment1", "Password@12")
                .subscribeOn(Schedulers.io());
    }


//    public void logout() {
//        // TODO: revoke authentication
//    }
}