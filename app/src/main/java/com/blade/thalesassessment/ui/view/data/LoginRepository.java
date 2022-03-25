package com.blade.thalesassessment.ui.view.data;

import androidx.annotation.NonNull;

import com.blade.thalesassessment.ui.view.data.model.LoggedInUser;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginRemoteDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginRemoteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginRemoteDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
//        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Single<LoggedInUser> login(String username, String password) {
        // handle login
        return dataSource.login(username, password).map(new Function<LoggedInUser, LoggedInUser>() {
            @Override
            public LoggedInUser apply(@NonNull LoggedInUser loggedInUser) throws Exception {
                setLoggedInUser(loggedInUser);
                return loggedInUser;
            }
        });
    }
}