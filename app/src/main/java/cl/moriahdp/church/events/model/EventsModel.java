package cl.moriahdp.church.events.model;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.events.modelObject.EventModelObject;
import cl.moriahdp.church.utils.TinyDB;
import cl.moriahdp.church.utils.data.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsModel extends BaseModel {

    private APIService aPIService;
    private TinyDB tinyDB;

    public EventsModel(Bus bus, APIService apiService, TinyDB tinyDB) {
        super(bus);
        aPIService = apiService;
        this.tinyDB = tinyDB;
    }

    public void requestEvents() {
        aPIService.getEvents().enqueue(new Callback<List<EventModelObject>>() {
            @Override
            public void onResponse(Call<List<EventModelObject>> call, Response<List<EventModelObject>> response) {
                if (response.isSuccessful()) {
                    List<EventModelObject> events = response.body();
                    mBus.post(new RequestEventsSuccess(events));
                }
            }

            @Override
            public void onFailure(Call<List<EventModelObject>> call, Throwable t) {
                mBus.post(new RequestEventsFailure());
            }
        });
    }

    public class RequestEventsSuccess {
        private List<EventModelObject> events;

        RequestEventsSuccess(List<EventModelObject> events) {
            this.events = events;
        }

        public List<EventModelObject> getEvents() {
            return events;
        }
    }

    public class RequestEventsFailure {
    }
}
