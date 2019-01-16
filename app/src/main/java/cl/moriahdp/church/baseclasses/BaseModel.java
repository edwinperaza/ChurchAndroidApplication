package cl.moriahdp.church.baseclasses;

import com.squareup.otto.Bus;

public class BaseModel {
    protected Bus mBus;

    public BaseModel(Bus bus) {
        mBus = bus;
    }

}
