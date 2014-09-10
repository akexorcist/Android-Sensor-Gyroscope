package app.akexorcist.sensor_gyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Main extends Activity {
	TextView textX, textY, textZ;
	SensorManager sensorManager;
	Sensor sensor;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		
		textX = (TextView) findViewById(R.id.textX);
		textY = (TextView) findViewById(R.id.textY);
		textZ = (TextView) findViewById(R.id.textZ);
	}
 
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(gyroListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(gyroListener);
	}

	public SensorEventListener gyroListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];

			textX.setText("X : " + (int)x + " rad/s");
			textY.setText("Y : " + (int)y + " rad/s");
			textZ.setText("Z : " + (int)z + " rad/s");
		}
	};
}