package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import in.gotongroyong.gotongroyong.data.TestData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

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
