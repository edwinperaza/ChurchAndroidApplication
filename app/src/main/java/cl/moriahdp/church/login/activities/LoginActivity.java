package cl.moriahdp.church.login.activities;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.login.model.LoginModel;
import cl.moriahdp.church.login.presenter.LoginPresenter;
import cl.moriahdp.church.login.view.LoginView;
import cl.moriahdp.church.utils.bus.BusProvider;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginView loginView = new LoginView(this, BusProvider.getInstance());
        ButterKnife.bind(loginView, this);
        mLoginPresenter = new LoginPresenter(new LoginModel(BusProvider.getInstance()), loginView);
        hideToolbar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(mLoginPresenter);
        mLoginPresenter.onResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(mLoginPresenter);
    }
}