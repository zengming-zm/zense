/**
 * 
 */
package edu.cmu.sv.lifelogger.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.cmu.sv.lifelogger.TimelineTestActivity;
import edu.cmu.sv.lifelogger.entities.TimelineItem;
import edu.cmu.sv.lifelogger.entities.TimelineSegment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Ming
 *
 */
public class DataReceiver extends BroadcastReceiver {
	
	private static final String TAG = "DataReceiver";
	
	private Intent intent;
	
	public ArrayList<TimelineSegment> tsResult = new ArrayList<TimelineSegment>();
	private ArrayList<TimelineItem> data0 = new ArrayList<TimelineItem>();
	public int numline;
	
	String filename = "";
	

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		filename = getSDPath() +"/" + "timeline.dat";
		
		String strCounter = intent.getStringExtra("DeviceID");
		String strTimeline = intent.getStringExtra("Timeline");
		
		Write2File(strTimeline);
/*		
		Intent intent2open = new Intent(context, TimelineTestActivity.class);
		intent2open.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent2open.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent2open.putExtra("DeviceID", strCounter);
		intent2open.putExtra("Timeline", strTimeline);
		context.startActivity(intent2open);
*/
		
//		updateUI(intent);
		
	}

	private void Write2File(String strline){

		File file = new File(filename);
		if( !file.exists() ){
			try{
				file.createNewFile();
				Log.d(TAG, "succed: " + String.valueOf(file.exists()));
			}catch (IOException e){
				Log.d(TAG, e.toString());
				e.printStackTrace();
			}
		}
		
		BufferedWriter out = null;
		try {  
            out = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(filename, true)));  
            out.write(strline);
            out.write("\n");
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }		
	}

	public String getSDPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()  
                        .equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {                              
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();
	} 
	


}
