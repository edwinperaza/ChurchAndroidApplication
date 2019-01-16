package cl.moriahdp.church.sermon.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.utils.recyclerListener.RecyclerOnItemClickListener;
import cl.moriahdp.church.sermon.adapter.SermonsAdapter;
import cl.moriahdp.church.sermon.fragment.SermonFragment;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.sermonDetail.fragment.SermonDetailFragment;


public class SermonView extends BaseFragmentView {

    private RecyclerView rvSermons;
    private SermonFragment fragment;
    private SermonsAdapter adapter = new SermonsAdapter();

    public SermonView(SermonFragment fragment, View rootView, final Bus bus) {
        super(fragment, rootView, bus);
        this.fragment = fragment;
        showLoadingOverlay();
        rvSermons = rootView.findViewById(R.id.rv_sermons);
        LinearLayoutManager llm = new LinearLayoutManager(fragment.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSermons.setLayoutManager(llm);
        rvSermons.setAdapter(adapter);
        adapter.setListener(new RecyclerOnItemClickListener<SermonModelObject>() {
            @Override
            public void onItemClickListener(SermonModelObject item) {
                bus.post(new onItemClickListener(item));
            }
        });

    }

    public void serRecycler(List<SermonModelObject> sermons) {
        if (adapter != null) {
            adapter.setSermons(sermons);
            hideLoadingOverlay();
        }
    }

    public void goToSermonDetailFragment(SermonModelObject sermon) {
        DashboardActivity dashboardActivity = ((DashboardActivity) fragment.getActivity());
        if (dashboardActivity != null) {
            dashboardActivity.goToFragmentWithStack(
                    R.id.tab_container,
                    SermonDetailFragment.newInstance(sermon),
                    SermonDetailFragment.getSermonDetailTag()
            );
        }
    }

    public static class onItemClickListener {
        private SermonModelObject sermon;

        public onItemClickListener(SermonModelObject sermon) {
            this.sermon = sermon;
        }

        public SermonModelObject getSermon() {
            return sermon;
        }
    }
}
