package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.entity.API;

public class ConfirmationActivity extends AppCompatActivity implements ResponseActivity {
    public static final String CONFIRMATION_CAMPAIGN_ID = "confirmation_campaign_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        int campaign_id = intent.getIntExtra(CONFIRMATION_CAMPAIGN_ID, -1);
        GotongRoyongAPI.getCampaign(this, new CampaignDetailBody(campaign_id));
    }

    private void fetchData(final CampaignDetailResponse response) {
        ImageView bg = findViewById(R.id.campaign_bg);
        Picasso.get().load(response.getImageList().get(0).getImageUrl()).into(bg);

        ((TextView) findViewById(R.id.tv_campaign_data)).setText(getResources().getString(R.string.confirmation_description_value, response.getTitle(), response.getCampaignerFullname()));

        findViewById(R.id.btn_next_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.gotoStory(getApplicationContext(), response.getCampaignId());
                finish();
            }
        });

        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void errorConnection() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    private void errorUnknown() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResponse(int responseCode, int resultCode, Object response) {
        switch (responseCode) {
            case API.CAMPAIGN_DETAIL:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        CampaignDetailResponse detailResponse = (CampaignDetailResponse) response;
                        fetchData(detailResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
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
