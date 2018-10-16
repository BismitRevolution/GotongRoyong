package in.gotongroyong.gotongroyong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import in.gotongroyong.gotongroyong.api.FirebaseAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.entity.FirebaseCode;
import in.gotongroyong.gotongroyong.entity.Preferences;

import static in.gotongroyong.gotongroyong.api.FirebaseAPI.FIREBASE_GOOGLE_KEY;

public class RegisterActivity extends AppCompatActivity implements ResultActivity {
    Activity activity = this;
    GoogleSignInClient googleClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        redirectIfAuth();
        setContentView(R.layout.activity_register);

        initAuth();

        ((TextView) findViewById(R.id.field_email)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearWarning();
            }
        });

        ((TextView) findViewById(R.id.field_password)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearWarning();
            }
        });

        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((TextView) findViewById(R.id.field_email)).getText().toString();
                String password = ((TextView) findViewById(R.id.field_password)).getText().toString();
                if (validate(email, password)) {
                    FirebaseAPI.register(activity, email, password);
                } else {
                    warningRequired();
                }
            }
        });

        final Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        final Button loginGoogle = findViewById(R.id.btn_register_google);
        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAPI.loginGoogle(activity, googleClient);
            }
        });
    }

    private void initAuth() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(FIREBASE_GOOGLE_KEY)
                .requestEmail()
                .build();

        googleClient = GoogleSignIn.getClient(this, gso);
    }

    private boolean validate(String email, String password) {
        return Util.isValidEmail(email) && !Util.isEmpty(password);
    }

    private void warningRequired() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_empty));
    }

    private void warningAlreadyRegistered() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_account_already_registered));
    }

    private void warningUnknown() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_unknown_error));
    }

    private void clearWarning() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText("");
    }

    public void redirectIfAuth() {
        String id = Router.checkAuth(this);
        if (id != null) {
            Router.gotoMain(this);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        redirectIfAuth();
    }

    private void login() {
        Router.gotoLogin(this);
        finish();
    }

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            case FirebaseCode.AUTH_EMAIL_REGISTER:
                if (resultCode == FirebaseCode.AUTH_SUCCESS) {
                    FirebaseAPI.saveData(getApplicationContext());
                    redirectIfAuth();
                } else {
                    warningAlreadyRegistered();
                }
                break;
            case FirebaseCode.AUTH_GOOGLE_LOGIN:
                if (resultCode == FirebaseCode.AUTH_SUCCESS) {
                    FirebaseAPI.saveData(getApplicationContext());
                    redirectIfAuth();
                } else if (resultCode == FirebaseCode.AUTH_UNKNOWN_ERROR) {
                    warningUnknown();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FirebaseCode.AUTH_GOOGLE_LOGIN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    FirebaseAPI.firebaseAuthWithGoogle(activity, account);
                } catch (ApiException e) {
                    Log.w("GSO", "Exception");
                }
        }
    }

}
