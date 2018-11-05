package in.gotongroyong.gotongroyong.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class FirebaseAPI {
    private static final String FIREBASE_AUTH_TAG = "FIREBASE_AUTH";
    public static final String FIREBASE_GOOGLE_KEY = "470611138429-sk40l6hf8b5qq76m34fsp70u7932hcp2.apps.googleusercontent.com";

    private static FirebaseAuth firebase = FirebaseAuth.getInstance();

    public static FirebaseUser getLoggedUser() {
        return firebase.getCurrentUser();
    }

    public static void emailRegister(final Activity activity, String email, String password) {
        firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebase.getCurrentUser().getUid();
                    Log.d(FIREBASE_AUTH_TAG, "REGISTER EMAIL SUCCESS : " + uid);
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_EMAIL_REGISTER, API.IS_SUCCESS);
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "REGISTER EMAIL FAILURE : " + task.getException().getMessage());
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_EMAIL_REGISTER, API.ERROR_EMAIL_ALREADY_REGISTERED);
                }
            }
        });
    }

    public static void emailLogin(final Activity activity, String email, String password) {
        firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = firebase.getCurrentUser().getUid();
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN EMAIL SUCCESS : " + uid);
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_EMAIL_LOGIN, API.IS_SUCCESS);
                } else {
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN EMAIL FAILURE : " + task.getException().getMessage());
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_EMAIL_LOGIN, API.ERROR_WRONG_PASS);
                }
            }
        });
    }

    public static void loginGoogle(Activity activity, GoogleSignInClient client) {
        Intent intent = client.getSignInIntent();
        activity.startActivityForResult(intent, API.FIREBASE_GOOGLE_LOGIN);
    }

    public static void firebaseAuthWithGoogle(final Activity activity, GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebase.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_GOOGLE_LOGIN, API.IS_SUCCESS);
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN GOOGLE SUCCESS");
                } else {
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_GOOGLE_LOGIN, API.ERROR_EMAIL_ALREADY_REGISTERED);
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN GOOGLE FAILED");
                }
            }
        });
    }

    public static void loginFacebook(final Activity activity, AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebase.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_FACEBOOK_LOGIN, API.IS_SUCCESS);
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN FACEBOOK SUCCESS");
                } else {
                    ((ResultActivity) activity).onActivityResult(API.FIREBASE_FACEBOOK_LOGIN, API.ERROR_EMAIL_ALREADY_REGISTERED);
                    Log.d(FIREBASE_AUTH_TAG, "LOGIN FACEBOOK FAILURE", task.getException());
                }
            }
        });

    }

    public static void logout() {
        firebase.signOut();
        LoginManager.getInstance().logOut();
    }
}
