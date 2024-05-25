package com.rinekri.util;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Date;

public class ShakeEventListener implements SensorEventListener {
    public static final String TAG ="ShakeEventListener";

    private static final float SHAKE_THRESHOLD_GRAVITY = 1.7F;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    private static final int SHAKE_TIME_MS = 5000;

    private OnShakeListener mListener;
    private static long mShakeTimestamp;
    private static int mShakeCount = 0;

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {

                final long now = System.currentTimeMillis();
                Date date = new Date(now);
//                Log.d(TAG,"Now time: "+date.toString());

                // Reset the shake count after X seconds of no shakes
                if ((mShakeCount > 0) && (now > mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS + SHAKE_TIME_MS)) {
                    mShakeCount = 0;
                }

                // We should shake X seconds to do something
                if ((mShakeCount > 0) && (now > mShakeTimestamp + SHAKE_TIME_MS)) {
                    mShakeCount++;
                    mShakeTimestamp = now;
                    mListener.onShake(mShakeCount);
                    Date shakeDate = new Date(mShakeTimestamp-1);
//                    Log.d(TAG,"Shake time: "+shakeDate.toString());
//                    Log.d(TAG, "Shake: " +(mShakeCount-1));
                }

               if (mShakeCount == 0) {
                   mShakeCount = 1;
                   mShakeTimestamp = now;
               }
            }
        }
    }
}