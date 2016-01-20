# TohokuTechDojo ImageChangeSample
東北Tech道場 石巻道場の講義用に作成をした、画像切り替えのサンプルアプリです。  
  
![Capture](https://raw.githubusercontent.com/TomiGie/TohokuTechDojoSeason8Picture/master/captures/MainActivity.png "Capture")
  

## Getting Started
このアプリは画面の画像をタップすると、画像が切り替わるというシンプルな機能が実装してあります。  
画像は複数セット準備してあり、変更が可能です。  
  
以下に変更部分とその手順を記載します。


### 画像の変更
このプログラムではImageViewに画像をセットしており、画像をタップすると以下のonTouchLayoutが呼び出されます。  
```java:MainActivity.java
// ---
// MainActivity.java class
// ---
public void onTouchLayout(View v) {
    if (touchCount == 0){
        // 1種類目の画像を設定
        imageView.setImageResource(R.drawable.sheep_2);

        // touchCountを1にする
        touchCount = 1;
    }
    else {
        // 2種類目の画像を設定
        imageView.setImageResource(R.drawable.sheep_1);

        // touchCountを0にする
        touchCount = 0;
    }
}
```
画像のセットはdrawableフォルダに格納されており、sheepの画像の他にもcarrotやojisanといった画像も用意しています。  
デフォルトではsheepがセットされていますので、講義をする際はハンズオン形式でコードの解説を行いながらリソース画像の変更を行うことをお勧めします。  
  
#License

Copyright 2014 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
