package cl.moriahdp.church.connect.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.connect.adapter.ConnectAdapter;
import cl.moriahdp.church.connect.model.ConnectModel;
import cl.moriahdp.church.connect.view.ConnectView;

public class ConnectPresenter extends BasePresenter<ConnectView> {

    private ConnectModel model;
    private ConnectView view;

    public ConnectPresenter(ConnectModel model, ConnectView view) {
        super(model, view);
        this.model = model;
        this.view = view;
        model.requestConnect();
    }

    @Subscribe
    public void onRequestConnectsSuccess(ConnectModel.RequestConnectsSuccess event) {
        view.setRecycler(event.getConnects());
    }

    @Subscribe
    public void onConnectItemPressed(ConnectAdapter.OnConnectItemPressed event) {
        view.goToUrl(event.getUrl());
    }
}
