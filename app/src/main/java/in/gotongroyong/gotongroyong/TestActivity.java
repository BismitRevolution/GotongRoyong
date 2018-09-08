package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.TestData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        Call<TestData> call = new GotongRoyongAPI().getService().test();
        call.enqueue(new Callback<TestData>() {
            @Override
            public void onResponse(Call<TestData> call, Response<TestData> response) {
                if (response.isSuccessful()) {
                    TestData result = response.body();
                    updateText(result.getData());
                }
            }

            @Override
            public void onFailure(Call<TestData> call, Throwable t) {
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
