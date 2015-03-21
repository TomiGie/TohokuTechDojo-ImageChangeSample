package org.tohokutechdojo.firstlesson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private ImageView imageView;
    private SensorManager sensorManager;
    private float[] magneticValues = new float[3];
    private float[] accelerometerValues = new float[3];
    private int[] images = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11};
    private float[] angle = {0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330, 360};
    private int currentIdx = 0;
    private boolean HOUSE_IMAGE = false;

    //イトナブの緯度経度
    double latitude = 38.43154510598532;
    double longitude = 141.3095775246578;

    //自分の家の緯度経度
    double myHouseLatitude = 38.42315487810955;
    double myHouseLongitude = 141.39446407556113;

    private int myHouseOrientation;
    private int space = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        imageView = (ImageView) findViewById(R.id.imageView);

        myHouseOrientation = getDirection(myHouseLatitude, myHouseLongitude, latitude, longitude);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {

            case Sensor.TYPE_ACCELEROMETER:
                magneticValues = event.values.clone();
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                accelerometerValues = event.values.clone();
                break;
        }

        if (magneticValues != null && accelerometerValues != null) {

            float[] orientation = new float[3];
            float R[] = new float[16];
            float I[] = new float[16];

            SensorManager.getRotationMatrix(R, I, accelerometerValues, magneticValues);
            SensorManager.getOrientation(R, orientation);

            float angle = (float) Math.floor(Math.toDegrees(orientation[0]));

            if (angle >= 0) {
                orientation[0] = angle;
            } else if (angle < 0) {
                orientation[0] = 360 + angle;
            }

            setImage(orientation[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void setImage(float value) {

        int length = angle.length;

        if (value >= myHouseOrientation - space && value <= myHouseOrientation + space) {
            if (!HOUSE_IMAGE) {
                imageView.setImageResource(R.drawable.house);
                HOUSE_IMAGE = true;
                return;
            } else {
                HOUSE_IMAGE = false;
            }
        }

        for (int i = 0; i < length; i++) {
            if (value >= angle[i] && value <= angle[i + 1]) {
                if (currentIdx != i) {
                    imageView.setImageResource(images[i]);
                }
                currentIdx = i;
                break;
            }
        }


    }

    private int getDirection(double latitude1, double longitude1, double latitude2, double longitude2) {
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        double Y = Math.sin(lng2 - lng1) * Math.cos(lat2);
        double X = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1);
        double deg = Math.toDegrees(Math.atan2(Y, X));
        double angle = (deg + 360) % 360;
        return (int) (Math.abs(angle) + (1 / 7200));
    }
}