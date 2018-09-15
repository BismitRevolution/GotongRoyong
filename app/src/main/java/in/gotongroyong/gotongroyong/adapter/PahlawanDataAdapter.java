package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.data.HeroData;

public class PahlawanDataAdapter extends RecyclerView.Adapter<PahlawanDataAdapter.PahlawanViewHolder> {
    private List<HeroData> dataset;

    public static class PahlawanViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public PahlawanViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(HeroData data) {
            ((TextView) layout.findViewById(R.id.tv_data_client)).setText(data.getName());

            ((TextView) layout.findViewById(R.id.pahlawan_data_donasi)).setText(Integer.toString(data.getTotalDonation()));
            ((TextView) layout.findViewById(R.id.pahlawan_data_campaign)).setText(Integer.toString(data.getTotalCampaign()));

            ImageView pic = layout.findViewById(R.id.img_data_client);
            Picasso.get().load(data.getImgUrl()).into(pic);
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
