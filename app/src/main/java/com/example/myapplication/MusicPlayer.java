package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MusicPlayer extends AppCompatActivity {


    MediaPlayer mPlayer;
    Button playButton, pauseButton, stopButton;
    SeekBar volumeControl;
    AudioManager audioManager;
    TextView ispol, song;
    ImageView albom;

    //ArrayList<Music> musics = new ArrayList<Music>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        ispol = (TextView) findViewById(R.id.ispolname);
        song = (TextView) findViewById(R.id.songname);
        albom = (ImageView) findViewById(R.id.imageView);

        String txtispol = getIntent().getStringExtra("isolnit");
        String txtsong = getIntent().getStringExtra("song");

        ispol.setText(txtispol);
        song.setText(txtsong);

        txtispol = ispol.getText().toString();
        txtsong = song.getText().toString();

        switch (ispol.getText().toString()) {
            case "alena":
                albom.setImageResource(R.drawable.alena);
                mPlayer= MediaPlayer.create(this, R.raw.alena);
                break;
            case "bts":
                albom.setImageResource(R.drawable.bts);
                mPlayer= MediaPlayer.create(this, R.raw.bts);
                break;
            case "etoegr":
                albom.setImageResource(R.drawable.etoegr);
                mPlayer= MediaPlayer.create(this, R.raw.egor);
                break;
            case "grechka":
                albom.setImageResource(R.drawable.grechka);
                mPlayer= MediaPlayer.create(this, R.raw.grec);
                break;
            case "macs":
                albom.setImageResource(R.drawable.macs);
                mPlayer= MediaPlayer.create(this, R.raw.maks);
                break;
        }


        //albom.setImageResource(R.drawable.ateez);
        // mPlayer= MediaPlayer.create(this, R.raw.ateez);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeControl = findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }
    private void stopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            playButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void play(View view){

        mPlayer.start();
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void pause(View view){

        mPlayer.pause();
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void stop(View view){
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }
}
