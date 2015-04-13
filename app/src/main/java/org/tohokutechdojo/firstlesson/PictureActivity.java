package org.tohokutechdojo.firstlesson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class PictureActivity extends ActionBarActivity {

    private ImageView imageView;
    private int touchCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        imageView = (ImageView) findViewById(R.id.imageView);
        //初期値の画像を入れる
        imageView.setImageResource(R.drawable.sheep_1);

    }

    public void onTouchLayout(View v) {
        if (touchCount == 0){
            //1種類目の画像を入れる
            imageView.setImageResource(R.drawable.sheep_2);
            touchCount++;
        }else {
            //2種類目の画像を入れる
            imageView.setImageResource(R.drawable.sheep_1);
            touchCount = 0;
        }
    }

}
