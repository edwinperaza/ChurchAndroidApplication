package cl.moriahdp.church.connect.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.church.R;
import cl.moriahdp.church.connect.modelObject.ConnectModelObject;

public class ConnectAdapter extends RecyclerView.Adapter<ConnectAdapter.ViewHolder>{

    private List<ConnectModelObject> connects = new ArrayList<>();
    private Bus bus;

    public ConnectAdapter(Bus bus) {
        this.bus = bus;
    }

    public void setConnects(List<ConnectModelObject> connects) {
        this.connects = connects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewConnect = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_connects, parent, false);
        return new ViewHolder(viewConnect);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ConnectModelObject connect = connects.get(position);
        holder.title.setText(connect.getTitle());
        Picasso.get().load(connect.getImageUrl()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new OnConnectItemPressed(connect.getUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return connects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_connect_image);
            title = itemView.findViewById(R.id.tv_connect_title);
        }
    }

    public class OnConnectItemPressed {
        String url;

        public OnConnectItemPressed(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }
}
