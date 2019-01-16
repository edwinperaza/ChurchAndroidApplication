package cl.moriahdp.church.login.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.login.model.LoginModel;
import cl.moriahdp.church.login.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView>{

    private LoginModel mModel;
    private LoginView mView;

    public LoginPresenter(LoginModel model, LoginView view) {
        super(model, view);
        this.mModel = model;
        this.mView = view;
    }

    public void onResumed() {
        mView.onResumed();
    }

    @Subscribe
    public void onLoginOnClickEvent (LoginView.LoginOnClickEvent event) {
//        mModel.loginUser(event.getLoginModelObject());
        mView.loginSuccess();
    }

    @Subscribe
    public void onLoginSuccess (LoginModel.LoginSuccess event) {
        mView.loginSuccess();
    }


    @Subscribe
    public void onLoginFailure (LoginModel.LoginFailure event) {
        mView.loginFailure();
    }

    @Subscribe
    public void onResetPassword(LoginView.ResetPasswordEvent event) {
        mModel.resetPassword(event.getEmail());
    }

    @Subscribe
    public void onResetPassword(LoginModel.ResetPasswordSuccess event) {
        mView.resetSuccess();
    }

    @Subscribe
    public void onResetPassword(LoginModel.ResetPasswordFailure event) {
        mView.resetFailure();
    }
}