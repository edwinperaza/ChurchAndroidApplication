package cl.moriahdp.church.sermonDetail.presenter;


import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.sermonDetail.model.SermonDetailModel;
import cl.moriahdp.church.sermonDetail.view.SermonDetailView;

public class SermonDetailPresenter extends BasePresenter<SermonDetailView> {

    private SermonDetailModel model;
    private SermonDetailView view;

    public SermonDetailPresenter(SermonDetailModel model, SermonDetailView view) {
        super(model, view);
        this.model = model;
        this.view = view;
        if (model.isValidSermon()) {
            view.setSermonDetail(model.getSermon());
        } else {
            view.showSermonDetailError();
        }
    }

}
