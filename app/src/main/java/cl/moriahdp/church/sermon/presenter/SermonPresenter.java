package cl.moriahdp.church.sermon.presenter;


import com.squareup.otto.Subscribe;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.sermon.model.SermonModel;
import cl.moriahdp.church.sermon.view.SermonView;

public class SermonPresenter extends BasePresenter<SermonView> {

    private SermonModel model;
    private SermonView view;

    public SermonPresenter(SermonModel model, SermonView view) {
        super(model, view);
        this.model = model;
        this.view = view;
        model.requestSermons();
    }

    @Subscribe
    public void onSermonsResponseSuccessful(SermonModel.RequestSermonsSuccess event) {
        view.serRecycler(event.getSermons());
    }

    @Subscribe
    public void onItemClickListenerEvent(SermonView.onItemClickListener event) {
        view.goToSermonDetailFragment(event.getSermon());
    }

}
