package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.gotongroyong.gotongroyong.fragment.CampaignFragment;
import in.gotongroyong.gotongroyong.fragment.HistoryFragment;
import in.gotongroyong.gotongroyong.fragment.PahlawanFragment;
import in.gotongroyong.gotongroyong.fragment.ProfileFragment;

public class ProfileAdapter extends FragmentPagerAdapter {

    public ProfileAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new ProfileFragment();
            default:
                return new HistoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] pages = {"Data Pribadi", "Riwayat Donasi"};
        if (position > -1 && position < pages.length) {
            return pages[position];
        } else {
            return pages[0];
        }
    }
}
