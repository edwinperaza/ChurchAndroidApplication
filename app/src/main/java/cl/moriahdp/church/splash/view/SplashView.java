package cl.moriahdp.church.splash.view;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.baseclasses.BaseActivityView;

public class SplashView extends BaseActivityView {

    private ConstraintLayout containerLayout;

    public SplashView(BaseActivity activity, Bus bus) {
        super(activity, bus);
        containerLayout = activity.findViewById(R.id.container_splash);

        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDashBoard();
            }
        });
    }

}
