package com.lokiiichauhan.playmediademo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String url = "https://pagalsong.in/uploads/systemuploads/mp3/Ae%20Dil%20Hai%20Mushkil/Ae_Dil_Hai_Mushkil_Title_Song_320_Kbps.mp3";
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play_button);
        seekBar = findViewById(R.id.seekBarId);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                Toast.makeText(MainActivity.this, String.valueOf((duration / 1000) / 60),Toast.LENGTH_LONG).show();
            }
        });
        MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());

                playButton.setOnClickListener(view ->{
                    if (mp.isPlaying()){
                        mp.pause();
                        playButton.setText(R.string.play_text);
                    }else {
                        mp.start();
                        playButton.setText(R.string.pause_text);
                    }
                });
            }
        };

        mediaPlayer.setOnPreparedListener(preparedListener);
        mediaPlayer.prepareAsync();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}