package cl.moriahdp.church.sermon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.SecretApplication;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.sermon.model.SermonModel;
import cl.moriahdp.church.sermon.presenter.SermonPresenter;
import cl.moriahdp.church.sermon.view.SermonView;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class SermonFragment extends BaseFragment implements IBackPressedCallback {

    private static String HOME_TAG = "SermonFragment";
    private View mRoot;
    private SermonPresenter sermonPresenter;

    public static SermonFragment newInstance() {
        Bundle args = new Bundle();
        SermonFragment fragment = new SermonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);
        sermonPresenter = new SermonPresenter(
                new SermonModel(
                        BusProvider.getInstance(),
                        ApiUtils.getAPIService(),
                        SecretApplication.getInstance().getTinyDB()),
                new SermonView(this, mRoot, BusProvider.getInstance()));
        return mRoot;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureActionBar();
    }

    public void configureActionBar() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.showToolbarWithoutIconProfile();
            dashboardActivity.setToolbarTitle(R.string.sermons_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(sermonPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(sermonPresenter);
    }

    public static String getHomeTag() {
        return HOME_TAG;
    }

    @Override
    public void onFragmentBackPressed() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.doBackPressed();
        }
    }

}
