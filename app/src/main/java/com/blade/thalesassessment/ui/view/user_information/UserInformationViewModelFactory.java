package com.blade.thalesassessment.ui.view.user_information;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blade.thalesassessment.ui.view.data.LoginRemoteDataSource;
import com.blade.thalesassessment.ui.view.data.LoginRepository;
import com.blade.thalesassessment.ui.view.data.network.NetworkManager;
import com.blade.thalesassessment.ui.view.ui.login.LoginViewModel;

public class UserInformationViewModelFactory implements ViewModelProvider.Factory {

@NonNull
@Override
@SuppressWarnings("unchecked")
public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserInformationViewModel.class)) {
        return (T) new UserInformationViewModel(LoginRepository.getInstance(new LoginRemoteDataSource(
        NetworkManager.getApi()
        )));
        } else {
        throw new IllegalArgumentException("Unknown ViewModel class");
        }
        }
        }