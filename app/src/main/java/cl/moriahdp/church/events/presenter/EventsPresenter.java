package cl.moriahdp.church.events.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.events.model.EventsModel;
import cl.moriahdp.church.events.view.EventsView;

public class EventsPresenter extends BasePresenter<EventsView> {

    private EventsModel model;
    private EventsView view;

    public EventsPresenter(EventsModel model, EventsView view) {
        super(model, view);
        this.model = model;
        this.view = view;
        model.requestEvents();
    }

    @Subscribe
    public void onRequestEventsSuccess(EventsModel.RequestEventsSuccess event) {
        view.serRecycler(event.getEvents());
    }

}
