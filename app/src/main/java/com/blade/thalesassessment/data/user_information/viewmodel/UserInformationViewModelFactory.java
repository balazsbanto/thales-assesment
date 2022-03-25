package com.blade.thalesassessment.data.user_information.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blade.thalesassessment.data.UserRemoteDataSource;
import com.blade.thalesassessment.data.UserRepository;
import com.blade.thalesassessment.network.NetworkManager;

public class UserInformationViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserInformationViewModel.class)) {
            return (T) new UserInformationViewModel(UserRepository.getInstance(new UserRemoteDataSource(
                    NetworkManager.getApi()
            )));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}