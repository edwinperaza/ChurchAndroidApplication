package cl.moriahdp.church.profile.model;

import com.squareup.otto.Bus;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.utils.data.APIService;

public class ProfileModel extends BaseModel {

    public ProfileModel(Bus bus, APIService apiService) {
        super(bus);
    }

}
