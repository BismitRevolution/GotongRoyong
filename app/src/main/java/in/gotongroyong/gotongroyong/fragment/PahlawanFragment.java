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
import in.gotongroyong.gotongroyong.adapter.PahlawanDataAdapter;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PahlawanFragment extends Fragment implements BaseFragment {
    private GridLayoutManager layoutManager;
    private PahlawanDataAdapter adapter;
    private int currentPage;

    @Override
    public String getTitle() {
        return "Pahlawan";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pahlawan, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.pahlawan_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        currentPage = 1;

        Call<BaseResponse<List<HeroData>>> call = new GotongRoyongAPI().getService().listHero(currentPage);
        call.enqueue(new Callback<BaseResponse<List<HeroData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<HeroData>>> call, Response<BaseResponse<List<HeroData>>> response) {
                List<HeroData> result = response.body().getPayload();
                adapter = new PahlawanDataAdapter(result);
                recyclerView.setAdapter(adapter);
                currentPage++;
            }

            @Override
            public void onFailure(Call<BaseResponse<List<HeroData>>> call, Throwable t) {
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
        Call<BaseResponse<List<HeroData>>> call = new GotongRoyongAPI().getService().listHero(currentPage);
        call.enqueue(new Callback<BaseResponse<List<HeroData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<HeroData>>> call, Response<BaseResponse<List<HeroData>>> response) {
                List<HeroData> result = response.body().getPayload();
                adapter.update(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BaseResponse<List<HeroData>>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to connect. Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
