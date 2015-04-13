package org.tohokutechdojo.firstlesson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startPictureSample();
    }

    private void startPictureSample() {
        Intent intent = new Intent(this, PictureActivity.class);
        startActivity(intent);
        finish();
    }

    private void startCompassSample() {
        Intent intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
        finish();
    }
}