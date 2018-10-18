package in.gotongroyong.gotongroyong.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.EmptyActivity;
import in.gotongroyong.gotongroyong.LoginActivity;
import in.gotongroyong.gotongroyong.MainScreen;
import in.gotongroyong.gotongroyong.RegisterActivity;
import in.gotongroyong.gotongroyong.StoryActivity;
import in.gotongroyong.gotongroyong.TestActivity;
import in.gotongroyong.gotongroyong.entity.Preferences;
import in.gotongroyong.gotongroyong.fragment.DetailFragment;

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

    public static void gotoStory(Context context, ArrayList<String> resources, boolean isVideoType) {
        Intent story = new Intent(context, StoryActivity.class);
        story.putExtra(StoryActivity.STORY_VIDEO_TYPE, isVideoType);
        story.putStringArrayListExtra(StoryActivity.STORY_RESOURCES_URL, resources);
        context.startActivity(story);
    }

    public static void gotoMain(Context context) {
        context.startActivity(new Intent(context, MainScreen.class));
    }

    public static void gotoDetail(Context context, String id) {
        Intent detail = new Intent(context, EmptyActivity.class);
        detail.putExtra(EmptyActivity.CAMPAIGN_ID, id);
        context.startActivity(detail);
    }

    public static void gotoTest(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }
}
