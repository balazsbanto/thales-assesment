package com.blade.thalesassessment.data.user_information;

import androidx.annotation.Nullable;

public class UserInformationViewState {
    @Nullable
    private UserInformation success;
    @Nullable
    private String error;

    public UserInformationViewState(@Nullable String error) {
        this.error = error;
    }

    public UserInformationViewState(@Nullable UserInformation success) {
        this.success = success;
    }

    @Nullable
    public UserInformation getSuccess() {
        return success;
    }

    @Nullable
    public String getError() {
        return error;
    }
}