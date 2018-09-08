package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import in.gotongroyong.gotongroyong.adapter.MainPageAdapter;

public class MainScreen extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.main_pager);
        pager.setAdapter(adapter);

//        startActivity(new Intent(this, TestActivity.class));
    }
}
