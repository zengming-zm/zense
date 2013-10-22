package edu.cmu.sv.lifelogger.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import edu.cmu.sv.lifelogger.entities.TimelineItem;
import edu.cmu.sv.lifelogger.entities.TimelineSegment;

public class TimelineManager{

	private static final String TAG = "TimelineManager";
	private Intent intent;
	
	public static final String BROADCAST_ACTION = "edu.cmu.sv.lifelogger.database.BroadcastService";
	
	private static ArrayList<TimelineSegment> tsResult;
//	private static ArrayList<TimelineItem> data0;
	public static int numline = 0;
	
	public static ArrayList<TimelineSegment> timelineItemList;
	
	private static String filename = "";
	
	private static void ReadfromFile(){

		tsResult = new ArrayList<TimelineSegment>();
		filename = getSDPath() +"/" + "timeline.dat";
		int numtextline = 0;
		Log.d(TAG, "read file: " + filename);
		
		ArrayList<String> itemlist = new ArrayList<String>();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filename)); 
			String line = null;
			while( (line = br.readLine()) != null){
				numtextline++;
				if (numtextline % 3 == 0){
					updateList(itemlist, false);
					itemlist.clear();
					itemlist.add(line);
				}
				else{
					itemlist.add(line);
				}
			}

		}catch(IOException e){
			Log.d(TAG, e.toString());	
		}	
	}
	
    public static void updateList(ArrayList<String> itemlist, boolean flag) {
    	
    	ArrayList<TimelineItem> data0 = new ArrayList<TimelineItem>();
    	for (int i = 0; i < itemlist.size(); i++){
    		String timeline = itemlist.get(i);
    		String[] token = timeline.split(";");
	    	TimelineItem timelineitem = new TimelineItem(token[0], token[1], token[2], token[3], token[4]);
//	    	Log.d(TAG, "timeline item: " + token[0] + "," + token[1] + "," + token[2] + "," + token[3] + "," + token[4]);
	    	data0.add(timelineitem); 
    	}
		
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
		Date today = new Date();
		String todayStr = dateFormat.format(today);
		
		TimelineSegment ts1 = new TimelineSegment(data0, today);

		
		tsResult.add(ts1);
    }

    
	//TODO: Change this to get real data
	public static ArrayList<TimelineSegment> getAllTimelineItems() {

		ArrayList<TimelineSegment> ts = getRealData();//createDummyData();

		return ts;

	}

	private static ArrayList<TimelineSegment> getRealData(){
		
		ReadfromFile();

		return tsResult;
	}
	
	
	private static ArrayList<TimelineSegment> createDummyData() {
		
		DataReceiver receiver = new DataReceiver();
		Log.d(TAG, "numline: " + String.valueOf(receiver.numline));
		
		ArrayList<TimelineSegment> ts = new ArrayList<TimelineSegment>();
		
		//Segment 1
		ArrayList<TimelineItem> data1 = new ArrayList<TimelineItem>();

		TimelineItem t1 = new TimelineItem("Driving", "9:00 AM", "9:30 AM", "Santa Clara", "Palo Alto");     
		TimelineItem t2 = new TimelineItem("Working", "9:30 AM", "11:30 AM", "University Ave, Palo Alto", "University Ave, Palo Alto");
		TimelineItem t3 = new TimelineItem("Dining", "12:00 PM", "1:00 PM", "Starbucks, Palo Alto", "Starbucks, Palo Alto");
		TimelineItem t4 = new TimelineItem("Walking", "1:00 PM", "1:30 PM", "University Ave, Palo Alto", "University Ave, Palo Alto");
		TimelineItem t5 = new TimelineItem("Work Meeting", "9:30 PM", "11:30 PM", "Moffett Field, Mountain View", "Moffett Field, Mountain View");

		data1.add(t1); 
		data1.add(t2);
		data1.add(t3);
		data1.add(t4);
		data1.add(t5);
		
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
		Date today = new Date();
		String todayStr = dateFormat.format(today);
		
		TimelineSegment ts1 = new TimelineSegment(data1, today);
		

		//segment 2   
		ArrayList<TimelineItem> data2 = new ArrayList<TimelineItem>();
		
		data2.add(t1);
		data2.add(t4);
		data2.add(t5);
		
		TimelineSegment ts2 = new TimelineSegment(data2, today);

		
		//segment 3
		ArrayList<TimelineItem> data3 = new ArrayList<TimelineItem>();
		data3.add(t2);
		data3.add(t3);
		data3.add(t4);
		TimelineSegment ts3 = new TimelineSegment(data3, today);

		
		//add all segments
		ts.add(ts1);
		ts.add(ts2);
		ts.add(ts3);
		
		return ts;

	}
	
	public static String getSDPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()  
                        .equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {                              
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();
	} 



}
