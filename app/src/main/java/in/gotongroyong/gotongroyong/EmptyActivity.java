package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import in.gotongroyong.gotongroyong.fragment.DetailFragment;

public class EmptyActivity extends AppCompatActivity {
    public static final String CAMPAIGN_ID = "CAMPAIGN_ID";

    DetailFragment detailFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_empty);

        this.toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        detailFragment = new DetailFragment();
        setDetailFragment();
    }

    private void setDetailFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, detailFragment).commit();
    }

    @Override
    // ActionBar menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
