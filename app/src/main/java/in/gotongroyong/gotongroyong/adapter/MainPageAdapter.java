package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.gotongroyong.gotongroyong.fragment.CampaignFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CampaignFragment();
            case 1:
                return new CampaignFragment();
            default:
                return new CampaignFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] pages = {"Campaigns", "Pahlawan"};
        if (position > -1 && position < pages.length) {
            return pages[position];
        } else {
            return pages[0];
        }
    }
}
