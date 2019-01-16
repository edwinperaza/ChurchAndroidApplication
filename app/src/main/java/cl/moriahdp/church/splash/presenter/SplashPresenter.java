package cl.moriahdp.church.splash.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.splash.model.SplashModel;
import cl.moriahdp.church.splash.view.SplashView;

public class SplashPresenter  extends BasePresenter<SplashView> {

    private SplashModel model;
    private SplashView view;

    public SplashPresenter(SplashModel model, SplashView view) {
        super(model, view);
        this.model = model;
        this.view = view;
    }

    public void onResume(){
        model.startTimer();
    }

    @Subscribe
    public void onTimeUp(SplashModel.TimeUpEvent event) {
        view.goToDashBoard();
    }

    public void onPause() {
        model.cancelTimer();
    }
}
