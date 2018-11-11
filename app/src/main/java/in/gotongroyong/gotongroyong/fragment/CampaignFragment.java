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
import in.gotongroyong.gotongroyong.ResponseActivity;
import in.gotongroyong.gotongroyong.adapter.CampaignDataAdapter;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import in.gotongroyong.gotongroyong.entity.API;

public class CampaignFragment extends Fragment implements BaseFragment, ResponseActivity {
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

        GotongRoyongAPI.listCampaign(this, currentPage);

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

    private void fetchData(CampaignListResponse response) {
        currentPage = response.getCurrentPage();
        nextPageUrl = response.getNextPageUrl();
        adapter = new CampaignDataAdapter(response.getData());
    }

    private void updateData(CampaignListResponse response) {
        currentPage = response.getCurrentPage();
        nextPageUrl = response.getNextPageUrl();
        adapter.update(response.getData());
        adapter.notifyDataSetChanged();
    }

    public void errorConnection() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    public void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    public void update() {
        if (nextPageUrl != null) {
            GotongRoyongAPI.listCampaign(this, currentPage + 1);
        }
//        adapter.update("New");
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResponse(int responseCode, int resultCode, Object response) {
        switch (responseCode) {
            case API.CAMPAIGN_LIST_INIT:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        CampaignListResponse listResponse = (CampaignListResponse) response;
                        fetchData(listResponse);
                    } catch (Exception e) {
                        errorUnknown();
                    }
                } else if (resultCode == API.ERROR_NO_CONNECTION) {
                    errorConnection();
                } else {
                    errorUnknown();
                }
                break;
            case API.CAMPAING_LIST_UPDATE:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        CampaignListResponse listResponse = (CampaignListResponse) response;
                        updateData(listResponse);
                    } catch (Exception e) {
                        errorUnknown();
                    }
                } else if (resultCode == API.ERROR_NO_CONNECTION) {
                    errorConnection();
                } else {
                    errorUnknown();
                }
                break;
        }
    }
}
