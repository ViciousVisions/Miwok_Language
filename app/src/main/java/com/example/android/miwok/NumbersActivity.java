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

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        int i = 0;

        //ArrayList<String> englishNumberList = new ArrayList<String>();
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list_view);
//        mediaPlayer = MediaPlayer.create(this,R.raw.number_one);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getAudioResourceId());
                mediaPlayer.start();
                Log.v("MediaClick","Clicked");
            }
        });

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);










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
