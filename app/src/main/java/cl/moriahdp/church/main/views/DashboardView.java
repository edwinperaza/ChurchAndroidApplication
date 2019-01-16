package cl.moriahdp.church.main.views;

import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.baseclasses.BaseActivityView;
import cl.moriahdp.church.baseclasses.BaseFragment;
import cl.moriahdp.church.events.fragment.EventsFragment;
import cl.moriahdp.church.sermon.fragment.SermonFragment;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.main.events.DashboardEvent;
import cl.moriahdp.church.contact.fragment.ContactFragment;
import cl.moriahdp.church.tabThree.fragment.ThreeFragment;
import cl.moriahdp.church.connect.fragment.ConnectFragment;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class DashboardView extends BaseActivityView {

    private static final String TAG = "DashboardView";

    FrameLayout tabContainer;
    BottomBar mBottomBar;

    public DashboardView(BaseActivity activity) {
        super(activity);
        tabContainer = activity.findViewById(R.id.tab_container);
        mBottomBar = activity.findViewById(R.id.bottomBar);

        mBottomBar.setDefaultTab(R.id.sermons);
        mBottomBar.setActiveTabColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        addBottomTabsBarListener();
    }

    @Override
    public void setDashboardEvent(BaseFragment baseFragment, String tag) {
        super.setDashboardEvent(baseFragment, tag);
    }

    private void addBottomTabsBarListener() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);

                switch (tabId) {
                    case R.id.sermons:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(SermonFragment.newInstance(), SermonFragment.getHomeTag());
                        }
                        break;
                    case R.id.events:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(EventsFragment.newInstance(), EventsFragment.getObjectiveTag());
                        }
                        break;

                    case R.id.connect: {
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(ConnectFragment.newInstance(), ConnectFragment.getFaithTag());
                        }
                        break;
                    }

                    case R.id.awareness: {
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(ThreeFragment.newInstance(), ThreeFragment.getAwarenessTag());
                        }
                        break;
                    }

                    case R.id.contact: {
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(ContactFragment.newInstance(), ContactFragment.getLoveTag());
                        }
                        break;
                    }
                }
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

                switch (tabId) {
                    case R.id.sermons:
                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(SermonFragment.getHomeTag())) {
                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
                            sendDashboardEvent(SermonFragment.newInstance(), SermonFragment.getHomeTag());
                        }
                        break;

                    case R.id.events:
                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(EventsFragment.getObjectiveTag())) {
                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
                            sendDashboardEvent(EventsFragment.newInstance(), EventsFragment.getObjectiveTag());
                        }
                        break;

                    case R.id.connect:
                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(ConnectFragment.getFaithTag())) {
                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
                            sendDashboardEvent(ConnectFragment.newInstance(), ConnectFragment.getFaithTag());
                        }
                        break;

                    case R.id.awareness:
                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(ThreeFragment.getAwarenessTag())) {
                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
                            sendDashboardEvent(ThreeFragment.newInstance(), ThreeFragment.getAwarenessTag());
                        }
                        break;

                    case R.id.contact:
                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(ContactFragment.getLoveTag())) {
                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
                            sendDashboardEvent(ContactFragment.newInstance(), ContactFragment.getLoveTag());
                        }
                        break;
                }
            }
        });
    }

    public void setSelectedTab(int tabId) {
        mBottomBar.selectTabWithId(tabId);
    }

    public boolean checkCurrentTabSelected(int tabId) {
        return tabId == mBottomBar.getCurrentTab().getId();
    }

    public BottomBar getmBottomBar() {
        return mBottomBar;
    }

    private void sendDashboardEvent(BaseFragment baseFragment, String tag) {
        mBus.post(new DashboardEvent(baseFragment, R.id.tab_container, tag));
    }

    private void sendDashboardEvent(DashboardEvent dashboardEvent) {
        mBus.post(dashboardEvent);
    }
}