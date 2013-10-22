/**
 * 
 */
package edu.cmu.sv.lifelogger.database;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Ming
 *
 */
public class BroadcastService extends Service {
	
	private static final String TAG = "BroadcastService";
	public static final String BROADCAST_ACTION = "edu.cmu.sv.lifelogger.database.BroadcastService";
	private final Handler handler = new Handler();
	Intent intent;
	int counter = 0;

	@Override
	public void onCreate() {
		
		super.onCreate();

    	intent = new Intent(BROADCAST_ACTION);	
	}
	
    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 10); // 10 second
   
    }

    private Runnable sendUpdatesToUI = new Runnable() {
    	public void run() {
    		DisplayLoggingInfo();    		
    	    handler.postDelayed(this, 10000); // 10 seconds
    	}
    };    
    
    private void DisplayLoggingInfo() {

    	DataGenerator c = new DataGenerator();
    	
    	String strTimeline = c.TimelineGenerator();

    	if( counter > 600 ){
    		counter = 0;
    	}
    	
    	intent.putExtra("DeviceID", "DeviceID" + String.valueOf(++counter));
    	intent.putExtra("Timeline", strTimeline);
    	 	
    	sendBroadcast(intent);
    	
 //   	Log.d(TAG, "DeviceID" + String.valueOf(++counter) + ";" + strTimeline);
    }
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {		
        handler.removeCallbacks(sendUpdatesToUI);		
		super.onDestroy();
	}

}
