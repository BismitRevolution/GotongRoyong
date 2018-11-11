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
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.fragment.AboutFragment;
import in.gotongroyong.gotongroyong.fragment.DetailFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmptyActivity extends AppCompatActivity implements ResponseActivity {
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

    private void fetchDetailData(CampaignDetailResponse response) {
        detailFragment.setData(response);
        getSupportActionBar().setTitle(detailFragment.getTitle());
    }

    private void setDetailFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, detailFragment).commit();
        Intent intent = getIntent();
        GotongRoyongAPI.getCampaign(this, new CampaignDetailBody(intent.getIntExtra(CAMPAIGN_ID, -1)));
        getSupportActionBar().setTitle(detailFragment.getTitle());
    }

    private void errorConnection() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    private void errorUnknown() {
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
    public void onActivityResponse(int responseCode, int resultCode, Object response) {
        switch (responseCode) {
            case API.CAMPAIGN_DETAIL:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        CampaignDetailResponse detailResponse = (CampaignDetailResponse) response;
                        fetchDetailData(detailResponse);
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
