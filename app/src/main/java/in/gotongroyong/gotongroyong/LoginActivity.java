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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import in.gotongroyong.gotongroyong.api.FirebaseAPI;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.entity.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.gotongroyong.gotongroyong.api.FirebaseAPI.FIREBASE_GOOGLE_KEY;

public class LoginActivity extends AppCompatActivity implements ResultActivity {
    Activity activity = this;
    GoogleSignInClient googleClient;
    CallbackManager facebookManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        redirectIfAuth();
        setContentView(R.layout.activity_login);

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

        final Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((TextView) findViewById(R.id.field_email)).getText().toString();
                String password = ((TextView) findViewById(R.id.field_password)).getText().toString();
                if (validate(email, password)) {
                    FirebaseAPI.emailLogin(activity, email, password);
                } else {
                    warningRequired();
                }
            }
        });

        final Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        final Button loginGoogle = findViewById(R.id.btn_login_google);
        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAPI.loginGoogle(activity, googleClient);
            }
        });

        final Button loginFacebook = findViewById(R.id.btn_login_facebook);
        loginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
            }
        });
//        loginFacebook.setReadPermissions("email", "public_profile");
        LoginManager.getInstance().registerCallback(facebookManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                FirebaseAPI.loginFacebook(activity, loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FB LOGIN", "CANCEL");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FB LOGIN", "ERROR", error);
            }
        });
    }

    private void initAuth() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(FIREBASE_GOOGLE_KEY)
                .requestEmail()
                .build();

        googleClient = GoogleSignIn.getClient(this, gso);
        facebookManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        redirectIfAuth();
    }

    private void register() {
        Router.gotoRegister(this);
        finish();
    }

    private boolean validate(String email, String password) {
        return Util.isValidEmail(email) && !Util.isEmpty(password);
    }

    private void warningRequired() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_empty));
    }

    private void warningWrongPassword() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_wrong_password));
    }

    private void warningUserNotRegistered() {
        TextView warning = findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_user_not_found));
    }

    private void warningEmailAlreadyRegistered() {
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
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            case API.FIREBASE_EMAIL_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    gtgEmailLogin();
                } else if (resultCode == API.ERROR_WRONG_PASS) {
                    warningWrongPassword();
                }
                break;
            case API.FIREBASE_GOOGLE_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    gtgGoogleLogin();
                } else if (resultCode == API.ERROR_EMAIL_ALREADY_REGISTERED) {
                    warningEmailAlreadyRegistered();
                }
                break;
            case API.FIREBASE_FACEBOOK_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    gtgFacebookLogin();
                } else if (resultCode == API.ERROR_EMAIL_ALREADY_REGISTERED) {
                    warningEmailAlreadyRegistered();
                }
                break;
            case API.AUTH_EMAIL_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    redirectIfAuth();
                } else if (resultCode == API.ERROR_WRONG_PASS) {
                    warningWrongPassword();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                } else {
                    warningUnknown();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                }
                break;
            case API.AUTH_FACEBOOK_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    redirectIfAuth();
                } else if (resultCode == API.ERROR_NOT_REGISTERED) {
                    warningUserNotRegistered();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                } else {
                    warningUnknown();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                }
                break;
            case API.AUTH_GOOGLE_LOGIN:
                if (resultCode == API.IS_SUCCESS) {
                    redirectIfAuth();
                } else if (resultCode == API.ERROR_NOT_REGISTERED) {
                    warningUserNotRegistered();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                } else {
                    warningUnknown();
                    GotongRoyongAPI.clearData(getApplicationContext());
                    FirebaseAPI.logout();
                }
                break;
        }
    }

    public void gtgEmailLogin() {
        String email = ((TextView) findViewById(R.id.field_email)).getText().toString();
        String password = ((TextView) findViewById(R.id.field_password)).getText().toString();
        EmailLoginBody body = new EmailLoginBody(email, password);
        GotongRoyongAPI.emailLogin(this, body);
    }

    public void gtgGoogleLogin() {
        FirebaseUser logged = FirebaseAuth.getInstance().getCurrentUser();
        GoogleLoginBody body = new GoogleLoginBody(logged.getUid());
        GotongRoyongAPI.googleLogin(this, body);
    }

    public void gtgFacebookLogin() {
        FirebaseUser logged = FirebaseAuth.getInstance().getCurrentUser();
        FacebookLoginBody body = new FacebookLoginBody(logged.getUid());
        GotongRoyongAPI.facebookLogin(this, body);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookManager.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case API.FIREBASE_GOOGLE_LOGIN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    FirebaseAPI.firebaseAuthWithGoogle(activity, account);
                } catch (ApiException e) {
                    Log.w("GSO", "Exception");
                    warningUnknown();
                }
        }
    }
}
