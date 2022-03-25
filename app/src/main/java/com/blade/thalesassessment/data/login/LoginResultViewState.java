package com.blade.thalesassessment.data.login;

import androidx.annotation.Nullable;

public class LoginResultViewState {
    @Nullable
    private LoggedInUser success;
    @Nullable
    private String error;

    public LoginResultViewState(@Nullable String error) {
        this.error = error;
    }

    public LoginResultViewState() {
    }

    public LoginResultViewState(@Nullable LoggedInUser success) {
        this.success = success;
    }

    @Nullable
    public LoggedInUser getSuccess() {
        return success;
    }

    @Nullable
    public String getError() {
        return error;
    }
}