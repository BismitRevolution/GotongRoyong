package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {
    public static final String STORY_VIDEO_TYPE = "story_video_type";
    public static final String STORY_DURATION = "story_duration";
    public static final String STORY_RESOURCES_URL = "story_resources_url";

    private boolean isVideo;
    private int duration;
    private ArrayList<String> resources;
    private CountDownTimer timer;
    private ProgressBar progressBar;

    private boolean immersiveMode;
    private GestureDetectorCompat detector;

    class StoryGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityY < 0) {
                Toast.makeText(getApplicationContext(), "FLING UP!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        setParams(getIntent());
        setContent();

        detector = new GestureDetectorCompat(this, new StoryGesture());
        detector.setIsLongpressEnabled(true);

        progressBar = findViewById(R.id.story_progress);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        final double total = 20 * 1000;

        timer = new CountDownTimer((int) total, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                double current = ((total - millisUntilFinished)/total) * 100;
                progressBar.setProgress((int) current);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
            }
        };
    }

    private void setParams(Intent intent) {
        this.isVideo = intent.getBooleanExtra(STORY_VIDEO_TYPE, false);
        this.duration = intent.getIntExtra(STORY_DURATION, 20000);
        if (intent.getStringArrayListExtra(STORY_RESOURCES_URL) != null) {
            this.resources = intent.getStringArrayListExtra(STORY_RESOURCES_URL);
        } else {
            this.resources = new ArrayList<>();
            this.resources.add("");
        }
    }

    private void setContent() {
        String url = (this.resources.size() > 0)? this.resources.get(0) : "";
        if (this.isVideo) {
            setVideoStory(url);
        } else {
            setImageStory(url);
        }
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
                    mp.seekTo(2000);
                }
            });
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "CONFIRMATION!", Toast.LENGTH_SHORT).show();
                }
            });
            hideErrorPanel();
        } else {
            showErrorPanel();
        }
    }

    private void setImageStory(String url) {
        ImageView image = findViewById(R.id.story_image);
        image.setVisibility(View.VISIBLE);

        if (!url.equals("")) {
            Picasso.get().load(url).into(image);
            hideErrorPanel();
        } else {
            showErrorPanel();
        }
    }

    private void showErrorPanel() {
        LinearLayout errorPanel = findViewById(R.id.error_panel);
        errorPanel.setVisibility(View.VISIBLE);
    }

    private void hideErrorPanel() {
        LinearLayout errorPanel = findViewById(R.id.error_panel);
        errorPanel.setVisibility(View.GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);

        VideoView video = findViewById(R.id.story_video);
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                video.seekTo(0);
                video.start();
                timer.start();
                break;
            case MotionEvent.ACTION_UP:
                video.pause();
                timer.cancel();
                break;
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
}
