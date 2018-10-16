package in.gotongroyong.gotongroyong.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.LoginActivity;
import in.gotongroyong.gotongroyong.MainScreen;
import in.gotongroyong.gotongroyong.RegisterActivity;
import in.gotongroyong.gotongroyong.StoryActivity;
import in.gotongroyong.gotongroyong.entity.Preferences;

public class Router {

    public static String checkAuth(Context context) {
        SharedPreferences userData = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        return userData.getString(Preferences.USER_ID, null);
    }

    public static void login(Context context, Bundle bundle) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE).edit();
        editor.putString(Preferences.USER_ID, bundle.getString(Preferences.USER_ID, ""));
        editor.putString(Preferences.USER_NAME, bundle.getString(Preferences.USER_NAME, "Unknown"));
        editor.putInt(Preferences.USER_TOTAL_DONATION, bundle.getInt(Preferences.USER_TOTAL_DONATION, 0));
        editor.putInt(Preferences.USER_TOTAL_SHARE, bundle.getInt(Preferences.USER_TOTAL_SHARE, 0));
        editor.putInt(Preferences.USER_EQUIVALENT, bundle.getInt(Preferences.USER_EQUIVALENT, 0));
        editor.apply();
    }

    public static void logout(Context context) {
        SharedPreferences userData = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        userData.edit().clear().apply();
    }

    public static void gotoLogin(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void gotoRegister(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    public static void gotoStory(Context context, ArrayList<String> resources, boolean isVideoType) {
        Intent story = new Intent(context, StoryActivity.class);
        story.putExtra(StoryActivity.STORY_VIDEO_TYPE, isVideoType);
        story.putStringArrayListExtra(StoryActivity.STORY_RESOURCES_URL, resources);
        context.startActivity(story);
    }

    public static void gotoMain(Context context) {
        context.startActivity(new Intent(context, MainScreen.class));
    }
}
