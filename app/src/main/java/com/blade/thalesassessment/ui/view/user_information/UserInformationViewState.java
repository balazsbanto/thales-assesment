package com.blade.thalesassessment.ui.view.user_information;

import androidx.annotation.Nullable;

import com.blade.thalesassessment.ui.view.data.model.UserInformation;

class UserInformationViewState {
    @Nullable
    private UserInformation success;
    @Nullable
    private String error;

    UserInformationViewState(@Nullable String error) {
        this.error = error;
    }

    UserInformationViewState(@Nullable UserInformation success) {
        this.success = success;
    }

    @Nullable
    UserInformation getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }
}