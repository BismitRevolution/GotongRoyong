package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.fragment.AboutFragment;
import in.gotongroyong.gotongroyong.fragment.DetailFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmptyActivity extends AppCompatActivity implements ResultActivity {
    public static final String ACTIVITY_TYPE = "activity_type";
    public static final String CAMPAIGN_ID = "campaign_id";

    public static final String ACTIVITY_DETAIL = "activity_detail";
    public static final String ACTIVITY_ABOUT = "activity_about";

    DetailFragment detailFragment;
    AboutFragment aboutFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_empty);

        this.toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        detailFragment = new DetailFragment();
        aboutFragment = new AboutFragment();

        setPage();
    }

    private void setPage() {
        Intent intent = getIntent();
        switch (intent.getStringExtra(ACTIVITY_TYPE)) {
            case ACTIVITY_DETAIL:
                setDetailFragment();
                break;
            case ACTIVITY_ABOUT:
                setAboutFragment();
                break;
        }
    }

    private void setDetailFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, detailFragment).commit();
        Intent intent = getIntent();
        Call<BaseResponse<CampaignDetailResponse>> call = GotongRoyongAPI.getService().getCampaign(new CampaignDetailBody(intent.getIntExtra(CAMPAIGN_ID, -1)));
        call.enqueue(new Callback<BaseResponse<CampaignDetailResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignDetailResponse>> call, Response<BaseResponse<CampaignDetailResponse>> response) {
                if (response.isSuccessful()) {
                    setDetailData(response.body().getPayload());
                } else {
                    errorUnknown();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignDetailResponse>> call, Throwable t) {
                errorConnection();
            }
        });
        getSupportActionBar().setTitle(detailFragment.getTitle());
    }

    private void setDetailData(CampaignDetailResponse data) {
        ImageView bg = findViewById(R.id.data_bg);
        Picasso.get().load(data.getImageList().get(0).getImageUrl()).into(bg);

        ((TextView) findViewById(R.id.data_title)).setText(data.getTitle());
//        ((TextView) findViewById(R.id.tv_data_client)).setText(data.getCampaignUser());

        String dataPahlawan = Util.toDecimal(data.getCountUsers());
        ((TextView) findViewById(R.id.campaign_data_pahlawan)).setText(dataPahlawan);
        String dataDonasi = Util.toDecimal(data.getCountDonations());
        ((TextView) findViewById(R.id.campaign_data_donasi)).setText(dataDonasi);
        String dataTarget = Util.toDecimal(data.getTargetDonation());
        ((TextView) findViewById(R.id.campaign_data_target)).setText(dataTarget);
        String dataCountdown = Util.toDecimal(data.getDayLeft());
        ((TextView) findViewById(R.id.campaign_data_countdown)).setText(dataCountdown);

        TextView dataClient = findViewById(R.id.tv_data_client);
        Drawable verified = getResources().getDrawable(R.drawable.ic_verified);
        dataClient.setCompoundDrawablesWithIntrinsicBounds(null, null, verified, null);

        ProgressBar progressBar = findViewById(R.id.data_progress);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.themeOrange), PorterDuff.Mode.SRC_IN);
        double percentage = ((double) data.getCountDonations() / (double) data.getTargetDonation()) * 100;
        progressBar.setProgress((int) percentage);

        findViewById(R.id.btn_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> resources = new ArrayList<>();
//                    resources.add("http://www.missionmedia.com/uploads/image/blog/1080x1920_blog_post.jpg");
                resources.add("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
                Router.gotoStory(getApplicationContext(), resources, true);
            }
        });

    }

    public void errorConnection() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    public void errorUnknown() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    private void setAboutFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, aboutFragment).commit();
        getSupportActionBar().setTitle(aboutFragment.getTitle());
    }

    @Override
    // ActionBar menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            default:
                getSupportActionBar().setTitle("Specific Campaign");
                break;
        }
    }
}
