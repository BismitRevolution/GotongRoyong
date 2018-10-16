package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

import in.gotongroyong.gotongroyong.api.FirebaseAPI;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import in.gotongroyong.gotongroyong.data.TestData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private final String FIREBASE_GOOGLE_KEY = "470611138429-sk40l6hf8b5qq76m34fsp70u7932hcp2.apps.googleusercontent.com";
    private int requestCode = 10;

    FirebaseAuth firebase;
    GoogleSignInClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(FIREBASE_GOOGLE_KEY)
                .requestEmail()
                .build();

        firebase = FirebaseAuth.getInstance();
        client = GoogleSignIn.getClient(this, gso);

//        googleLogin();
        googleLogout();
    }

    private void googleLogin() {
        Intent intent = client.getSignInIntent();
        startActivityForResult(intent, requestCode);
    }

    private void googleLogout() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == this.requestCode) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("GSO", "Exception");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebase.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebase.getCurrentUser();
                    Log.d("GSO", "LOGIN SUCCESS");
                } else {
                    Log.d("GSO", "LOGIN FAILED");
                }
            }
        });
    }

    private void testAPI() {
        Call<BaseResponse<List<CampaignData>>> call = new GotongRoyongAPI().getService().listCampaign(1);
        call.enqueue(new Callback<BaseResponse<List<CampaignData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CampaignData>>> call, Response<BaseResponse<List<CampaignData>>> response) {
                if (response.isSuccessful()) {
                    List<CampaignData> result = response.body().getPayload();
                    updateText(result.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CampaignData>>> call, Throwable t) {
                t.printStackTrace();
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
