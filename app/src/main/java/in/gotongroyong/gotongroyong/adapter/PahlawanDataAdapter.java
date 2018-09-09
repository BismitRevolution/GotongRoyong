package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.R;

public class PahlawanDataAdapter extends RecyclerView.Adapter<PahlawanDataAdapter.PahlawanViewHolder> {
    private ArrayList<String> dataset;

    public static class PahlawanViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;

        public PahlawanViewHolder(RelativeLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public void setData(String data) {
            TextView tv = layout.findViewById(R.id.tv_data_client);
            tv.setText(data);
        }
    }

    public PahlawanDataAdapter(ArrayList<String> dataset) {
        this.dataset = dataset;
    }

    public void update(String data) {
        this.dataset.add(data);
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
