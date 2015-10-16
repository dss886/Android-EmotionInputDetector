package com.dss886.emotioninputdetector.library;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by dss886 on 15/9/26.
 */
public class EmotionInputDetector {

    private static final String SHARE_PREFERENCE_NAME = "com.dss886.emotioninputdetector";
    private static final String SHARE_PREFERENCE_TAG = "soft_input_height";

    private InputMethodManager mInputManager;
    private Activity mContext;
    private SharedPreferences sp;
    private EditText mEditText;
    private View mEmotionLayout;

    private int lastSoftInputHeight = 0;
    private boolean notHideEmojiLayout = false;

    private EmotionInputDetector() {}

    public static EmotionInputDetector with(Activity activity) {
        EmotionInputDetector emotionInputDetector = new EmotionInputDetector();
        emotionInputDetector.mContext = activity;
        emotionInputDetector.mInputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        emotionInputDetector.sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return emotionInputDetector;
    }

    public void detectorSoftInputHeight(final EditText editText) {
        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int softInputHeight = getSupportSoftInputHeight();
                if (softInputHeight != lastSoftInputHeight) {
                    if (softInputHeight >= 0) {
                        lastSoftInputHeight = softInputHeight;
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
                        sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
                    }
                }
                lastSoftInputHeight = 0;
            }
        });
    }

    public EmotionInputDetector bindToEditText(int editTextResId) {
        mEditText = (EditText) mContext.findViewById(editTextResId);
        mEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int softInputHeight = getSupportSoftInputHeight();
                if (softInputHeight != lastSoftInputHeight) {
                    if (softInputHeight <= 0) {
                        lastSoftInputHeight = softInputHeight;
                        if (!notHideEmojiLayout) {
                            mEmotionLayout.setVisibility(View.GONE);
                        } else {
                            notHideEmojiLayout = false;
                        }
                    } else {
                        lastSoftInputHeight = softInputHeight;
                        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mEmotionLayout.getLayoutParams();
                        linearParams.height = softInputHeight;
                        mEmotionLayout.setVisibility(View.INVISIBLE);
                        if (linearParams.height == softInputHeight) {
                            mEmotionLayout.setVisibility(View.INVISIBLE);
                        } else {
                            linearParams.height = softInputHeight;
                        }

                        sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
                    }
                }
            }
        });

        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && lastSoftInputHeight <= 0) {
                    mEmotionLayout.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        mEditText.requestFocus();
        return this;
    }

    public EmotionInputDetector bindToEmotionButton(int emotionButtonResId) {
        View mEmotionButton = mContext.findViewById(emotionButtonResId);
        mEmotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastSoftInputHeight <= 0) {
                    if (mEmotionLayout.getVisibility() == View.VISIBLE ||
                            mEmotionLayout.getVisibility() == View.INVISIBLE) {
                        showSoftInput();
                    } else {
                        mEmotionLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    notHideEmojiLayout = true;
                    mEmotionLayout.setVisibility(View.VISIBLE);
                    hideSoftInput();
                }
            }
        });
        return this;
    }

    public EmotionInputDetector setEmotionView(int emotionLayoutResId) {
        mEmotionLayout = mContext.findViewById(emotionLayoutResId);
        return this;
    }

    public void build(){
        int height = sp.getInt(SHARE_PREFERENCE_TAG, -1);
        if (height != -1) {
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mEmotionLayout.getLayoutParams();
            linearParams.height = height;
        }
    }

    private int getSupportSoftInputHeight() {
        Rect r = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight = mContext.getWindow().getDecorView().getRootView().getHeight();
        int softInputHeight = screenHeight - r.bottom;
        if (Build.VERSION.SDK_INT >= 18) {
            // When SDK Level >= 18, the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
        }
        return softInputHeight;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        mContext.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    private void showSoftInput() {
        mInputManager.showSoftInput(mEditText, 0);
    }

    private void hideSoftInput() {
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
