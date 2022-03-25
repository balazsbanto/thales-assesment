package com.blade.thalesassessment.data.login.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blade.thalesassessment.data.UserRepository;
import com.blade.thalesassessment.data.login.LoggedInUser;
import com.blade.thalesassessment.data.login.LoginResultViewState;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResultViewState> loginResult = new MutableLiveData<>();
    private UserRepository userRepository;

    LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<LoginResultViewState> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        userRepository.login(username, password).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoggedInUser>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull LoggedInUser loggedInUser) {
                        loginResult.setValue(new LoginResultViewState());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginResult.setValue(new LoginResultViewState(e.getMessage()));
                    }
                });
    }
}