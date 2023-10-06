package in.basulabs.shakealarmclock.dialogue;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Selector extends Node implements SensorEventListener {
    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float brightness;


    public Selector(String nodeName, String nodeText, int speechUri, Context context) {
        super(nodeName, nodeText, speechUri);

        // setting up sensors
        sensorManager = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
        assert sensorManager != null;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


    private String brightness(Float brightness) {
        if (Math.round(brightness) == 0) {
            return "ptich dark";
        } else if (Math.round(brightness) < 10) {
            return "darkness";
        } else if (Math.round(brightness) < 50) {
            return "gray";
        } else if (Math.round(brightness) < 5000) {
            return "normal";
        } else if (Math.round(brightness) < 25000) {
            return "sunlight";
        }
        return "ptich dark";
    }

    protected void onPause() {
        onPause();
        // unregister the sensor listener
        sensorManager.unregisterListener(this, sensor);
    }
    protected void onResume() {
        onResume();
        // register the sensor listener
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // detect light sensor change
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            // light value
            brightness = sensorEvent.values[0];
        }
        return;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        return;
    }

    public int getBrightness() {
        return Math.round(brightness);
    }
}
