package com.blade.thalesassessment.data.login.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blade.thalesassessment.data.LoginRepository;
import com.blade.thalesassessment.data.login.LoggedInUser;
import com.blade.thalesassessment.data.login.LoggedInUserView;
import com.blade.thalesassessment.data.login.LoginResult;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoggedInUser>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
                    }

                    @Override
                    public void onSuccess(@NonNull LoggedInUser loggedInUser) {
                        loginResult.setValue(new LoginResult(new LoggedInUserView(loggedInUser.getUserId())));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginResult.setValue(new LoginResult(e.getMessage()));
                    }
                });

//        if (result instanceof Result.Success) {
//            LoginResponse data = ((Result.Success<LoginResponse>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }
    }
}