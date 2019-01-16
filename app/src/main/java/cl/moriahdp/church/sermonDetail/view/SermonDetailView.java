package cl.moriahdp.church.sermonDetail.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import cl.moriahdp.church.R;
import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;
import cl.moriahdp.church.sermonDetail.fragment.SermonDetailFragment;


public class SermonDetailView extends BaseFragmentView {

    private Context context;
    private Button btnSermonDetailWatch;
    private ImageView ivSermonDetailImage;
    private ImageView ivSermonDetailShare;
    private TextView tvSermonDetailTitle;
    private TextView tvSermonDetailDescription;
    private TextView tvSermonDetailPastorName;

    public SermonDetailView(SermonDetailFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
        this.context = fragment.getContext();
        setUpView(rootView);
    }

    private void setUpView(View rootView) {
        btnSermonDetailWatch = rootView.findViewById(R.id.btn_sermon_detail_watch);
        ivSermonDetailImage = rootView.findViewById(R.id.iv_sermon_detail_image);
        ivSermonDetailShare = rootView.findViewById(R.id.iv_sermon_detail_share);
        tvSermonDetailTitle = rootView.findViewById(R.id.tv_sermon_detail_title);
        tvSermonDetailDescription = rootView.findViewById(R.id.tv_sermon_detial_description);
        tvSermonDetailPastorName = rootView.findViewById(R.id.tv_sermon_detail_pastor_name);
    }

    public void setSermonDetail(final SermonModelObject sermon) {

        Picasso.get().load(sermon.getImageUrl()).into(ivSermonDetailImage);
        tvSermonDetailTitle.setText(sermon.getTitle());
        tvSermonDetailDescription.setText(sermon.getDescription());
        tvSermonDetailPastorName.setText(sermon.getPastorName());
        btnSermonDetailWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl(sermon.getVideoUrl());
            }
        });

        ivSermonDetailShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTo(sermon.getTitle(), sermon.getVideoUrl());
            }
        });
    }

    public void showSermonDetailError() {
        Toast.makeText(context, R.string.sermon_detail_error, Toast.LENGTH_LONG).show();
    }

    private void goToUrl(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

    private void shareTo(String title, String url){

        String message = context.getResources().getText(R.string.sermon_detail_share_message_title)  + title +
                context.getResources().getText(R.string.sermon_detail_share_message_url) + url;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.sermon_detail_share)));
    }

}
