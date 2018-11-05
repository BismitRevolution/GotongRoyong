package in.gotongroyong.gotongroyong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.gotongroyong.gotongroyong.R;

public class DetailFragment extends Fragment implements BaseFragment {
    private View root;
    private String title;

    public DetailFragment() {
        super();
        this.title = "Specific Campaign";
    }

    @Nullable
@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_campaign_detail, container, false);
        return root;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}
