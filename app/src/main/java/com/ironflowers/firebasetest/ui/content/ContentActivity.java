package com.ironflowers.firebasetest.ui.content;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Displays one page of content in a fragment.
 */
public class ContentActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_CONTENT_ITEM_ID = "EXTRA_CONTENT_ITEM_ID",
            EXTRA_TOOLBAR_TITLE = "EXTRA_TOOLBAR_TITLE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);
        ButterKnife.bind(this);

        setupToolbar();
        setupFragment();
    }

    /**
     * Sets up the {@link ContentFragment} if this was not done yet.
     */
    private void setupFragment() {

        ContentFragment thisContentFragment =
                (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.contentItemFrame);

        if (thisContentFragment == null) {

            thisContentFragment = contentFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    thisContentFragment, R.id.contentItemFrame);
        }
    }

    private void setupToolbar() {

        toolbar.setTitle(getIntent().getStringExtra(ContentActivity.EXTRA_TOOLBAR_TITLE));
        setSupportActionBar(toolbar);
    }
}
