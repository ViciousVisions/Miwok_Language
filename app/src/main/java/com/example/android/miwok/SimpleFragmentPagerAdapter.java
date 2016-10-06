package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Code__000 on 10/6/2016.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    final static int NUMBER_OF_PAGES = 4;

    public SimpleFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // In order for the ViewPager to display page 0,
    // the ViewPager asks the adapter for the 0th fragment.
    // The getItem(int position) method returns the fragment for the requested page.
    // When the user swipes leftward, we move onto page 1,
    // which means the ViewPager asks the adapter for the fragment at position 1.
    // When we get to page 2, the ViewPager asks the adapter for the fragment at position 2.
    // Thus, depending on which page (also known as position), the user has swiped to,
    // the corresponding fragment gets shown.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new NumbersFragment();
            case 1: return new FamilyFragment();
            case 2: return new ColorFragment();
            case 3: return new PhraseFragment();
            default: return null;
        }
    }

    // When the app is launched on a device,
    // first the ViewPager asks the adapter how many pages there will be.
    // In this case, the adapter will says there are 4 pages.
    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

