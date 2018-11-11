package in.gotongroyong.gotongroyong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.ResponseActivity;
import in.gotongroyong.gotongroyong.adapter.PahlawanDataAdapter;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.HeroListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.HeroResponse;
import in.gotongroyong.gotongroyong.entity.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PahlawanFragment extends Fragment implements BaseFragment, ResponseActivity {
    private GridLayoutManager layoutManager;
    private PahlawanDataAdapter adapter;
    private RecyclerView recyclerView;
    private int currentPage;
    private String nextPageUrl;

    @Override
    public String getTitle() {
        return "Pahlawan";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pahlawan, container, false);

        recyclerView = root.findViewById(R.id.pahlawan_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        currentPage = 1;

        GotongRoyongAPI.listHero(this, currentPage);

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

    private void errorConnection() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    private void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    private void errorNoData() {
        Toast.makeText(getContext(), getResources().getString(R.string.load_nothing), Toast.LENGTH_SHORT).show();
    }


    public void fetchData(HeroListResponse response) {
        currentPage = response.getCurrentPage();
        nextPageUrl = response.getNextPageUrl();
        adapter = new PahlawanDataAdapter(response.getData());
        recyclerView.setAdapter(adapter);
    }

    private void updateData(HeroListResponse response) {
        currentPage = response.getCurrentPage();
        nextPageUrl = response.getNextPageUrl();
        adapter.update(response.getData());
        adapter.notifyDataSetChanged();
    }

    public void update() {
        if (nextPageUrl != null) {
            GotongRoyongAPI.listHero(this, currentPage + 1);
        } else {
            errorNoData();
        }
    }

    @Override
    public void onActivityResponse(int responseCode, int resultCode, Object response) {
        switch (responseCode) {
            case API.HERO_LIST_INIT:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        HeroListResponse listResponse = (HeroListResponse) response;
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
            case API.HERO_LIST_UPDATE:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        HeroListResponse listResponse = (HeroListResponse) response;
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
