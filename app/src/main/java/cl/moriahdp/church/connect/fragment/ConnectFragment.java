package cl.moriahdp.church.connect.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.connect.model.ConnectModel;
import cl.moriahdp.church.connect.presenter.ConnectPresenter;
import cl.moriahdp.church.connect.view.ConnectView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class ConnectFragment extends BaseFragment implements IBackPressedCallback {

    private static String FAITH_TAG = "ConnectFragment";
    private View mRoot;
    private ConnectPresenter connectPresenter;
    private ConnectView connectView;

    public static ConnectFragment newInstance() {
        Bundle args = new Bundle();
        ConnectFragment fragment = new ConnectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onFragmentBackPressed() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_connects, container, false);
        connectView = new ConnectView(this, mRoot, BusProvider.getInstance());
        connectPresenter = new ConnectPresenter(new ConnectModel(BusProvider.getInstance(), ApiUtils.getAPIService()), connectView);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureActionBar();
    }

    public void configureActionBar() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.showToolbarWithIconProfile();
            dashboardActivity.setToolbarTitle(R.string.connect_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(connectPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(connectPresenter);
    }

    public static String getFaithTag() {
        return FAITH_TAG;
    }
}
