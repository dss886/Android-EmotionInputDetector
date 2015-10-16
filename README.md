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
	compile 'com.github.dss886:Android-EmotionInputDetector:v0.1.2'
}
~~~

## Usage

1.对所在的Activity配置SoftInputMode：

~~~xml
android:name=".MainActivity"
android:windowSoftInputMode="adjustPan"
~~~

2.在布局文件的最下方中加入表情输入框的layout：

~~~xml
<include
    layout="@layout/reply_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"/>
~~~

然后新建一个reply_layout，其中的布局可完全自定义，比如使用ViewPager

3.使用EmotionInputDetector

~~~java
EmotionInputDetector.with(this)
		.setEmotionView(R.id.emotion_layout)
		.bindToEmotionButton(R.id.emotion_button)
		.bindToEditText(R.id.edit_text)
		.build();
~~~

一共需要三个ResId：

- emotion_layout: 显示表情输入框的Layout，正常输入时会被软键盘覆盖的部分
- emotion_button: 点击显示/隐藏表情输入框的按钮
- edit_text: 需要绑定的EditText

这些View的关系：

![](/01.png)

![](/02.png)

## A Small Bug

在第一次进入该页面时，显示表情框高度可能会不正常。

（这是因为需要根据软键盘的高度动态改变表情框的显示高度，并使用了SharedPreference来保存该值，第一次进入页面该高度不存在就会导致显示错误）

在进入表情框页面前在一个有软键盘弹出的界面（比如说，登陆界面）调用下面的方法探测一次其高度可以防止这个问题发生

~~~java
EmotionInputDetector.with(this)
		.detectorSoftInputHeight(editText);
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