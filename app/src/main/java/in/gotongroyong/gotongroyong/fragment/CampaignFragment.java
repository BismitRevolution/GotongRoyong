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

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.adapter.CampaignDataAdapter;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignFragment extends Fragment implements BaseFragment {
    private LinearLayoutManager layoutManager;
    private CampaignDataAdapter adapter;
    private int currentPage;
    private String nextPageUrl;

    @Override
    public String getTitle() {
        return "Campaigns";
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

        Call<BaseResponse<CampaignListResponse>> call = GotongRoyongAPI.getService().listCampaign(currentPage);
        call.enqueue(new Callback<BaseResponse<CampaignListResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignListResponse>> call, Response<BaseResponse<CampaignListResponse>> response) {
                if (response.isSuccessful()) {
                    CampaignListResponse listResponse = response.body().getPayload();
                    currentPage = listResponse.getCurrentPage();
                    nextPageUrl = listResponse.getNextPageUrl();
                    adapter = new CampaignDataAdapter(listResponse.getData());
                } else {
                    errorUnknown();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignListResponse>> call, Throwable t) {
                errorConnection();
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

    public void errorConnection() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    public void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    public void update() {
        if (nextPageUrl != null) {
            Call<BaseResponse<CampaignListResponse>> call = GotongRoyongAPI.getService().listCampaign(currentPage + 1);
            call.enqueue(new Callback<BaseResponse<CampaignListResponse>>() {
                @Override
                public void onResponse(Call<BaseResponse<CampaignListResponse>> call, Response<BaseResponse<CampaignListResponse>> response) {
                    if (response.isSuccessful()) {
                        CampaignListResponse listResponse = response.body().getPayload();
                        currentPage = listResponse.getCurrentPage();
                        nextPageUrl = listResponse.getNextPageUrl();
                        adapter.update(listResponse.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        errorUnknown();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<CampaignListResponse>> call, Throwable t) {
                    errorConnection();
                }
            });
        }

//        adapter.update("New");
//        adapter.notifyDataSetChanged();
    }
}
