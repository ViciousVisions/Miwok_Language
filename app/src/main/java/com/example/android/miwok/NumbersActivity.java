/*
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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static java.util.Arrays.asList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        int i = 0;

        ArrayList<String> englishNumberList = new ArrayList<String>();

        Collections.addAll(englishNumberList, "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten");


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, englishNumberList);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        //OLD VERSIONS OF THE CODE
        /**
         ArrayList<TextView> englishNumberViews = new ArrayList<TextView>();
         LinearLayout rootView = (LinearLayout) findViewById(R.id.rootNumbersView);
         rootView.setOrientation(LinearLayout.VERTICAL);
         **/

        /**
         i = 0;
         while (i < englishNumberList.size()) {
         englishNumberViews.add(new TextView(rootView.getContext()));
         englishNumberViews.get(i).setText(englishNumberList.get(i));
         rootView.addView(englishNumberViews.get(i));
         i++;
         }
         **/

        /**
         for (String englishNumber : englishNumberList) {
         TextView word = new TextView(rootView.getContext().);
         word.setText(englishNumberList.get(i++));
         rootView.addView(word);
         }
         **/

        /**
         i = 0;
         for (String englishNumber : englishNumberList) {
         Log.v("ArrayCheck", "For Loop: Word at index " + i++ + ": " + englishNumber);
         }

         i = 0;
         Iterator<String> listIterator = englishNumberList.iterator();
         while (listIterator.hasNext()) {
         Log.v("ArrayCheck", "While Loop 1: Word at index " + i++ + ": "
         + listIterator.next());
         }

         i = 0;
         while (i < englishNumberList.size()) {
         Log.v("ArrayCheck", "While Loop 2: Word at index " + i + ": "
         + englishNumberList.get(i++));
         }
         **/

        /** old method
         String[] englishNumberArray = {"one", "two", "three", "four", "five",
         "six", "seven", "eight", "nine", "ten"};

         for (String englishNumber : englishNumberArray) {
         Log.v("ArrayCheck", "Word at index " + i++ + ": " + englishNumber);
         }
         **/
    }
}
