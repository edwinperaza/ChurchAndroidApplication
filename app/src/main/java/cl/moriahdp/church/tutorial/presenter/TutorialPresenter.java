package cl.moriahdp.church.tutorial.presenter;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.tutorial.model.TutorialModel;
import cl.moriahdp.church.tutorial.view.TutorialView;

public class TutorialPresenter extends BasePresenter<TutorialView> {

    private TutorialModel model;
    private TutorialView view;

    public TutorialPresenter(TutorialModel model, TutorialView view) {
        super(model, view);
        this.model = model;
        this.view = view;
    }
}
