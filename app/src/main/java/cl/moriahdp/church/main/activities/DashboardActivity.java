package cl.moriahdp.church.main.activities;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.squareup.otto.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.contact.fragment.ContactFragment;
import cl.moriahdp.church.sermon.fragment.SermonFragment;
import cl.moriahdp.church.main.events.DashboardEvent;
import cl.moriahdp.church.main.views.DashboardView;
import cl.moriahdp.church.profile.fragment.ProfileFragment;
import cl.moriahdp.church.events.fragment.EventsFragment;
import cl.moriahdp.church.tabThree.fragment.ThreeFragment;
import cl.moriahdp.church.connect.fragment.ConnectFragment;
import cl.moriahdp.church.utils.Constants;
import cl.moriahdp.church.utils.bus.BusProvider;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class DashboardActivity extends BaseActivity {

    private static final String DASHBOARD_ACTIVITY_TAG = DashboardActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Fragment mCurrentFragment;
    private DashboardView mDashboardView = null;
    private String mCurrentFragmentTag;
    private static DashboardActivity instance;

    private boolean mDoubleBackToExitPressedOnce;
    private Menu mMenu;

    // variable to track event time
    private static final int LONG_TIME_SECONDS_REDIRECT = 800;
    private long mLastClickTime = 0;

    //Toolbar
    protected RelativeLayout toolbarTitleContainer;
    protected TextView toolbarTitle;
    protected RelativeLayout iconProfileContainer;
    protected ImageView iconProfile;
    protected RelativeLayout iconProfileEditContainer;
    protected ImageView iconProfileEdit;
    protected LinearLayout linearLayoutContainer;

    private boolean isFromProfileChangeLanguage = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        instance = this;
        mDoubleBackToExitPressedOnce = false;
        bindViewElements();
        if (getIntent().getExtras() != null && getIntent().hasExtra(Constants.FROM_PROFILE_CHANGE_LANGUAGE)) {
            isFromProfileChangeLanguage = getIntent().getExtras().getBoolean(Constants.FROM_PROFILE_CHANGE_LANGUAGE);
        }
    }

    /**
     * Bind element from view to Activity
     */
    public void bindViewElements() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_more));
        setSupportActionBar(toolbar);
        linearLayoutContainer = toolbar.findViewById(R.id.ll_container);

        toolbarTitleContainer = toolbar.findViewById(R.id.title_container);
        toolbarTitle = toolbar.findViewById(R.id.tv_toolbar_title);

        iconProfileContainer = toolbar.findViewById(R.id.icon_profile_container);
        iconProfile = toolbar.findViewById(R.id.iv_icon_profile);

        iconProfileEditContainer = toolbar.findViewById(R.id.icon_edit_container);
        iconProfileEdit = toolbar.findViewById(R.id.iv_icon_edit);
        setOnClickIconMenu();
    }

    public void showToolbarWithIconProfile() {
        toolbarTitleContainer.setVisibility(View.VISIBLE);
        iconProfileContainer.setVisibility(View.VISIBLE);
        iconProfileEditContainer.setVisibility(View.GONE);
    }

    public void showToolbarWithIconProfileEdit() {
        toolbarTitleContainer.setVisibility(View.VISIBLE);
        iconProfileContainer.setVisibility(View.GONE);
        iconProfileEditContainer.setVisibility(View.VISIBLE);
    }

    public void showToolbarWithoutIconProfile() {
        toolbarTitleContainer.setVisibility(View.VISIBLE);
        iconProfileContainer.setVisibility(View.GONE);
        iconProfileEditContainer.setVisibility(View.GONE);
    }

    public void setIconProfileEdit(Drawable image){
        iconProfileEdit.setImageDrawable(image);
    }

    public ImageView getIconProfileEdit() {
        return iconProfileEdit;
    }

    public ImageView getIconProfile() {
        return iconProfile;
    }

    public void setToolbarTitle(int text){
        toolbarTitle.setText(text);
    }

    public void setOnClickIconMenu(){
        iconProfileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentWithStack(
                        R.id.tab_container,
                        ProfileFragment.newInstance(),
                        ProfileFragment.getProfileTag());
            }
        });
    }

    public static DashboardActivity getInstance() {
        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        if (mDashboardView == null)
            mDashboardView = new DashboardView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    /**
     * Show a new fragment from Activity. Call super goToFragment at BaseActivity and
     * update mCurrentFragment to be able to go back when user press Back at 2nd, 3rd or other
     * level fragments
     *
     * @param layout   to be replace by new fragment
     * @param fragment to be shown to user
     * @param tag      to customize process
     */
    @Override
    public void goToFragment(int layout, Fragment fragment, String tag) {
        super.goToFragment(layout, fragment, tag);
        mCurrentFragment = fragment;
        mCurrentFragmentTag = tag;
    }

    public void goToFragment(Fragment fragment, String tag) {
        mCurrentFragmentTag = tag;
        mCurrentFragment = fragment;
        super.goToFragment(R.id.tab_container, fragment, tag);
    }

    @Override
    public void goToFragmentWithStack(int fragmentContainer, Fragment fragment, String tag) {
        mCurrentFragment = fragment;
        setmCurrentFragmentTag(tag);
        super.goToFragmentWithStack(fragmentContainer, fragment, tag);
    }

    @Subscribe
    public void setCurrentFragmentInTab(DashboardEvent dashboardEvent) {
        mCurrentFragment = dashboardEvent.getBaseFragment();
        mCurrentFragmentTag = dashboardEvent.getFragmentTag();
        goToFragmentWithStack(dashboardEvent.getLayoutContainerID(), mCurrentFragment, dashboardEvent.getFragmentTag());
    }

    /**
     * When user is at first level fragment like myProfileFragment and press back the app
     * show initial fragment clear
     */
    public void backToSermonsFragment() {
        setSelectedTab(R.id.sermons);
    }

    public void backToEventsFragment() {
        setSelectedTab(R.id.events);
    }

    public void backToAwarenessFragment() {
        setSelectedTab(R.id.awareness);
    }

    public void backToConnectFragment() {
        setSelectedTab(R.id.connect);
    }

    public void backToContactFragment() {
        setSelectedTab(R.id.contact);
    }

    /**
     * Select specific tabs at bottomBar, also check if bottom bar is null.
     *
     * @param tabId id of tab to be selected
     */
    public void setSelectedTab(int tabId) {
        // Preventing multiple clicks, using threshold of 10 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < LONG_TIME_SECONDS_REDIRECT) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        BottomBar bottomBar = mDashboardView.getmBottomBar();
        if (bottomBar != null) {
            bottomBar.selectTabWithId(tabId);
        } else {
            Log.d(DASHBOARD_ACTIVITY_TAG, "BottomBar is null");
            //TODO implement behavior when bottomBar is null
        }
    }

    public int getSelectedTab() {
        BottomBar bottomBar = mDashboardView.getmBottomBar();
        return bottomBar.getCurrentTabId();
    }

    public void hideBottomBar(boolean hide) {

        if (mDashboardView != null && mDashboardView.getmBottomBar() != null) {
            if (hide) {
                mDashboardView.getmBottomBar().setVisibility(View.GONE);
            } else {
                mDashboardView.getmBottomBar().setVisibility(View.VISIBLE);
            }
        }
    }

    public String getmCurrentFragmentTag() {
        return mCurrentFragmentTag;
    }

    public void setmCurrentFragmentTag(String mCurrentFragmentTag) {
        this.mCurrentFragmentTag = mCurrentFragmentTag;
    }

    public Fragment getmCurrentFragment() {
        return mCurrentFragment;
    }

    public String getCurrentSelectedFragment() {
        return mCurrentFragmentTag;
    }


    public void setEditProfileIcon() {
            mMenu.getItem(0).setIcon(R.drawable.ic_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getLastFragmentFromStack() {
        int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
        android.support.v4.app.FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
        String previousTag = backEntry.getName();
        setmCurrentFragmentTag(previousTag);
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(previousTag);

        if (previousTag != null && previousTag.equals(SermonFragment.getHomeTag())) {
            setSelectedTab(R.id.sermons);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragmentTag.equals(ConnectFragment.getFaithTag()) ||
                mCurrentFragmentTag.equals(EventsFragment.getObjectiveTag()) ||
                mCurrentFragmentTag.equals(ThreeFragment.getAwarenessTag()) ||
                mCurrentFragmentTag.equals(ContactFragment.getLoveTag())) {
            getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
            setSelectedTab(R.id.sermons);
            return;
        }

        if (mCurrentFragment instanceof SermonFragment) {
            ((SermonFragment) mCurrentFragment).onFragmentBackPressed();
        } else if (mCurrentFragment instanceof ProfileFragment) {
            ((ProfileFragment) mCurrentFragment).onFragmentBackPressed();
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
//        getLastFragmentFromStack();
    }

    public boolean isFromProfileChangeLanguage() {
        return isFromProfileChangeLanguage;
    }

    public void doBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            getSupportFragmentManager().popBackStackImmediate();
            super.onBackPressed();
            return;
        }
        mDoubleBackToExitPressedOnce = true;
//        mDashboardView.showExitSnackbar();
        Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                mDoubleBackToExitPressedOnce = false;
            }
        }, 2500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //mDashboardView.showCallAlertDialog();
        } else {
            //TODO: show snackbar with error
        }
    }

    public DashboardView getView() {
        return mDashboardView;
    }

}
