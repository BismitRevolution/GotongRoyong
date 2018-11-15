package in.gotongroyong.gotongroyong.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.ConfirmationActivity;
import in.gotongroyong.gotongroyong.EmptyActivity;
import in.gotongroyong.gotongroyong.LoginActivity;
import in.gotongroyong.gotongroyong.MainScreen;
import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.RegisterActivity;
import in.gotongroyong.gotongroyong.StoryActivity;
import in.gotongroyong.gotongroyong.TestActivity;
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

    public static void gotoStory(Context context, int campaign_id) {
        Intent story = new Intent(context, StoryActivity.class);
        story.putExtra(StoryActivity.STORY_CAMPAIGN_ID, campaign_id);
//        story.putExtra(StoryActivity.STORY_VIDEO_TYPE, isVideoType);
//        story.putStringArrayListExtra(StoryActivity.STORY_RESOURCES_URL, resources);
        context.startActivity(story);
    }

    public static void gotoConfirmation(Context context, int campaign_id) {
        Intent confirmation = new Intent(context, ConfirmationActivity.class);
        confirmation.putExtra(ConfirmationActivity.CONFIRMATION_CAMPAIGN_ID, campaign_id);
        context.startActivity(confirmation);
    }

    public static void gotoMain(Context context) {
        context.startActivity(new Intent(context, MainScreen.class));
    }

    public static void gotoDetail(Context context, int id) {
        Intent empty = new Intent(context, EmptyActivity.class);
        empty.putExtra(EmptyActivity.ACTIVITY_TYPE, EmptyActivity.ACTIVITY_DETAIL);
        empty.putExtra(EmptyActivity.CAMPAIGN_ID, id);
        context.startActivity(empty);
    }

    public static void gotoAbout(Context context) {
        Intent empty = new Intent(context, EmptyActivity.class);
        empty.putExtra(EmptyActivity.ACTIVITY_TYPE, EmptyActivity.ACTIVITY_ABOUT);
        context.startActivity(empty);
    }

    public static void gotoLink(Context context, String url) {
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri.parse(url));
        context.startActivity(link);
    }

    public static void share(Context context, int campaign_id, String campaign_title) {
        String deep_play_store = "https://play.google.com/store/apps/details?id=in.gotongroyong.gotongroyong&campaign=" + campaign_id;
        String message = campaign_title + "\n" + deep_play_store;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        context.startActivity(intent);
    }

    public static void sentEmail(Context context) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        String[] recipients = new String[]{ context.getResources().getString(R.string.app_email) };
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, "Customer Service");
        email.putExtra(Intent.EXTRA_TEXT, "I want to complain about this.");
        context.startActivity(Intent.createChooser(email, "Send Email"));
    }

    public static void sentWhatsapp(Context context) {
        try {
            Uri uri = Uri.parse("https://wa.me/" + context.getResources().getString(R.string.app_phone_plain) + "/?text=Hello%20Developer!");
            Intent whatsapp = new Intent(Intent.ACTION_VIEW, uri);
//            Intent whatsapp = new Intent(Intent.ACTION_SEND);
//            whatsapp.setType("text/plain");
            whatsapp.setPackage("com.whatsapp");
//            whatsapp.putExtra(Intent.EXTRA_TEXT, "Dear GotongRoyong, I want to complain about this.");
            context.startActivity(whatsapp);
        }
        catch (Exception e) {
            Toast.makeText(context, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void gotoTest(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }
}
