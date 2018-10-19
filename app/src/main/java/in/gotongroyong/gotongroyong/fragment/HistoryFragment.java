package in.gotongroyong.gotongroyong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.adapter.CampaignDataAdapter;
import in.gotongroyong.gotongroyong.adapter.HistoryAdapter;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment implements BaseFragment {
    private LinearLayoutManager layoutManager;
    private HistoryAdapter adapter;
    private int currentPage;

    @Override
    public String getTitle() {
        return "History";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_campaign, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.campaign_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        currentPage = 1;

        Call<BaseResponse<List<CampaignData>>> call = new GotongRoyongAPI().getService().listCampaign(currentPage);
        call.enqueue(new Callback<BaseResponse<List<CampaignData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CampaignData>>> call, Response<BaseResponse<List<CampaignData>>> response) {
                List<CampaignData> result = response.body().getPayload();
                adapter = new HistoryAdapter(result);
                recyclerView.setAdapter(adapter);
                currentPage++;
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CampaignData>>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to connect. Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(1)) {
                    update();
                }
            }
        });

        return root;
    }

    public void update() {
        Call<BaseResponse<List<CampaignData>>> call = new GotongRoyongAPI().getService().listCampaign(currentPage);
        call.enqueue(new Callback<BaseResponse<List<CampaignData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CampaignData>>> call, Response<BaseResponse<List<CampaignData>>> response) {
                adapter.update(response.body().getPayload());
                adapter.notifyDataSetChanged();
                currentPage++;
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CampaignData>>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to connect. Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
//        adapter.update("New");
//        adapter.notifyDataSetChanged();
    }
}
