package com.example.tanishqsaluja.musicbox;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.SeekBar;

import com.ohoussein.playpause.PlayPauseView;
import com.richpath.RichPath;
import com.richpath.RichPathView;
import com.richpathanimator.RichPathAnimator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private RichPathView notificationsRichPathView;
    PlayPauseView playPauseView;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    ArrayList<List> myList=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList.add(new List(R.raw.coming));
        myList.add(new List(R.raw.feelmyface));
        Adapter adapter=new Adapter(myList,this);
        recyclerView=findViewById(R.id.rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        initcontrols();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TEST","OnPause was called: ");
         //without this the music player would have stopped after 3-4 sec of pausing
        //cannot release media player from here since we have not created it here,we created it in Adapter class
        //mediaPlayer.release();
    }

    private void initcontrols(){
        volumeSeekbar=findViewById(R.id.seekbar);
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        //setting those properties of audio manager into seekbar
        volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    /*    notificationsRichPathView = findViewById(R.id.ic_notifications);
        final RichPath top = notificationsRichPathView.findRichPathByName("top");
        final RichPath bottom = notificationsRichPathView.findRichPathByName("bottom");
        notificationsRichPathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RichPathAnimator.animate(top)
                                .interpolator(new DecelerateInterpolator())
                                .trimPathOffset(0, 1.0f)
                                .rotation(0, 20, -20, 10, -10, 5, -5, 2, -2, 0)
                                .duration(4000)
                                .andAnimate(bottom)
                                .interpolator(new DecelerateInterpolator())
                                .rotation(0, 10, -10, 5, -5, 2, -2, 0)
                                .trimPathOffset(0, 1.0f)
                                .startDelay(50)
                                .duration(4000)
                                .start();
                    }
                });
            }
        });*/
}
