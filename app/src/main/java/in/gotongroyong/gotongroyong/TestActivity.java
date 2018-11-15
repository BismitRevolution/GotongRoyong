package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.AdsClickBody;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
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

//    private GLSurfaceView glSurfaceView;
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;

//    class MyGLRenderer implements GLSurfaceView.Renderer {
//        @Override
//        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//            gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
//        }
//
//        public void onSurfaceChanged(GL10 gl, int w, int h) {
//            gl.glViewport(0, 0, w, h);
//        }
//
//        public void onDrawFrame(GL10 gl) {
//            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testAPI();
                share();
            }
        });


//        String url = "https://gotongroyong.online/Uploads/ads-content/7/1540609561/3-TVC-ELZATTA-2018.mp4";
//        Uri uri = Uri.parse(url);

//        glSurfaceView = findViewById(R.id.gl_sv_test);
//        glSurfaceView.setRenderer(new MyGLRenderer());
//        surfaceView = findViewById(R.id.sv_test);
//        mediaPlayer = new MediaPlayer();
//        SurfaceHolder holder = glSurfaceView.getHolder();
//        SurfaceHolder holder = surfaceView.getHolder();

//        holder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                mediaPlayer.setDisplay(holder);
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                mediaPlayer.setDisplay(null);
//            }
//        });

//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//            }
//        });

//        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

//        try {
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDataSource(url);
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void share() {
//        String pkg = "id.ac.ui.cs.mobileprogramming.ibrahim.gitprofile";
//        String youtube = "com.google.android.youtube";
//        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + pkg));
//        intent.setPackage(youtube);
//        String shareLine = "https://line.me/R/msg/text/?" + pkg;
        String gtg = "in.gotongroyong.gotongroyong";
        String deep_link = "gotongroyong://campaign/1";
        String deep_link_http = "http://www.gotongroyong.in/campaign/1";
        String deep_play_store = "https://play.google.com/store/apps/details?id=in.gotongroyong.gotongroyong&campaign=1";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, deep_play_store);
        intent.setType("text/plain");
        startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shareLine));
//        startActivity(intent);
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
