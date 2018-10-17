package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private final String FIREBASE_GOOGLE_KEY = "470611138429-sk40l6hf8b5qq76m34fsp70u7932hcp2.apps.googleusercontent.com";
    private final String FACEBOOK_APP_ID = "502859973522287";
    private final String FACEBOOK_APP_SECRET = "bc6e906b7ea40c514154b64d8a2a3961";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void testAPI() {
        Call<BaseResponse<List<CampaignData>>> call = new GotongRoyongAPI().getService().listCampaign(1);
        call.enqueue(new Callback<BaseResponse<List<CampaignData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CampaignData>>> call, Response<BaseResponse<List<CampaignData>>> response) {
                if (response.isSuccessful()) {
                    List<CampaignData> result = response.body().getPayload();
                    updateText(result.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CampaignData>>> call, Throwable t) {
                t.printStackTrace();
                updateFail();
            }
        });
    }

    private void updateText(String data) {
        TextView tvResult = findViewById(R.id.tv_test_result);
        tvResult.setText(getResources().getString(R.string.test_result, data));
    }

    private void updateFail() {
        TextView tvResult = findViewById(R.id.tv_test_result);
        tvResult.setText(getResources().getString(R.string.test_result, "FAILED"));
    }
}
