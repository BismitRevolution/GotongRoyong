package in.gotongroyong.gotongroyong;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.adapter.CampaignPageAdapter;
import in.gotongroyong.gotongroyong.adapter.ProfilePageAdapter;
import in.gotongroyong.gotongroyong.fragment.BaseFragment;

public class MainScreen extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout drawer;
    private TabLayout tab;
    private ViewPager pager;
    private CampaignPageAdapter campaignPageAdapter;
    private ProfilePageAdapter profilePageAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = findViewById(R.id.main_toolbar);
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

        this.campaignPageAdapter = new CampaignPageAdapter(getSupportFragmentManager());
        this.profilePageAdapter = new ProfilePageAdapter(getSupportFragmentManager());
        this.pager = findViewById(R.id.main_pager);
        this.tab = findViewById(R.id.main_tab);

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

        setCampaignPage();
//        startActivity(new Intent(this, TestActivity.class));
    }

    private void clear() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.addAll(campaignPageAdapter.getFragments());
        fragments.addAll(profilePageAdapter.getFragments());
        for (int i = 0; i < fragments.size(); i++) {
            getSupportFragmentManager().beginTransaction().remove((Fragment) fragments.get(i)).commit();
        }
        campaignPageAdapter.getFragments().clear();
        profilePageAdapter.getFragments().clear();
    }

    private void setCampaignPage() {
        clear();
        getSupportActionBar().setTitle(R.string.toolbar_campaign);
        campaignPageAdapter.reset();
        pager.setAdapter(campaignPageAdapter);
        tab.setupWithViewPager(pager);
    }

    private void setProfilePage() {
        clear();
        getSupportActionBar().setTitle(R.string.toolbar_profile);
        profilePageAdapter.reset();
        pager.setAdapter(profilePageAdapter);
        tab.setupWithViewPager(pager);
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
