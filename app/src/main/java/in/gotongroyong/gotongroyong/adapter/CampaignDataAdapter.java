package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.data.CampaignData;

public class CampaignDataAdapter extends RecyclerView.Adapter<CampaignDataAdapter.CampaignViewHolder> {
    private List<CampaignData> dataset;

    public static class CampaignViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public CampaignViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(CampaignData data) {
            ((TextView) layout.findViewById(R.id.data_title)).setText(data.getTitle());
            ((TextView) layout.findViewById(R.id.tv_data_client)).setText(data.getClientName());

            ((TextView) layout.findViewById(R.id.campaign_data_pahlawan)).setText(Integer.toString(data.getTotalHero()));
            ((TextView) layout.findViewById(R.id.campaign_data_donasi)).setText(Integer.toString(data.getTotalDonation()));
            ((TextView) layout.findViewById(R.id.campaign_data_target)).setText(Integer.toString(data.getTargetDonation()));
            ((TextView) layout.findViewById(R.id.campaign_data_countdown)).setText(Integer.toString(data.getDayLeft()));

            layout.findViewById(R.id.btn_donate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> resources = new ArrayList<>();
//                    resources.add("http://www.missionmedia.com/uploads/image/blog/1080x1920_blog_post.jpg");
                    resources.add("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
                    Router.gotoStory(layout.getContext(), resources, true);
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
