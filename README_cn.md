# EmotionInputDetector

![JitPack](https://img.shields.io/github/release/dss886/Android-EmotionInputDetector.svg?label=JitPack)

[For English Version, Click Me >>>](/README_en.md)

一个用来开发表情输入键盘的依赖库，类似于微信，QQ和Telegram

![Demo](/01.gif)

## Download

在project的build.gradle中加入以下语句

~~~
allprojects {
	repositories {
		jcenter()
		maven { url "https://jitpack.io" }
	}
}
~~~

在module的build.gradle中加入以下语句

~~~
dependencies {
	compile 'com.github.dss886:Android-EmotionInputDetector:v0.2.0'
}
~~~

## Usage

1.保证整个布局文件根布局为LinearLayout，其中有高度可变化的组件（比如一个ListView或一个RelativeLayout），将其layout_height设为0，将layout_weight设为1

2.然后在布局文件的最下方中加入表情输入框的layout

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

3.然后新建一个reply_layout，其中的布局可完全自定义，如Sample项目使用了一个ViewPager

4.使用EmotionInputDetector

~~~java
EmotionInputDetector.with(this)
	    .setEmotionView(emotionView)
	    .bindToContent(contentView)
	    .bindToEditText(editText)
	    .bindToEmotionButton(emotionButton)
	    .build();
~~~

这里一共需要设置四个View：

- emotionView: 显示表情框的Layout
- contentView: 根布局中高度可变化的组件 (如Usage#1中的listview)
- editText: 需要绑定的EditText
- emotionButton: 点击显示/隐藏表情框的按钮

这些View的关系：

![](/01.png)

![](/02.png)

## Options

在Activity的onBackPressed函数中使用`EmotionInputDetector.interceptBackPress()`函数判断是否拦截返回键动作，可以得到更好的表现形式。

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