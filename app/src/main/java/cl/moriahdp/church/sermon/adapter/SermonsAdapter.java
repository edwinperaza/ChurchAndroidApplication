package cl.moriahdp.church.sermon.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.utils.recyclerListener.RecyclerOnItemClickListener;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;

public class SermonsAdapter extends RecyclerView.Adapter<SermonsAdapter.ViewHolder> {

    private List<SermonModelObject> sermons = new ArrayList<>();
    private RecyclerOnItemClickListener<SermonModelObject> listener;

    public SermonsAdapter() {

    }

    public void setSermons(List<SermonModelObject> sermons) {
        this.sermons = sermons;
        notifyDataSetChanged();
    }

    public void setListener(RecyclerOnItemClickListener<SermonModelObject> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sermonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sermons, parent, false);
        return new ViewHolder(sermonView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SermonModelObject sermon = sermons.get(position);
        holder.title.setText(sermon.getTitle());
        holder.date.setText(sermon.getCreatedAt());
        Picasso.get().load(sermon.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return sermons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        ImageView image;

        public ViewHolder(View itemView, final RecyclerOnItemClickListener<SermonModelObject> listener) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_sermon);
            title = itemView.findViewById(R.id.tv_sermon_title);
            date = itemView.findViewById(R.id.tv_sermon_date);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(sermons.get(getLayoutPosition()));
                }
            });
        }
    }
}
