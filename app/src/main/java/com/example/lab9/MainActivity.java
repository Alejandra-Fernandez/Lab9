package com.example.lab9;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    private final int REQUEST_VIDEO_CAPTURE = 1;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        findViewById(R.id.btnGrabarVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,REQUEST_VIDEO_CAPTURE);
                }
            }
        });

    }

    private void init(){
        this.videoView = findViewById(R.id.videoView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            if (videoUri != null) {
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        }
    }
}