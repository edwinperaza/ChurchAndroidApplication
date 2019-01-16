package cl.moriahdp.church.sermonDetail.model;

import com.squareup.otto.Bus;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;

public class SermonDetailModel extends BaseModel {

    private  SermonModelObject sermon;

    public SermonDetailModel(Bus bus, SermonModelObject sermon) {
        super(bus);
        this.sermon = sermon;
    }

    public SermonModelObject getSermon() {
        return sermon;
    }

    public boolean isValidSermon() {
        return sermon != null && sermon.getTitle() != null && sermon.getTitle().length() > 0;
    }
}