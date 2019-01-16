package cl.moriahdp.church.registry.presenter;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.registry.model.RegistryModel;
import cl.moriahdp.church.registry.view.RegistryView;

public class RegistryPresenter extends BasePresenter<RegistryView> {

    private RegistryModel mModel;
    private RegistryView mView;

    public RegistryPresenter(RegistryModel model, RegistryView view) {
        super(model, view);
        this.mModel = model;
        this.mView = view;
    }

}
