package in.gotongroyong.gotongroyong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.data.body.ShareBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.entity.API;

public class DetailFragment extends Fragment implements BaseFragment, ResultActivity {
    private View root;
    private String title;

    public DetailFragment() {
        super();
        this.title = "Retrieving Data...";
    }

    @Nullable
@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_campaign_detail, container, false);
        return root;
    }

    public void setData(final CampaignDetailResponse data) {
        this.title = data.getTitle();

        ImageView bg = root.findViewById(R.id.data_bg);
        Picasso.get().load(data.getImageList().get(0).getImageUrl()).into(bg);

        ((TextView) root.findViewById(R.id.data_title)).setText(data.getTitle());
        ((TextView) root.findViewById(R.id.tv_data_client)).setText(data.getCampaignerFullname());
        ImageView clientImg = root.findViewById(R.id.img_data_client);
        Picasso.get().load(data.getCampaignerImgUrl()).into(clientImg);

        String dataPahlawan = Util.toDecimal(data.getCountUsers());
        ((TextView) root.findViewById(R.id.campaign_data_pahlawan)).setText(dataPahlawan);
        String dataDonasi = Util.toDecimal(data.getCountDonations());
        ((TextView) root.findViewById(R.id.campaign_data_donasi)).setText(dataDonasi);
        String dataTarget = Util.toDecimal(data.getTargetDonation());
        ((TextView) root.findViewById(R.id.campaign_data_target)).setText(dataTarget);
        String dataCountdown = Util.toDecimal(data.getDayLeft());
        ((TextView) root.findViewById(R.id.campaign_data_countdown)).setText(dataCountdown);

        TextView dataClient = root.findViewById(R.id.tv_data_client);
        Drawable verified = getResources().getDrawable(R.drawable.ic_verified);
        dataClient.setCompoundDrawablesWithIntrinsicBounds(null, null, verified, null);

        ProgressBar progressBar = root.findViewById(R.id.data_progress);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.themeOrange), PorterDuff.Mode.SRC_IN);
        double percentage = ((double) data.getCountDonations() / (double) data.getTargetDonation()) * 100;
        progressBar.setProgress((int) percentage);

        ((WebView) root.findViewById(R.id.detail_webview)).loadData(data.getDescription(), "text/html; charset=UTF-8", null);

        root.findViewById(R.id.btn_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<String> resources = new ArrayList<>();
//                    resources.add("http://www.missionmedia.com/uploads/image/blog/1080x1920_blog_post.jpg");
//                resources.add("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
                Router.gotoStory(getContext(), data.getCampaignId());
            }
        });

        root.findViewById(R.id.btn_data_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(data.getCampaignId(), data.getTitle());
            }
        });
    }

    private void share(int id, String title) {
        Router.share(getContext(), id, title);
        GotongRoyongAPI.share(this, new ShareBody(id));
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            case API.CAMPAIGN_SHARE:
                if (resultCode == API.IS_SUCCESS) {
                    // Do something?
                }
                break;
        }
    }
}
