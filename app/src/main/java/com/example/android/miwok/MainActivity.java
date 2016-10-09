/*
 * Concepts Bookmarked
 * - Audio
 * - TabLayout
 * - ViewPager
 * -
 *
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.android.miwok.R;
import com.example.android.miwok.SimpleFragmentPagerAdapter;
import static android.R.attr.fragment;

// import static com.example.android.miwok.R.id.numbers;

public class MainActivity extends AppCompatActivity {
    int mSelectedColor;
    int mNormalColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        // Where the view pager is located
        setContentView(R.layout.activity_main);

        // Get the view pager (the layout that allows swiping between pages)
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // Creates the adapter that provides the correct page fragment
        final SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Pass the ViewPager to the TabLayout
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Set the initial tab text colors
        // Set current tab color based on its category color
        mSelectedColor = getSelectedColor(tabLayout.getSelectedTabPosition());
        // Set unselected tab colors to unselectedTabTextColor
        mNormalColor = ContextCompat.getColor(MainActivity.this, R.color.unselectedTabTextColor);
        tabLayout.setTabTextColors(mNormalColor, mSelectedColor);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // Listen for when new tab is selected
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.v("MainActivity", "OnTabSelected " + tab.getPosition());
                // Set current tab color based on its category color
                mSelectedColor = getSelectedColor(tab.getPosition());
                // Set unselected tab colors to white
                mNormalColor = ContextCompat.getColor(MainActivity.this,
                        R.color.unselectedTabTextColor);
                tabLayout.setTabTextColors(mNormalColor, mSelectedColor);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.v("MainActivity", "OnTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.v("MainActivity", "OnTabRe");
            }
        });


        // Removes sunken tab bar effect.
        // The shading between the ActionBar and the Tabs is removed
        this.getSupportActionBar().setElevation(0);

        // tabLayout.setElevation(0);
        // tabLayout.setBackgroundColor(color1);


    }

    public int getSelectedColor(int position) {
        switch (position) {
            case 0:
                return ContextCompat.getColor(MainActivity.this, R.color.category_numbers);
            case 1:
                return ContextCompat.getColor(MainActivity.this, R.color.category_family);
            case 2:
                return ContextCompat.getColor(MainActivity.this, R.color.category_colors);
            case 3:
                return ContextCompat.getColor(MainActivity.this, R.color.category_phrases);
            default:
                return ContextCompat.getColor(MainActivity.this, R.color.english_text_color);
        }

    }

}
