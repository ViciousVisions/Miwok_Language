package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = getOnAudioFocusChangeListener();
    final static boolean ABANDON_AUDIO_FOCUS = true;


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Get the {@link AudioManager} plugged into the system so that it can send and
        // receive requests for AutoFocus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int audioPermissionStatus = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT );
                if(audioPermissionStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer(ABANDON_AUDIO_FOCUS);
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer(ABANDON_AUDIO_FOCUS);
                        }
                    });
                }
                Log.v("MediaClick", "Clicked");
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(ABANDON_AUDIO_FOCUS);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer(boolean abandonAudioFocus) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            if (abandonAudioFocus) {
                //The file is finished playing so the app no longer needs AudioFocus
                //Example: This would allow user to continue listening to music in the
                //background while still using the app
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
    }


    private AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener(){
        return new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    // Pause playback because your Audio Focus was
                    // temporarily stolen, but will be back soon.
                    // i.e. for a phone call
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange ==
                        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // Lower the volume or pause, because another app has requested
                    // to play audio over this app
                    // i.e. for notifications or navigation directions
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // Stop playback, because the app has lost Audio Focus.
                    // i.e. the user started some other playback app
                    // Remember to unregister your controls/buttons here.
                    // Release the Kraken! err umm ... Audio Focus!
                    releaseMediaPlayer(ABANDON_AUDIO_FOCUS);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // Resume playback, because the app has regained the Audio Focus
                    // i.e. the phone call ended or the nav directions are finished
                    // If you implement ducking and lower the volume, be
                    // sure to return it to normal here, as well.
                    mMediaPlayer.start();
                }
            }
        };
    }

}
