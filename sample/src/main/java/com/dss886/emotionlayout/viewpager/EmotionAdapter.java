package com.dss886.emotionlayout.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by dss886 on 15/9/22.
 */
public class EmotionAdapter extends FragmentStatePagerAdapter {
    private SparseArray<Fragment> mPages;

    private String[] mTitles;

    public EmotionAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        mPages = new SparseArray<Fragment>();
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position) {
            case 0:
                f = new ClassicFragment();
                break;
            case 1:
                f = new XiHaHouFragment();
                break;
            case 2:
                f = new TuzkiFragment();
                break;
            case 3:
                f = new YangCongTouFragment();
                break;
        }
        mPages.put(position, f);
        return f;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (0 <= mPages.indexOfKey(position)) {
            mPages.remove(position);
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
