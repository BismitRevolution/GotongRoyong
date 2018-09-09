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

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.adapter.PahlawanDataAdapter;

public class PahlawanFragment extends Fragment implements BaseFragment {
    private GridLayoutManager layoutManager;
    private PahlawanDataAdapter adapter;

    @Override
    public String getTitle() {
        return "Pahlawan";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pahlawan, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.pahlawan_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> dataset = new ArrayList<>();
        dataset.add("First");
        dataset.add("Second");
        dataset.add("Third");
        dataset.add("Four");
        adapter = new PahlawanDataAdapter(dataset);
        recyclerView.setAdapter(adapter);

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
        adapter.update("New");
        adapter.notifyDataSetChanged();
    }
}
