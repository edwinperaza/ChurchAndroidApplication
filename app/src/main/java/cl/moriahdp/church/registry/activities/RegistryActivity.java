package cl.moriahdp.church.registry.activities;

import android.os.Bundle;

import butterknife.ButterKnife;
import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.registry.model.RegistryModel;
import cl.moriahdp.church.registry.presenter.RegistryPresenter;
import cl.moriahdp.church.registry.view.RegistryView;
import cl.moriahdp.church.utils.bus.BusProvider;

public class RegistryActivity extends BaseActivity {

    private RegistryPresenter mRegistryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        RegistryView registryView = new RegistryView(this, BusProvider.getInstance());
        mRegistryPresenter = new RegistryPresenter(new RegistryModel(BusProvider.getInstance()), registryView);
        ButterKnife.bind(registryView, this);
        hideToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(mRegistryPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(mRegistryPresenter);
    }
}
