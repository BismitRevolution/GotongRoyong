package in.gotongroyong.gotongroyong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        redirectIfAuth();
        setContentView(R.layout.activity_login);

        final Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        final Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        redirectIfAuth();
    }

    private void login() {
        String id = "CLT001";
        String name = "Luthfi Abdurrahim";
        int totalDonation = 1234;
        int totalShare = 25;
        int equivalent = 125000;

        Bundle bundle = new Bundle();
        bundle.putString(Preferences.USER_ID, id);
        bundle.putString(Preferences.USER_NAME, name);
        bundle.putInt(Preferences.USER_TOTAL_DONATION, totalDonation);
        bundle.putInt(Preferences.USER_TOTAL_SHARE, totalShare);
        bundle.putInt(Preferences.USER_EQUIVALENT, equivalent);
//        Log.d("LOGIN", id + "|" + name + "|" + totalDonation + "|" + totalShare + "|" + equivalent + "|");

        Router.login(this, bundle);
        redirectIfAuth();
    }

    private void register() {
        Router.gotoRegister(this);
        finish();
    }

    private void redirectIfAuth() {
        String id = Router.checkAuth(this);
        if (id != null) {
            Router.gotoMain(this);
            finish();
        }
    }
}
