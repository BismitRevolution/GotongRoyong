package in.gotongroyong.gotongroyong.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.data.HeroData;

public class PahlawanDataAdapter extends RecyclerView.Adapter<PahlawanDataAdapter.PahlawanViewHolder> {
    private List<HeroData> dataset;

    public static class PahlawanViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public PahlawanViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(final HeroData data) {
            TextView clientName = layout.findViewById(R.id.tv_data_client);
            clientName.setText(data.getName());
            Drawable verified = layout.getResources().getDrawable(R.drawable.ic_verified);
            clientName.setCompoundDrawablesWithIntrinsicBounds(null, null, verified, null);

            String totalDonation = Util.toDecimal(data.getTotalDonation());
            ((TextView) layout.findViewById(R.id.pahlawan_data_donasi)).setText(totalDonation);
            String totalCampaign = Util.toDecimal(data.getTotalCampaign());
            ((TextView) layout.findViewById(R.id.pahlawan_data_campaign)).setText(totalCampaign);

            ImageView pic = layout.findViewById(R.id.img_data_client);
            Picasso.get().load(data.getImgUrl()).into(pic);

            layout.findViewById(R.id.social_facebook).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoLink(data.getFacebookUrl());
                }
            });

            layout.findViewById(R.id.social_twitter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoLink(data.getTwitterUrl());
                }
            });

            layout.findViewById(R.id.social_instagram).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoLink(data.getInstagramUrl());
                }
            });
        }

        private void gotoLink(String url) {
            if (url.equals("")) {
                Toast.makeText(layout.getContext(), "Link is unavailable!", Toast.LENGTH_SHORT).show();
            } else {
                Router.gotoLink(layout.getContext(), url);
            }
        }
    }

    public PahlawanDataAdapter(List<HeroData> dataset) {
        this.dataset = dataset;
    }

    public void update(List<HeroData> data) {
        this.dataset.addAll(data);
    }

    @NonNull
    @Override
    public PahlawanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pahlawan_data, parent, false);
        return new PahlawanViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PahlawanViewHolder holder, int position) {
        holder.setData(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
