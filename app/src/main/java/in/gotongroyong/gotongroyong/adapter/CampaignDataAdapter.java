package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.R;

public class CampaignDataAdapter extends RecyclerView.Adapter<CampaignDataAdapter.CampaignViewHolder> {
    private ArrayList<String> dataset;

    public static class CampaignViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public CampaignViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(String data) {
            TextView tv = layout.findViewById(R.id.data_title);
            tv.setText(data);
        }
    }

    public CampaignDataAdapter(ArrayList<String> dataset) {
        this.dataset = dataset;
    }

    public void update(String data) {
        this.dataset.add(data);
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
