package in.gotongroyong.gotongroyong.adapter;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;

public class CampaignDataAdapter extends RecyclerView.Adapter<CampaignDataAdapter.CampaignViewHolder> {
    private List<CampaignData> dataset;

    public static class CampaignViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public CampaignViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(final CampaignData data) {
            ((TextView) layout.findViewById(R.id.data_title)).setText(data.getTitle());
            ((TextView) layout.findViewById(R.id.tv_data_client)).setText(data.getClientName());

            String dataPahlawan = Util.toDecimal(data.getTotalHero());
            ((TextView) layout.findViewById(R.id.campaign_data_pahlawan)).setText(dataPahlawan);
            String dataDonasi = Util.toDecimal(data.getTotalDonation());
            ((TextView) layout.findViewById(R.id.campaign_data_donasi)).setText(dataDonasi);
            String dataTarget = Util.toDecimal(data.getTargetDonation());
            ((TextView) layout.findViewById(R.id.campaign_data_target)).setText(dataTarget);
            String dataCountdown = Util.toDecimal(data.getDayLeft());
            ((TextView) layout.findViewById(R.id.campaign_data_countdown)).setText(dataCountdown);

            TextView dataClient = layout.findViewById(R.id.tv_data_client);
            Drawable verified = layout.getResources().getDrawable(R.drawable.ic_verified);
            dataClient.setCompoundDrawablesWithIntrinsicBounds(null, null, verified, null);

            ProgressBar progressBar = layout.findViewById(R.id.data_progress);
            progressBar.getProgressDrawable().setColorFilter(layout.getResources().getColor(R.color.themeOrange), PorterDuff.Mode.SRC_IN);
            double percentage = ((double) data.getTotalDonation() / (double) data.getTargetDonation()) * 100;
            progressBar.setProgress((int) percentage);

            layout.findViewById(R.id.btn_donate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> resources = new ArrayList<>();
//                    resources.add("http://www.missionmedia.com/uploads/image/blog/1080x1920_blog_post.jpg");
                    resources.add("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
                    Router.gotoStory(layout.getContext(), resources, true);
                }
            });

            layout.findViewById(R.id.campaign).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Router.gotoDetail(layout.getContext(), data.getId());
                }
            });

            ImageView bg = layout.findViewById(R.id.data_bg);
            Picasso.get().load(data.getImgUrl()).into(bg);
        }
    }

    public CampaignDataAdapter(List<CampaignData> dataset) {
        this.dataset = dataset;
    }

    public void update(List<CampaignData> data) {
        dataset.addAll(data);
    }

    @NonNull
    @Override
    public CampaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_campaign_data, parent, false);
        return new CampaignViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CampaignViewHolder holder, int position) {
        holder.setData(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
