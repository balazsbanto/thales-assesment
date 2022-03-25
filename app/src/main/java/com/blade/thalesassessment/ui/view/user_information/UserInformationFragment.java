package com.blade.thalesassessment.ui.view.user_information;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blade.thalesassessment.R;
import com.blade.thalesassessment.databinding.FragmentUserInformationBinding;
import com.blade.thalesassessment.ui.view.data.model.UserInformation;
import com.blade.thalesassessment.utils.AlertDialogBuilder;

public class UserInformationFragment extends Fragment {

    private UserInformationViewModel userInformationViewModel;
    private FragmentUserInformationBinding binding;

    public static UserInformationFragment newInstance() {
        return new UserInformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userInformationViewModel = new ViewModelProvider(this, new UserInformationViewModelFactory())
                .get(UserInformationViewModel.class);

        requestUserInformation();

        userInformationViewModel.getUserInformationObservable().observe(getViewLifecycleOwner(),
                new Observer<UserInformationViewState>() {
                    @Override
                    public void onChanged(UserInformationViewState userInformationViewState) {
                        if (userInformationViewState == null) {
                            return;
                        }
                        binding.loading.setVisibility(View.GONE);
                        if (userInformationViewState.getError() != null) {
                            showGetUserFailed(userInformationViewState.getError());
                        }
                        if (userInformationViewState.getSuccess() != null) {
                            updateUiWithUserInformation(userInformationViewState.getSuccess());
                        }
                    }
                });

        binding.logoutButton.setOnClickListener(v -> {
            userInformationViewModel.logOut();
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_userInformation_to_login);
        });

    }

    private void showGetUserFailed(String errorString) {
        if (getContext() != null) {
            AlertDialogBuilder.showErrorDialog(getContext(), errorString);
        }
    }

    private void requestUserInformation() {
        binding.loading.setVisibility(View.VISIBLE);
        userInformationViewModel.requestUserInformation();
    }

    private void updateUiWithUserInformation(UserInformation userInformation) {
        binding.userId.setText(getString(R.string.user_information_id, userInformation.getId()));
        binding.username.setText(getString(R.string.user_information_username,userInformation.getUsername()));
        binding.email.setText(getString(R.string.user_information_email,userInformation.getEmail()));
        binding.roles.setText(getString(R.string.user_information_roles,userInformation.getRoles().toString()));
        binding.firstName.setText(getString(R.string.user_information_firstname,userInformation.getFirstName()));
        binding.lastName.setText(getString(R.string.user_information_lastname,userInformation.getLastName()));
        binding.location.setText(getString(R.string.user_information_location,userInformation.getLocation()));
        binding.language.setText(getString(R.string.user_information_language,userInformation.getLanguage()));
        binding.accountNonExpired.setText(getString(R.string.user_information_isAccountNonExpired,String.valueOf(userInformation.isAccountNonExpired())));
        binding.accountNonLocked.setText(getString(R.string.user_information_isAccountNonLocked,String.valueOf(userInformation.isAccountNonLocked())));
        binding.credentialsNonExpired.setText(getString(R.string.user_information_isCredentialsNonExpired,String.valueOf(userInformation.isCredentialsNonExpired())));
        binding.enabled.setText(getString(R.string.user_information_isEnabled,String.valueOf(userInformation.isEnabled())));
        binding.creationDate.setText(getString(R.string.user_information_creationDate,userInformation.getCreationDate()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}