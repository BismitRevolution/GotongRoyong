package in.gotongroyong.gotongroyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import in.gotongroyong.gotongroyong.fragment.AboutFragment;
import in.gotongroyong.gotongroyong.fragment.DetailFragment;

public class EmptyActivity extends AppCompatActivity implements ResultActivity {
    public static final String ACTIVITY_TYPE = "activity_type";
    public static final String CAMPAIGN_ID = "campaign_id";

    public static final String ACTIVITY_DETAIL = "activity_detail";
    public static final String ACTIVITY_ABOUT = "activity_about";

    DetailFragment detailFragment;
    AboutFragment aboutFragment;
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
        aboutFragment = new AboutFragment();

        setPage();
    }

    private void setPage() {
        Intent intent = getIntent();
        switch (intent.getStringExtra(ACTIVITY_TYPE)) {
            case ACTIVITY_DETAIL:
                setDetailFragment();
                break;
            case ACTIVITY_ABOUT:
                setAboutFragment();
                break;
        }
    }

    private void setDetailFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, detailFragment).commit();
        getSupportActionBar().setTitle(detailFragment.getTitle());
    }

    private void setAboutFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, aboutFragment).commit();
        getSupportActionBar().setTitle(aboutFragment.getTitle());
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

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            default:
                getSupportActionBar().setTitle("Specific Campaign");
                break;
        }
    }
}
