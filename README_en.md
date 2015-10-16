# EmotionInputDetector

![JitPack](https://img.shields.io/github/release/dss886/Android-EmotionInputDetector.svg?label=JitPack)

[简体中文版说明 >>>](/README.md)

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
	compile 'com.github.dss886:Android-EmotionInputDetector:v0.1.2'
}
~~~

## Usage

1.Edit SoftInputMode in your Activity:

~~~xml
android:name=".MainActivity"
android:windowSoftInputMode="adjustPan"
~~~

2.Add an custom layout at the bottom of your activity layout:

~~~xml
<include
    layout="@layout/reply_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"/>
~~~

then custom your reply_layout.xml to show editText and emotion layout (e.g using a ViewPager)

3.Use EmotionInputDetector

~~~java
EmotionInputDetector.with(this)
		.setEmotionView(R.id.emotion_layout)
		.bindToEmotionButton(R.id.emotion_button)
		.bindToEditText(R.id.edit_text)
		.build();
~~~

There are 3 ResIds:

- emotion_layout: the layout showing emotions, which will be covered when soft keyboard shows.
- emotion_button: the button to switch emotion layout's visibility.
- edit_text: the EditText need to bind.

the relationship of these views:

![](/01.png)

![](/02.png)

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