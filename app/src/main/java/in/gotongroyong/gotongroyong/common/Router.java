package in.gotongroyong.gotongroyong.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import in.gotongroyong.gotongroyong.LoginActivity;
import in.gotongroyong.gotongroyong.MainScreen;
import in.gotongroyong.gotongroyong.RegisterActivity;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class Router {

    public static String checkAuth(Context context) {
        SharedPreferences userData = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        return userData.getString(Preferences.USER_ID, null);
    }

    public static void gotoLogin(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void gotoRegister(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    public static void gotoMain(Context context) {
        context.startActivity(new Intent(context, MainScreen.class));
    }
}
