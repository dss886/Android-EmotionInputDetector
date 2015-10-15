# EmotionInputDetector

一个用来开发表情输入键盘的依赖库，类似于微信，QQ和Telegram

// todo : add a gif demo

## Download

// todo : add gradle method

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

3.使用组件库

~~~java
EmotionInputDetector.with(this)
		.setEmotionView(R.id.emotion_layout)
		.bindToEmotionButton(R.id.emotion_button)
		.bindToEditText(R.id.edit_text)
		.build();
~~~

一共需要三个ID：

- emotion_layout: 显示表情输入框的Layout，正常输入时会被软键盘覆盖的部分
- emotion_button: 点击显示/隐藏表情输入框的按钮
- edit_text: 需要绑定的EditText

这些View的关系：

![](/01.png)

![](/02.png)

## License

~~~
Copyright 2013 Square, Inc.

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