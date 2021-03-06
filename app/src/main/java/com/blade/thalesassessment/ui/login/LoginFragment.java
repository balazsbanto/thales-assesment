package com.blade.thalesassessment.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blade.thalesassessment.R;
import com.blade.thalesassessment.data.login.LoginResultViewState;
import com.blade.thalesassessment.data.login.viewmodel.LoginViewModel;
import com.blade.thalesassessment.data.login.viewmodel.LoginViewModelFactory;
import com.blade.thalesassessment.databinding.FragmentLoginBinding;
import com.blade.thalesassessment.utils.AlertDialogBuilder;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<LoginResultViewState>() {
            @Override
            public void onChanged(@Nullable LoginResultViewState loginResultViewState) {
                if (loginResultViewState == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResultViewState.getError() != null) {
                    showLoginFailed(loginResultViewState.getError());
                } else {
                    loginSuccessful();
                }
            }
        });
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void loginSuccessful() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_login_to_userInformation);
    }

    private void showLoginFailed(String errorString) {
        if (getContext() != null) {
            AlertDialogBuilder.showErrorDialog(getContext(), errorString);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}