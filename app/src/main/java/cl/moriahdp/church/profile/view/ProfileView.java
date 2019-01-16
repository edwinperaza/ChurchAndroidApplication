package cl.moriahdp.church.profile.view;

import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.profile.fragment.ProfileFragment;

public class ProfileView extends BaseFragmentView {

    public ProfileView(ProfileFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
    }
}