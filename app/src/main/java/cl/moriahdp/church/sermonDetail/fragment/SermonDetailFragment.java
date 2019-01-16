package cl.moriahdp.church.sermonDetail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.baseclasses.IBackPressedCallback;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.sermonDetail.model.SermonDetailModel;
import cl.moriahdp.church.sermonDetail.presenter.SermonDetailPresenter;
import cl.moriahdp.church.sermonDetail.view.SermonDetailView;
import cl.moriahdp.church.utils.bus.BusProvider;

public class SermonDetailFragment extends BaseFragment implements IBackPressedCallback {

    private static String SERMON_DETAIL_TAG = "SermonDetailFragment";
    private View mRoot;
    private SermonDetailPresenter sermonDetailPresenter;

    public static SermonDetailFragment newInstance(SermonModelObject sermon) {
        Bundle args = new Bundle();
        args.putSerializable("Sermon", sermon);
        SermonDetailFragment fragment = new SermonDetailFragment();
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
        mRoot = inflater.inflate(R.layout.fragment_sermon_detail, container, false);

        SermonModelObject sermon = new SermonModelObject();
        if (getArguments() != null && getArguments().containsKey("Sermon")) {
            sermon = (SermonModelObject) getArguments().getSerializable("Sermon");
        }

        sermonDetailPresenter = new SermonDetailPresenter(
                new SermonDetailModel(BusProvider.getInstance(), sermon),
                new SermonDetailView(this, mRoot, BusProvider.getInstance()));
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
            dashboardActivity.configureToolbarBackArrow(true);
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
        BusProvider.register(sermonDetailPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(sermonDetailPresenter);
    }

    public static String getSermonDetailTag() {
        return SERMON_DETAIL_TAG;
    }

    @Override
    public void onFragmentBackPressed() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.doBackPressed();
        }
    }

}
