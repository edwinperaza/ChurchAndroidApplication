package cl.moriahdp.church.events.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.events.modelObject.EventModelObject;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<EventModelObject> events = new ArrayList<>();

    public EventsAdapter() {
    }

    public void setEvents(List<EventModelObject> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        return new ViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventModelObject event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.dateMonth.setText(event.getDateInit());
        holder.dateDay.setText(event.getDateEnd());
        holder.address.setText(event.getAddress());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView dateMonth;
        TextView dateDay;
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_event_title);
            dateMonth = itemView.findViewById(R.id.tv_event_month);
            dateDay = itemView.findViewById(R.id.tv_event_day);
            address = itemView.findViewById(R.id.tv_event_address);
        }
    }
}
