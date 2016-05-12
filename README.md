# EmotionInputDetector

![JitPack](https://img.shields.io/github/release/dss886/Android-EmotionInputDetector.svg?label=JitPack)

[简体中文版说明 >>>](/README_cn.md)

A Library to develop emotion-input layout like WeChat, Telegram, etc.

![Demo](/01.gif)

## Download

Add it in your build.gradle at the end of repositories:

~~~
allprojects {
	repositories {
		jcenter()
		maven { url "https://jitpack.io" }
	}
}
~~~

Add the dependency in the form:

~~~
dependencies {
	compile 'com.github.dss886:Android-EmotionInputDetector:v0.2.0'
}
~~~

## Usage

1.Make sure the root layout of your activity is a LinearLayout, and there are a view/layout of variable height in it (like a ListView or a RelativeLayout). Set the height-variable view/layout's layout_height as 0 and the layout_weight as 1

2.Add your custom emotion-input layout at the bottom of your activity layout:

~~~xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        />

    <include
		layout="@layout/reply_layout"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"/>

</LinearLayout>
~~~

3.then custom your reply_layout.xml to show editText and emotion layout (e.g using a ViewPager as the Sample module)

4.Use EmotionInputDetector

~~~java
EmotionInputDetector.with(this)
	    .setEmotionView(emotionView)
	    .bindToContent(contentView)
	    .bindToEditText(editText)
	    .bindToEmotionButton(emotionButton)
	    .build();
~~~

There are 4 views:

- emotionView: the layout to show clickable emotions.
- contentView: the height-variable view/layout in the root layout.
- editText: the EditText need to bind.
- emotionButton: the button to switch emotion layout's visibility.

the relationship of these views:

![](/01.png)

![](/02.png)

## Options

For better performance, using `EmotionInputDetector.interceptBackPress()` method in the override method `onBackPressed()` of your activity, to determine whether the back pressed action should be intercepted.

~~~java
@Override
public void onBackPressed() {
    if (!mDetector.interceptBackPress()) {
        super.onBackPressed();
    }
}
~~~

## License

~~~
Copyright 2015 dss886

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~