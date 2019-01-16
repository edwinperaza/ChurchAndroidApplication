package cl.moriahdp.church.splash.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.splash.model.SplashModel;
import cl.moriahdp.church.splash.presenter.SplashPresenter;
import cl.moriahdp.church.splash.view.SplashView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class SplashActivity extends BaseActivity {

    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashView splashView = new SplashView(this, BusProvider.getInstance());
        mSplashPresenter = new SplashPresenter(new SplashModel(BusProvider.getInstance(), ApiUtils.getAPIService()), splashView);
        hideToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(mSplashPresenter);
        mSplashPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(mSplashPresenter);
        mSplashPresenter.onPause();
    }
}