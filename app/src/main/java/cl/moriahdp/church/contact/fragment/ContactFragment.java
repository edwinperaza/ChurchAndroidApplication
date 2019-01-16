package cl.moriahdp.church.contact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.contact.model.ContactModel;
import cl.moriahdp.church.contact.presenter.ContactPresenter;
import cl.moriahdp.church.contact.view.ContactView;
import cl.moriahdp.church.utils.bus.BusProvider;
import cl.moriahdp.church.utils.data.ApiUtils;

public class ContactFragment extends BaseFragment implements IBackPressedCallback {

    private static String LOVE_TAG = "ContactFragment";
    private View mRoot;
    private ContactPresenter contactPresenter;
    private ContactView contactView;

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
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
        mRoot = inflater.inflate(R.layout.fragment_contact, container, false);
        contactView = new ContactView(this, mRoot, BusProvider.getInstance());
        contactPresenter = new ContactPresenter(new ContactModel(BusProvider.getInstance(), ApiUtils.getAPIService()), contactView);
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
            dashboardActivity.setToolbarTitle(R.string.contact_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(contactPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(contactPresenter);
    }


    public static String getLoveTag() {
        return LOVE_TAG;
    }
}
