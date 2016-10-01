package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Code__000 on 9/30/2016.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */
    public WordAdapter(Activity context, ArrayList<Word> words) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,words);

        //Note: context and words are passed to the ArrayAdapter's constructor by way of "super".
        //Note: Now ArrayAdapter has full access to the ArrayList "words".
        //Note: So the method getItem(position) knows to take the item from the ArrayList "words"
        //      at the location given by "position"
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * Note: ArrayAdapter is essentially requesting that the data located at "position"
     * be placed in "convertView" so that ArrayAdapter can send it out to be displayed
     * (in this case to a ListView)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        //Note: This item contains the english and miwok words to be displayed
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID englishWord
        //Note: In this application listItemView is essentially one card made up of two strings
        //      of text.  If the card doesn't exist then it is inflated from the xml file.
        //      (see above).  Now that the card does exist the relevant views are extracted and
        //      modified.
        //Note: There can be multiple TextViews built from xml template associated with the
        //      id R.id.englishWord. In this case there is only one created per card.
        //Note: By this point the listItemView exists, so all we are doing is changing the
        //      text string for the english word and miwok word within the existing card (lisItemView)
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);
        // Get the english name from the current Word object and
        // set this text on the english TextView
        englishTextView.setText(currentWord.getEnglishWord());

        // Find the TextView in the list_item.xml layout with the ID miwokWord
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok name from the current Word object and
        // set this text on the miwok TextView
        miwokTextView.setText(currentWord.getMiwokWord());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}