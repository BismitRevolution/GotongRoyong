package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.gotongroyong.gotongroyong.common.Router;

public class LoadingScreen extends AppCompatActivity {
    private static final boolean DEBUG_MODE = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_loading);

        Thread loading = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    finishLoading();
                } finally {
                    finishLoading();
                }
            }
        };

        loading.start();
    }

    private void finishLoading() {
        if (DEBUG_MODE) {
            Router.gotoTest(this);
        } else {
            Router.gotoMain(this);
        }
        finish();
    }
}
