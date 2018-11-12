package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.body.AdsClickBody;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.FacebookRegisterBody;
import in.gotongroyong.gotongroyong.data.body.GenerateAdsBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleRegisterBody;
import in.gotongroyong.gotongroyong.data.body.ShareBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.GenerateAdsResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.RegisterResponse;
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
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAPI();
            }
        });

        String desc = "<figure class='image'><img src='https://gotongroyong.online/Uploads/baznas-bg.jpg' alt='Baznas Untuk Donggala'></figure><p>Bencana yang menghantam Palu, Sigi dan Donggala tidak menghentikan semangat bangsa Indonesia untuk kerjasama dan bangkit membangun kembali kehidupan-kehidupan disana. GotongRoyong bekerjasama dengan BAZNAS membuat kampanye sosial 1001 Sabun Gratis untuk warga yang terpengaruh bencana di Palu-Sigi-Donggala. &nbsp;&nbsp;</p><p><strong>Ini akan menjadi kampanye donasi uang tanpa uang pertama dalam sejarah Indonesia. 100% uang donasi akan dialirkan ke BAZNAS</strong></p><p>Kami juga bekerjasama dengan partner-partner kami yang memasang iklan, termasuk UMKM yang siap membantu menyalurkan donasi. Setiap partner pengiklan kami berkomitmen akan mendonasikan uang untuk membelikan 1001 sabun gratis bagi warga Palu-Sigi-Donggala. &nbsp; &nbsp;&nbsp;</p><p>Yang perlu kamu lakukan adalah menonton iklan mereka. Uang yang didapatkan dari iklan tersebut akan didonasikan untuk membeli 1001 sabun di Palu-Sigi-Donggala. Kamu tidak perlu mengeluarkan uang atau pergi ke ATM untuk transfer, cukup luangkan waktu 10 detik untuk menonton iklan dan membuat donasi gratis.</p><p>Setiap 10 kali menonton iklan, Anda mendonasikan satu batang sabun bagi warga Palu-Sigi-Donggala. Waktu yang dibutuhkan hanya 2 menit saja. &nbsp; &nbsp;&nbsp;</p><p>Mudah kan? Ayo ajak teman-teman, saudara-saudara dan keluargamu untuk berpartisipasi dalam kampanye donasi uang tanpa uang pertama dalam sejarah Indonesia!</p><p>Yuk Tekan <i><strong>Donasi Gratis </strong></i>sekarang!</p>";
        ((WebView) findViewById(R.id.wv_test)).loadData(desc, "text/html; charset=UTF-8", null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void testAPI() {
//        String id_fb = "test7";
//        String email = "test7@mail.com";
//        String pass = "test2";
//        String name = "Test 2 Google";
//        testLoginFb(id_fb);
//        testRegisterFb(email, id_fb, name, "", "", "", "");
//        testLoginEmail(email, pass);
//        testRegisterEmail(email, pass, name);
//        testListCampaign(1);
//        testGetCampaign(86);
//        testGenerateAds("Bearer j1VoEksM25dvBLvs34U4SOzSKIkabH8hmCrPdseIKotgytZBcoNNFiFhg2Kk", 86);
//        testShare(25);
//        testAdsClick(25);
    }

    private void testAdsClick(int id_donation) {
        AdsClickBody body = new AdsClickBody(id_donation);
        Call<BaseResponse<String>> call = new GotongRoyongAPI().getService().adsClick(body);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                updateFail();
            }
        });
    }

    private void testShare(int id_campaign) {
        ShareBody body = new ShareBody(id_campaign);
        Call<BaseResponse<String>> call = new GotongRoyongAPI().getService().share(body);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                updateFail();
            }
        });
    }

    private void testGenerateAds(String api_token, int id_campaign) {
        GenerateAdsBody body = new GenerateAdsBody(id_campaign);
        Call<BaseResponse<GenerateAdsResponse>> call = new GotongRoyongAPI().getService().generateAds(api_token, body);
        call.enqueue(new Callback<BaseResponse<GenerateAdsResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<GenerateAdsResponse>> call, Response<BaseResponse<GenerateAdsResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getAdsData().getAdvName());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<GenerateAdsResponse>> call, Throwable t) {
                updateFail();
            }
        });
    }

    private void testGetCampaign(int id) {
        CampaignDetailBody body = new CampaignDetailBody(id);
        Call<BaseResponse<CampaignDetailResponse>> call = new GotongRoyongAPI().getService().getCampaign(body);
        call.enqueue(new Callback<BaseResponse<CampaignDetailResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignDetailResponse>> call, Response<BaseResponse<CampaignDetailResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getTitle() + " IMG : " + response.body().getPayload().getImageList().get(0));
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignDetailResponse>> call, Throwable t) {
                updateFail();
            }
        });
    }

    private void testListCampaign(int page) {
        Call<BaseResponse<CampaignListResponse>> call = new GotongRoyongAPI().getService().listCampaign(page);
        call.enqueue(new Callback<BaseResponse<CampaignListResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignListResponse>> call, Response<BaseResponse<CampaignListResponse>> response) {
                if (response.isSuccessful()) {
                    updateText("PAGE : " + response.body().getPayload().getCurrentPage() + " " + response.body().getPayload().getData().get(0).getTitle());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignListResponse>> call, Throwable t) {
                updateFail();
            }
        });
    }

    private void testLoginFb(String id_fb) {
        GoogleLoginBody body = new GoogleLoginBody(id_fb);
        Call<BaseResponse<LoginResponse>> call = new GotongRoyongAPI().getService().googleLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getFullname());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                updateFail();
                t.printStackTrace();
            }
        });
    }

    private void testRegisterFb(String email, String id_fb, String fullname, String birthdate, String birthplace, String gender, String image_profile) {
        GoogleRegisterBody body = new GoogleRegisterBody(email, id_fb, fullname, birthdate, birthplace, gender, image_profile);
        Call<BaseResponse<RegisterResponse>> call = new GotongRoyongAPI().getService().googleRegister(body);
        call.enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getFullname());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                updateFail();
                t.printStackTrace();
            }
        });
    }

    private void testLoginEmail(String email, String password) {
        EmailLoginBody body = new EmailLoginBody(email, password);
        Call<BaseResponse<LoginResponse>> call = new GotongRoyongAPI().getService().emailLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getFullname());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                updateFail();
                t.printStackTrace();
            }
        });
    }

    private void testRegisterEmail(String email, String password, String fullname) {
        EmailRegisterBody body = new EmailRegisterBody(email, password, password, fullname);
        Call<BaseResponse<RegisterResponse>> call = new GotongRoyongAPI().getService().emailRegister(body);
        call.enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    updateText(response.body().getPayload().getEmail());
                } else {
                    updateError(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                updateFail();
                t.printStackTrace();
            }
        });
    }

    private void updateText(String data) {
        TextView tvResult = findViewById(R.id.tv_test_result);
        tvResult.setText(getResources().getString(R.string.test_result, "\nSUCCESS: " + data));
    }

    private void updateError(String message) {
        TextView tvResult = findViewById(R.id.tv_test_result);
        tvResult.setText(getResources().getString(R.string.test_result, "\nERROR: " + message));
    }

    private void updateFail() {
        TextView tvResult = findViewById(R.id.tv_test_result);
        tvResult.setText(getResources().getString(R.string.test_result, "FAILED"));
    }
}
