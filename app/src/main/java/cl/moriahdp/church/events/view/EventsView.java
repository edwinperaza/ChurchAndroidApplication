package cl.moriahdp.church.events.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.events.adapter.EventsAdapter;
import cl.moriahdp.church.events.fragment.EventsFragment;
import cl.moriahdp.church.events.modelObject.EventModelObject;

public class EventsView extends BaseFragmentView {

    private RecyclerView reEvents;
    private EventsFragment fragment;
    private EventsAdapter adapter = new EventsAdapter();

    public EventsView(EventsFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
        this.fragment = fragment;
        showLoadingOverlay();
        reEvents = rootView.findViewById(R.id.rv_events);
        LinearLayoutManager llm = new LinearLayoutManager(fragment.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reEvents.setLayoutManager(llm);
        reEvents.setAdapter(adapter);
    }

    public void serRecycler(List<EventModelObject> events) {
        if (adapter != null) {
            adapter.setEvents(events);
            hideLoadingOverlay();
        }
    }
}
