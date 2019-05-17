package com.example.brian.timerdemo;

import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    VideoView videoView;
    Button startButton;
    ImageView pepeImageView;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    boolean videoPlaying = false;

    public void buttonClicked(View view){

        if (counterIsActive) {

            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            countDownTimer.cancel();
            startButton.setText("Start");
            counterIsActive = false;
            pepeImageView.setAlpha(255);
            if (videoPlaying){
                videoView.stopPlayback();
                videoPlaying = false;
                videoView.setAlpha(0);
                pepeImageView.setAlpha(255);
                timerTextView.setAlpha(1);
            }

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("Stop");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    pepeImageView.setAlpha(0);
                    timerTextView.setAlpha(0);
                    startButton.setText("Reset");
                    videoView.setAlpha(1);
                    videoView.start();
                    videoPlaying = true;
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (secondString.length() == 1) {
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pepeImageView = findViewById(R.id.pepeImageView);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        videoView = (VideoView) findViewById(R.id.videoView);
        startButton = findViewById(R.id.startButton);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.jebaited);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(10);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
