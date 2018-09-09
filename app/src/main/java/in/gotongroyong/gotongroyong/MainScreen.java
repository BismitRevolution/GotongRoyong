package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import in.gotongroyong.gotongroyong.adapter.CampaignAdapter;
import in.gotongroyong.gotongroyong.adapter.ProfileAdapter;

public class MainScreen extends AppCompatActivity {
    private DrawerLayout drawer;
    private ViewPager pager;
    private CampaignAdapter campaignAdapter;
    private ProfileAdapter profileAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.drawer_navigation);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.menu_campaign:
                        setCampaignPage();
                        break;
                    case R.id.menu_profile:
                        setProfilePage();
                        break;
                    default:
                        setCampaignPage();
                }
                drawer.closeDrawers();
                return true;
            }
        });

        this.campaignAdapter = new CampaignAdapter(getSupportFragmentManager());
        this.profileAdapter = new ProfileAdapter(getSupportFragmentManager());
        this.pager = findViewById(R.id.main_pager);
        setCampaignPage();
        final TabLayout tab = findViewById(R.id.main_tab);
        tab.setupWithViewPager(pager);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        startActivity(new Intent(this, TestActivity.class));
    }

    private void setCampaignPage() {
        pager.setAdapter(campaignAdapter);
    }

    private void setProfilePage() {
        pager.setAdapter(profileAdapter);
    }

    @Override
    // ActionBar menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
