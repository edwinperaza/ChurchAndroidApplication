package cl.moriahdp.church.contact.model;

import com.squareup.otto.Bus;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.utils.data.APIService;

public class ContactModel extends BaseModel {

    public ContactModel(Bus bus, APIService apiService) {
        super(bus);
    }
}
