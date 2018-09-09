package in.gotongroyong.gotongroyong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.gotongroyong.gotongroyong.R;

public class PahlawanFragment extends Fragment implements BaseFragment {

    @Override
    public String getTitle() {
        return "Pahlawan";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_test, container, false);
        TextView text = root.findViewById(R.id.tv_test_result);
        text.setText(getResources().getString(R.string.test_result, "PAHLAWAN PAGE"));
        return root;
    }
}
