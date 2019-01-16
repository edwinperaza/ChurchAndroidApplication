package cl.moriahdp.church.events.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.SecretApplication;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.events.model.EventsModel;
import cl.moriahdp.church.events.presenter.EventsPresenter;
import cl.moriahdp.church.events.view.EventsView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class EventsFragment extends BaseFragment implements IBackPressedCallback {

    private static String OBJECTIVE_TAG = "EventsFragment";
    private View mRoot;
    private EventsPresenter eventsPresenter;
    private EventsView eventsView;

    public static EventsFragment newInstance() {
        Bundle args = new Bundle();
        EventsFragment fragment = new EventsFragment();
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
        mRoot = inflater.inflate(R.layout.fragment_events, container, false);
        eventsView = new EventsView(this, mRoot, BusProvider.getInstance());
        eventsPresenter = new EventsPresenter(
                new EventsModel(
                        BusProvider.getInstance(),
                        ApiUtils.getAPIService(),
                        SecretApplication.getInstance().getTinyDB()),
                eventsView);
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
            dashboardActivity.setToolbarTitle(R.string.events_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    public static String getObjectiveTag() {
        return OBJECTIVE_TAG;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(eventsPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(eventsPresenter);
    }

}
