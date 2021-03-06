package com.blade.thalesassessment.data;

import androidx.annotation.NonNull;

import com.blade.thalesassessment.data.login.LoggedInUser;
import com.blade.thalesassessment.data.user_information.UserInformation;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UserRepository {

    private static volatile UserRepository instance;

    private UserRemoteDataSource dataSource;

    private LoggedInUser user = null;

    // private constructor : singleton access
    private UserRepository(UserRemoteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserRepository getInstance(UserRemoteDataSource dataSource) {
        if (instance == null) {
            instance = new UserRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
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

    public Single<UserInformation> getUserInformation() {
        // handle login
        return dataSource.getUserInformation(user.getAccessToken());
    }
}