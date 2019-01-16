package cl.moriahdp.church.profile.presenter;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.profile.model.ProfileModel;
import cl.moriahdp.church.profile.view.ProfileView;

public class ProfilePresenter extends BasePresenter<ProfileView> {

    private ProfileModel model;
    private ProfileView view;

    public ProfilePresenter(ProfileModel model, ProfileView view) {
        super(model, view);
        this.model = model;
        this.view = view;
    }

}
