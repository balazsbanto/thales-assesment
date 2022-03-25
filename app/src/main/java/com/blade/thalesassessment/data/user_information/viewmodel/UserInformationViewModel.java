package com.blade.thalesassessment.data.user_information.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blade.thalesassessment.data.UserRepository;
import com.blade.thalesassessment.data.user_information.UserInformation;
import com.blade.thalesassessment.data.user_information.UserInformationViewState;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class UserInformationViewModel extends ViewModel {
    private MutableLiveData<UserInformationViewState> userInformationLiveData = new MutableLiveData<>();

    private UserRepository userRepository;

    public UserInformationViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<UserInformationViewState> getUserInformationObservable() {
        return userInformationLiveData;
    }

    public void requestUserInformation() {
        userRepository.getUserInformation().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserInformation>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull UserInformation userInformation) {
                        userInformationLiveData.setValue(new UserInformationViewState(userInformation));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        userInformationLiveData.setValue(new UserInformationViewState(e.getMessage()));
                    }
                });
    }

    public void logOut() {
        userRepository.logout();
    }
}