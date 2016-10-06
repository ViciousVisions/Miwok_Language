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
public class PhraseFragment extends Fragment {
    final static boolean ABANDON_AUDIO_FOCUS = true;
    MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            getOnAudioFocusChangeListener();


    public PhraseFragment() {
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
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going, false));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name, false));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is, false));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling, false));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good, false));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming, false));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming, false));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming, false));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go, false));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here, false));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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
                releaseMediaPlayer(ABANDON_AUDIO_FOCUS);

                // Request audio focus (permission to play the audio file). The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int audioPermissionStatus = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //Don't play audio unless the app has received permission
                if (audioPermissionStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(),
                            words.get(position).getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            //Turn off MediaPlayer
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

    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener() {
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
