package com.ironflowers.firebasetest.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);

        setupToolbar();
        setupFragment();
    }

    /**
     * Sets up the {@link HomeFragment} if this was not done yet.
     */
    private void setupFragment() {

        HomeFragment thisHomeFragment =
                (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.contentListFrame);

        if (thisHomeFragment == null) {

            thisHomeFragment = homeFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    thisHomeFragment, R.id.contentListFrame);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }
}
