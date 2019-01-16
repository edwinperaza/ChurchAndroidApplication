package cl.moriahdp.church.baseclasses;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;

public class BaseActivity extends AppCompatActivity {

    private ViewGroup mBaseActivityLayout;
    private ViewGroup mMainContainer;
    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBaseActivityLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_base, null);
        mMainContainer = mBaseActivityLayout.findViewById(R.id.main_container);
        getLayoutInflater().inflate(layoutResID, mMainContainer, true);
        setContentView(mBaseActivityLayout);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void goToFragment(int layout, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layout, fragment, tag).commit();
    }

    public void goToFragmentWithStack(int fragmentContainer, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentContainer, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public ViewGroup getMainContainer() {
        return mMainContainer;
    }

    public void configureToolbarBackArrow(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (show) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        }
        actionBar.setDisplayHomeAsUpEnabled(show);
    }

    public void hideToolbar(){
        toolbar.setVisibility(View.GONE);
    }
}