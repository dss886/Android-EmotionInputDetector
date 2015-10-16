package com.dss886.emotionlayout;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import com.dss886.emotioninputdetector.library.EmotionInputDetector;
import com.dss886.emotionlayout.SlidingTab.SlidingTabLayout;
import com.dss886.emotionlayout.viewpager.EmotionAdapter;
import com.dss886.emotionlayout.viewpager.GlobalOnItemClickManager;

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

        setUpEmotionViewPager();
        Log.d("SoftInputMethod", String.valueOf(getWindow().getAttributes().softInputMode));
    }

    private void setUpEmotionViewPager() {
        final String[] titles = new String[]{"经典", "悠嘻猴", "兔斯基", "洋葱头"};
        EmotionAdapter mViewPagerAdapter = new EmotionAdapter(getSupportFragmentManager(), titles);
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(1);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.widget_tab_indicator, R.id.text);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorPrimary));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);

        GlobalOnItemClickManager globalOnItemClickListener = GlobalOnItemClickManager.getInstance();
        globalOnItemClickListener.attachToEditText((EditText)findViewById(R.id.edit_text));
    }
}
