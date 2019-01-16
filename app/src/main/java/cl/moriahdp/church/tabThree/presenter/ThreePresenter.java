package cl.moriahdp.church.tabThree.presenter;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.tabThree.model.ThreeModel;
import cl.moriahdp.church.tabThree.view.ThreeView;

public class ThreePresenter extends BasePresenter<ThreeView> {

    private ThreeModel threeModel;
    private ThreeView threeView;

    public ThreePresenter(ThreeModel model, ThreeView view) {
        super(model, view);
        threeModel = model;
        threeView = view;
    }
}
