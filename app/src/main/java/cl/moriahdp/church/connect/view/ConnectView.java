package cl.moriahdp.church.connect.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.connect.adapter.ConnectAdapter;
import cl.moriahdp.church.connect.fragment.ConnectFragment;
import cl.moriahdp.church.connect.modelObject.ConnectModelObject;

public class ConnectView extends BaseFragmentView {

    private RecyclerView rvConnects;
    private Context context;
    private ConnectAdapter adapter;

    public ConnectView(ConnectFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
        context = fragment.getContext();
        showLoadingOverlay();
        rvConnects = rootView.findViewById(R.id.rv_connects);

        adapter = new ConnectAdapter(bus);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(context, 2);
        rvConnects.setLayoutManager(gridLayoutManager);
        rvConnects.setAdapter(adapter);
    }

    public void setRecycler(List<ConnectModelObject> connects) {
        if (adapter != null) {
            adapter.setConnects(connects);
            hideLoadingOverlay();
        }
    }

    public void goToUrl(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

}
