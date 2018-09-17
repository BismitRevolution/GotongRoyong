package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import in.gotongroyong.gotongroyong.common.Router;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        redirectIfAuth();
    }

    private void login() {
        Router.login(this, "CLT001", "Client 1");
        redirectIfAuth();
    }

    private void redirectIfAuth() {
        String id = Router.checkAuth(this);
        if (id != null) {
            Router.gotoMain(this);
            finish();
        }
    }
}
