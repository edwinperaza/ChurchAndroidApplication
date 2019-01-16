package cl.moriahdp.church.connect.model;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.baseclasses.BaseModel;
import cl.moriahdp.church.connect.modelObject.ConnectModelObject;
import cl.moriahdp.church.utils.TinyDB;
import cl.moriahdp.church.utils.data.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectModel extends BaseModel {

    private APIService apiService;
    private TinyDB tinyDB;

    public ConnectModel(Bus bus, APIService apiService) {
        super(bus);
        this.apiService = apiService;
    }

    public void requestConnect() {
        apiService.getConnects().enqueue(new Callback<List<ConnectModelObject>>() {
            @Override
            public void onResponse(Call<List<ConnectModelObject>> call, Response<List<ConnectModelObject>> response) {
                if (response.isSuccessful()) {
                    List<ConnectModelObject> connects = response.body();
                    mBus.post(new RequestConnectsSuccess(connects));
                }
            }

            @Override
            public void onFailure(Call<List<ConnectModelObject>> call, Throwable t) {
                mBus.post(new RequestConnectsFailure());
            }
        });
    }

    public class RequestConnectsSuccess {
        private List<ConnectModelObject> connects;

        RequestConnectsSuccess(List<ConnectModelObject> connects) {
            this.connects = connects;
        }

        public List<ConnectModelObject> getConnects() {
            return connects;
        }
    }

    public class RequestConnectsFailure {
    }

}