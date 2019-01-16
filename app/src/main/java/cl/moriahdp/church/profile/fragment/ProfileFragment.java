package cl.moriahdp.church.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.profile.model.ProfileModel;
import cl.moriahdp.church.profile.presenter.ProfilePresenter;
import cl.moriahdp.church.profile.view.ProfileView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class ProfileFragment extends BaseFragment implements IBackPressedCallback {

    private static String PROFILE_TAG = "ProfileFragment";
    private View mRoot;
    private ProfilePresenter profilePresenter;
    private ProfileView profileView;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onFragmentBackPressed() {
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_profile, container, false);
        profileView = new ProfileView(this, mRoot, BusProvider.getInstance());
        profilePresenter = new ProfilePresenter(new ProfileModel(BusProvider.getInstance(), ApiUtils.getAPIService()), profileView);
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
            dashboardActivity.showToolbarWithIconProfileEdit();
            dashboardActivity.setToolbarTitle(R.string.profile_tab);
            dashboardActivity.hideBottomBar(true);
            dashboardActivity.configureToolbarBackArrow(true);
        }
    }

    public static String getProfileTag() {
        return PROFILE_TAG;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(profilePresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(profilePresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
