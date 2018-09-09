package in.gotongroyong.gotongroyong.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import in.gotongroyong.gotongroyong.fragment.BaseFragment;
import in.gotongroyong.gotongroyong.fragment.CampaignFragment;
import in.gotongroyong.gotongroyong.fragment.PahlawanFragment;

public class CampaignPageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> fragments;

    public CampaignPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        reset();
    }

    public void reset() {
        fragments.add(new CampaignFragment());
        fragments.add(new PahlawanFragment());
    }

    public ArrayList<BaseFragment> getFragments() {
        return fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
