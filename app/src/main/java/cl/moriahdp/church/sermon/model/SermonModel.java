package cl.moriahdp.church.sermon.model;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;
import cl.moriahdp.church.utils.TinyDB;
import cl.moriahdp.church.utils.data.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SermonModel extends BaseModel {

    private APIService apiService;

    public SermonModel(Bus bus, APIService apiService, TinyDB tinyDB) {
        super(bus);
        this.apiService = apiService;
    }

    public void requestSermons() {
        apiService.getSermons().enqueue(new Callback<List<SermonModelObject>>() {
            @Override
            public void onResponse(Call<List<SermonModelObject>> call, Response<List<SermonModelObject>> response) {
                if (response.isSuccessful()) {
                    List<SermonModelObject> sermons = response.body();
                    mBus.post(new RequestSermonsSuccess(sermons));
                }
            }

            @Override
            public void onFailure(Call<List<SermonModelObject>> call, Throwable t) {
                mBus.post(new RequestSermonsFailure());
            }
        });
    }

    public class RequestSermonsSuccess {
        private List<SermonModelObject> sermons;

        RequestSermonsSuccess(List<SermonModelObject> sermons) {
            this.sermons = sermons;
        }

        public List<SermonModelObject> getSermons() {
            return sermons;
        }
    }

    public class RequestSermonsFailure {
    }
}