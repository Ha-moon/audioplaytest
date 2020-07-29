package com.example.audioplaytest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr"; //웹서버에 있는 파일 가져오기

    MediaPlayer player;
    int position = 0;//현재 재생한 위치


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(); //아래선언
            }
        });

        Button button2 =(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });

        Button button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });

        Button button4 =(Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

    }


    public void playAudio() {
        try{
            closePlayer(); //항상 처음시작에 죽임

            player = new MediaPlayer();
            player.setDataSource(url); //인터넷에서 가져와서 플레이
            player.prepare();
            player.start();

            Toast.makeText(this,"재생 시작됨.", Toast.LENGTH_LONG).show(); //메세지띄움
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pauseAudio() { //중지
        if (player != null){
            position = player.getCurrentPosition();
            player.pause();

            Toast.makeText(this,"일시정지됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void  resumeAudio(){ //재시작
        if(player != null && !player.isPlaying()) {
            player.seekTo(position); //play가 시작되는 시점
            player.start();

            Toast.makeText(this,"재시작됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void  stopAudio(){ //재시작
        if(player != null && player.isPlaying()) {
            player.stop();

            Toast.makeText(this,"정지됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void closePlayer(){ //항상 처음시작에 죽임
        if(player != null){
            player.release();
            player = null;
        }
    }
}