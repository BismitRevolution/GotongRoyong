package in.gotongroyong.gotongroyong.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.common.Router;

public class AboutFragment extends Fragment implements BaseFragment {
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_about, container, false);

        SpannableStringBuilder str = new SpannableStringBuilder(root.getResources().getString(R.string.about_first_paragraph));
        str.setSpan(new StyleSpan(Typeface.BOLD), 0, "GotongRoyong".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) root.findViewById(R.id.tv_first_paragraph)).setText(str);

        root.findViewById(R.id.btn_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.sentEmail(root.getContext());
            }
        });

        root.findViewById(R.id.btn_whatsapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.sentWhatsapp(root.getContext());
            }
        });
        return root;
    }

    @Override
    public String getTitle() {
        return "Tentang GotongRoyong";
    }
}
