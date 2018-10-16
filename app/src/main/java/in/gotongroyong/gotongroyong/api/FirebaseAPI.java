package in.gotongroyong.gotongroyong.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.entity.FirebaseCode;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class FirebaseAPI {
    private static final String FIREBASE_AUTH_TAG = "FIREBASE_AUTH";
    public static final String FIREBASE_GOOGLE_KEY = "470611138429-sk40l6hf8b5qq76m34fsp70u7932hcp2.apps.googleusercontent.com";

    private static FirebaseAuth firebase = FirebaseAuth.getInstance();

    public static FirebaseUser getLoggedUser() {
        return firebase.getCurrentUser();
    }

    public static void register(final Activity activity, String email, String password) {
        firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebase.getCurrentUser().getUid();
                    Log.d(FIREBASE_AUTH_TAG, "REGISTER EMAIL SUCCESS : " + uid);
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_EMAIL_REGISTER, FirebaseCode.AUTH_SUCCESS);
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "REGISTER EMAIL FAILURE : " + task.getException().getMessage());
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_EMAIL_REGISTER, FirebaseCode.AUTH_UNKNOWN_ERROR);
                }
            }
        });
    }

    public static void login(final Activity activity, String email, String password) {
        firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebase.getCurrentUser().getUid();
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN EMAIL SUCCESS : " + uid);
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_EMAIL_LOGIN, FirebaseCode.AUTH_SUCCESS);
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN EMAIL FAILURE : " + task.getException().getMessage());
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_EMAIL_LOGIN, FirebaseCode.AUTH_WRONG_PASS);
                }
            }
        });
    }

    public static void loginGoogle(Activity activity, GoogleSignInClient client) {
        Intent intent = client.getSignInIntent();
        activity.startActivityForResult(intent, FirebaseCode.AUTH_GOOGLE_LOGIN);
    }

    public static void firebaseAuthWithGoogle(final Activity activity, GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebase.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_GOOGLE_LOGIN, FirebaseCode.AUTH_SUCCESS);
                    Log.d("GSO", "LOGIN SUCCESS");
                } else {
                    ((ResultActivity) activity).onActivityResult(FirebaseCode.AUTH_GOOGLE_LOGIN, FirebaseCode.AUTH_UNKNOWN_ERROR);
                    Log.d("GSO", "LOGIN FAILED");
                }
            }
        });
    }

    public static void logoutGoogle() {
        firebase.signOut();
    }

    public static void clearData(Context context) {
        SharedPreferences userData = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        userData.edit().clear().apply();
    }

    public static void saveData(Context context) {
        FirebaseUser loggedUser = FirebaseAPI.getLoggedUser();
        String id = loggedUser.getUid();
        String name = loggedUser.getDisplayName();
        int totalDonation = 1234;
        int totalShare = 25;
        int equivalent = 125000;

        SharedPreferences.Editor editor = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE).edit();
        editor.putString(Preferences.USER_ID, id);
        editor.putString(Preferences.USER_NAME, name);
        editor.putInt(Preferences.USER_TOTAL_DONATION, totalDonation);
        editor.putInt(Preferences.USER_TOTAL_SHARE, totalShare);
        editor.putInt(Preferences.USER_EQUIVALENT, equivalent);
        editor.apply();
    }
}
