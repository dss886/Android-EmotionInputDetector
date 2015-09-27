package com.dss886.emotionlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.dss886.emotioninputdetector.library.EmotionInputDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EmotionInputDetector.with(this)
                .setEmotionView(R.id.emotion_layout)
                .bindToEditText(R.id.edit_text)
                .bindToEmotionButton(R.id.emotion_button)
                .build();
    }
}
