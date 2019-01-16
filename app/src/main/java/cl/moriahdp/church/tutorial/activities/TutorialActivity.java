package cl.moriahdp.church.tutorial.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.pixelcan.inkpageindicator.InkPageIndicator;

import butterknife.ButterKnife;
import cl.moriahdp.church.R;
import cl.moriahdp.church.SecretApplication;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.registry.modelObject.UserModelObject;
import cl.moriahdp.church.tutorial.fragment.FirstFragment;
import cl.moriahdp.church.tutorial.fragment.FourthFragment;
import cl.moriahdp.church.tutorial.fragment.SecondFragment;
import cl.moriahdp.church.tutorial.fragment.ThirdFragment;
import cl.moriahdp.church.tutorial.model.TutorialModel;
import cl.moriahdp.church.tutorial.presenter.TutorialPresenter;
import cl.moriahdp.church.tutorial.view.TutorialView;
import cl.moriahdp.church.utils.bus.BusProvider;


public class TutorialActivity extends BaseActivity {

    public static final String TUTORIAL_SHOWED = "tutorial_showed";
    private TutorialPresenter presenter;
    private TutorialView view ;
    FragmentPagerAdapter adapterViewPager;
    TextView skipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager vpPager = findViewById(R.id.vp_tutorial);
        InkPageIndicator inkPageIndicator = findViewById(R.id.indicator);
        skipTextView = findViewById(R.id.tv_tutorial_skip);

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        inkPageIndicator.setViewPager(vpPager);

        view = new TutorialView(this, BusProvider.getInstance());
        ButterKnife.bind(view, this);
        presenter = new TutorialPresenter(new TutorialModel(BusProvider.getInstance()), view);
        hideToolbar();

        SecretApplication.getInstance().getTinyDB().putBoolean(TUTORIAL_SHOWED, true);

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserModelObject.isUserLoggedIn()) {
                    view.goToDashBoard();
                } else {
                    view.goToLogin();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 1 - This will show FirstFragment
                    return FirstFragment.newInstance("Page # 1");
                case 1: // Fragment # 2 - This will show FirstFragment different title
                    return SecondFragment.newInstance("Page # 2");
                case 2: // Fragment # 3 - This will show SecondFragment
                    return ThirdFragment.newInstance("Page # 3");
                case 3: // Fragment # 4 - This will show SecondFragment
                    return FourthFragment.newInstance("Page # 4");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
