package com.blade.thalesassessment.ui.view.user_information;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blade.thalesassessment.ui.view.data.LoginRepository;
import com.blade.thalesassessment.ui.view.data.model.UserInformation;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class UserInformationViewModel extends ViewModel {
    private MutableLiveData<UserInformationViewState> userInformationLiveData = new MutableLiveData<>();

    private LoginRepository loginRepository;

    UserInformationViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<UserInformationViewState> getUserInformationObservable() {
        return userInformationLiveData;
    }

    public void requestUserInformation() {
        loginRepository.getUserInformation().observeOn(AndroidSchedulers.mainThread())
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
        loginRepository.logout();
    }
}