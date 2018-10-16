package in.gotongroyong.gotongroyong.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.gotongroyong.gotongroyong.AuthActivity;
import in.gotongroyong.gotongroyong.common.Router;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class FirebaseAPI {
    private static final String FIREBASE_AUTH_TAG = "FIREBASE_AUTH";
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
                    saveData(activity.getApplicationContext());
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "REGISTER EMAIL FAILURE : " + task.getException().getMessage());
                    Router.logout(activity.getApplicationContext());
                }
                ((AuthActivity) activity).redirectIfAuth();
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
                    saveData(activity.getApplicationContext());
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN EMAIL FAILURE : " + task.getException().getMessage());
                    Router.logout(activity.getApplicationContext());
                }
                ((AuthActivity) activity).redirectIfAuth();
            }
        });
    }

    private static void saveData(Context context) {
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
