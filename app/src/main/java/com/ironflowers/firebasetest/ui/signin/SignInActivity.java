package com.ironflowers.firebasetest.ui.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.databinding.SignInActivityBinding;
import com.ironflowers.firebasetest.ui.home.HomeActivity;
import com.ironflowers.firebasetest.ui.signin.vm.SignInViewModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Signs in the user anonymously and continues into the app on success.
 */
public class SignInActivity extends DaggerAppCompatActivity implements SignInContract.View {

    @Inject
    SignInContract.Presenter presenter;

    private SignInActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.sign_in_activity);
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    public void bind(SignInViewModel viewModel) {
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }

    @Override
    public void continueIntoApp() {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @OnClick(R.id.buttonContinue)
    public void onContinueButtonClicked() {
        presenter.onContinueButtonClicked();
    }
}