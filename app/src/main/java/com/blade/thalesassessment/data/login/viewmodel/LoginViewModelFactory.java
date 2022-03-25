package com.blade.thalesassessment.data.login.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.blade.thalesassessment.data.UserRemoteDataSource;
import com.blade.thalesassessment.data.UserRepository;
import com.blade.thalesassessment.network.NetworkManager;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(UserRepository.getInstance(new UserRemoteDataSource(
                    NetworkManager.getApi()
            )));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}