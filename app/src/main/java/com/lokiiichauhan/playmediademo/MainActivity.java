package com.lokiiichauhan.playmediademo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String url = "https://pagalsong.in/uploads/systemuploads/mp3/Ae%20Dil%20Hai%20Mushkil/Ae_Dil_Hai_Mushkil_Title_Song_320_Kbps.mp3";
    private MediaPlayer mediaPlayer;
    private Button playButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play_button);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
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