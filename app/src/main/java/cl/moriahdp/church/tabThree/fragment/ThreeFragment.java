package cl.moriahdp.church.tabThree.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.tabThree.model.ThreeModel;
import cl.moriahdp.church.tabThree.presenter.ThreePresenter;
import cl.moriahdp.church.tabThree.view.ThreeView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class ThreeFragment extends BaseFragment implements IBackPressedCallback {

    private static String AWARENESS_TAG = "ThreeFragment";
    private View mRoot;
    private ThreePresenter threePresenter;
    private ThreeView threeView;

    public static ThreeFragment newInstance() {
        Bundle args = new Bundle();
        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentBackPressed() {

    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_three, container, false);
        threeView = new ThreeView(this, mRoot, BusProvider.getInstance());
        threePresenter = new ThreePresenter(new ThreeModel(BusProvider.getInstance(), ApiUtils.getAPIService()), threeView);
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
        BusProvider.register(threePresenter);
    }

    public static String getAwarenessTag() {
        return AWARENESS_TAG;
    }

}