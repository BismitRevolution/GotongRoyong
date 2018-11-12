package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.data.body.AdsClickBody;
import in.gotongroyong.gotongroyong.data.body.GenerateAdsBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.AdsResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.DonationResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.GenerateAdsResponse;
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class StoryActivity extends AppCompatActivity implements ResultActivity, ResponseActivity {
    private final String STORY_TAG = "STORY";

    public static final String STORY_CAMPAIGN_ID = "story_campaign_id";

    public static final String STORY_VIDEO_TYPE = "story_video_type";
    public static final String STORY_DURATION = "story_duration";
    public static final String STORY_RESOURCES_URL = "story_resources_url";
    public static final String STORY_WEBSITE_URL = "story_website_url";

//    private boolean isVideo;
    private int id_donation;
    private int duration;
//    private ArrayList<String> resources;
    private String websiteUrl;
    private CountDownTimer timer;
    private ProgressBar progressBar;

    private boolean immersiveMode;
    private boolean isLoaded;
    private boolean isFinish;
    private GestureDetectorCompat detector;

    class StoryGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityY < 0) {
                openLink();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (immersiveMode) {
                hideSystemUI();
            } else {
                showSystemUI();
            }
            immersiveMode = !immersiveMode;
            return super.onSingleTapUp(e);
        }
    }

    private void openLink() {
        Log.d(STORY_TAG, "SWIPE LINK TO " + this.websiteUrl);
        if (isLoaded) {
            if (this.websiteUrl.equals("")) {
                Toast.makeText(getApplicationContext(), "Link is unavailable!", Toast.LENGTH_SHORT).show();
            } else {
                GotongRoyongAPI.adsClick(this, new AdsClickBody(this.id_donation));
                Router.gotoLink(getApplicationContext(), this.websiteUrl);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        isLoaded = false;

//        setParams();
        findContent();

        detector = new GestureDetectorCompat(this, new StoryGesture());
        detector.setIsLongpressEnabled(true);

        ProgressBar spinner = findViewById(R.id.spinner_loading);
        spinner.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);

        progressBar = findViewById(R.id.story_progress);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        this.isFinish = false;
    }

//    private void setParams() {
//        Intent intent = getIntent();
//        this.isVideo = intent.getBooleanExtra(STORY_VIDEO_TYPE, false);
//        this.duration = intent.getIntExtra(STORY_DURATION, 20000);
//        if (intent.getStringExtra(STORY_WEBSITE_URL) != null) {
//            this.websiteUrl = intent.getStringExtra(STORY_WEBSITE_URL);
//        } else {
//            this.websiteUrl = "";
//        }
//        if (intent.getStringArrayListExtra(STORY_RESOURCES_URL) != null) {
//            this.resources = intent.getStringArrayListExtra(STORY_RESOURCES_URL);
//        } else {
//            this.resources = new ArrayList<>();
//            this.resources.add("");
//        }
//    }

    private void findContent() {
        SharedPreferences savedData = getSharedPreferences(Preferences.SETTING_USER, MODE_PRIVATE);
        String api_token = savedData.getString(Preferences.USER_API_TOKEN, "");

        Intent intent = getIntent();
        int campaign_id = intent.getIntExtra(STORY_CAMPAIGN_ID, -1);

        GotongRoyongAPI.generateAds(this, api_token, new GenerateAdsBody(campaign_id));
    }

    private void createTimer() {
        timer = new CountDownTimer(duration, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                double current = (((double) duration - millisUntilFinished)/(double) duration) * 100;
                progressBar.setProgress((int) current);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                finish();
            }
        };
    }

    private void fetchData(GenerateAdsResponse response) {
        DonationResponse donation = response.getDonationData();
        AdsResponse ads = response.getAdsData();

        this.id_donation = donation.getIdAds();
        this.duration = ads.getDuration();
        this.websiteUrl = ads.getWebsiteUrl();

        ImageView clientLogo = findViewById(R.id.story_client_pic);
        Picasso.get().load(ads.getAdvLogo()).into(clientLogo);

        ((TextView) findViewById(R.id.story_client_name)).setText(ads.getAdvName());

        if (isVideo(ads.getAdsCategory())) {
            setVideoStory(ads.getContentUrl());
        } else {
            setImageStory(ads.getContentUrl());
        }
    }

    private boolean isVideo(int ads_category) {
        if (ads_category == 1) {
            return true;
        }
        return false;
    }

    private void setVideoStory(String url) {
        final VideoView video = findViewById(R.id.story_video);
        video.setVisibility(View.VISIBLE);

        if (!url.equals("")) {
            Uri uri = Uri.parse(url);
            video.setVideoURI(uri);
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    stopLoading();
                    showFeature();
                    mp.seekTo(2000);
                }
            });
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "END!", Toast.LENGTH_SHORT).show();
                    isFinish = true;
                }
            });
        } else {
            showErrorPanel();
            stopLoading();
        }
    }

    private void setImageStory(String url) {
        ImageView image = findViewById(R.id.story_image);
        image.setVisibility(View.VISIBLE);

        if (!url.equals("")) {
            Picasso.get().load(url).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    stopLoading();
                    showFeature();
                }

                @Override
                public void onError(Exception e) {
                    showErrorPanel();
                    stopLoading();
                }
            });
        } else {
            showErrorPanel();
            stopLoading();
        }
    }

    private void showLoading() {
        LinearLayout loadingSpinner = findViewById(R.id.loading_panel);
        loadingSpinner.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        LinearLayout loadingSpinner = findViewById(R.id.loading_panel);
        loadingSpinner.setVisibility(View.GONE);
    }

    private void showErrorPanel() {
        LinearLayout errorPanel = findViewById(R.id.error_panel);
        errorPanel.setVisibility(View.VISIBLE);
    }

    private void hideErrorPanel() {
        LinearLayout errorPanel = findViewById(R.id.error_panel);
        errorPanel.setVisibility(View.GONE);
    }

    private void showFeature() {
        LinearLayout topPanel = findViewById(R.id.top_panel);
        topPanel.setVisibility(View.VISIBLE);

        ImageView arrow = findViewById(R.id.up_arrow);
        arrow.setVisibility(View.VISIBLE);

        TextView slideUp = findViewById(R.id.up_text);
        slideUp.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);

        if (isLoaded) {
            VideoView video = findViewById(R.id.story_video);
            int action = MotionEventCompat.getActionMasked(event);
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (!isFinish) {
                        video.seekTo(0);
                        video.start();
                        timer.start();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!isFinish) {
                        video.pause();
                        timer.cancel();
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
            this.immersiveMode = true;
        }
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            case API.ADS_CLICK:
                if (resultCode == API.IS_SUCCESS) {
                    // Do something?
                }
                break;
        }
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
            case API.ADS_GENERATE:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        GenerateAdsResponse adsResponse = (GenerateAdsResponse) response;
                        isLoaded = true;
                        createTimer();
                        fetchData(adsResponse);
                    } catch (Exception e) {
                        errorUnknown();
                    }
                } else {
                    errorConnection();
                    showErrorPanel();
                    stopLoading();
                }
                break;
        }
    }

}
