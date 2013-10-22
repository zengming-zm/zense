/**
 * 
 */
package edu.cmu.sv.lifelogger.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.util.Log;


/**
 * @author Ming
 *
 */
public class DataGenerator {
	
public List<String> ActivityList = new ArrayList<String>();;
	
	public List<String> LocationList = new ArrayList<String>();;
	
	public DataGenerator(){
		
		ActivityList.add("Driving");
		ActivityList.add("Working");
		ActivityList.add("Dining");
		ActivityList.add("Walking");
		ActivityList.add("Work Meeting");
		ActivityList.add("Shopping");
		ActivityList.add("Sleeping");
		
		LocationList.add("Berkeley");
		LocationList.add("Brentwood");
		LocationList.add("Cupertino");
		LocationList.add("East Palo Alto");
		LocationList.add("Foster City");
		LocationList.add("Hillsborough");
		LocationList.add("Los Altos");
		LocationList.add("Los Gatos");
		LocationList.add("Mill Valley");
		LocationList.add("Mountain View");
		LocationList.add("Oakland");
		LocationList.add("Pittsburg");
		LocationList.add("Santa Clara");
		LocationList.add("San Mateo");
		LocationList.add("San Jose");
		LocationList.add("San Francisco");
		LocationList.add("Sonoma");
		LocationList.add("Suisun City");
		LocationList.add("Vacaville");
		LocationList.add("Yountville");
		LocationList.add("Palo Alto");
	}
	
	String TimelineGenerator(){	

		
		String Timeline = "";
		int num_activity = ActivityList.size();
		int num_location = LocationList.size();
		
//		Log.d("DataGenerator", "num_act:" + String.valueOf(num_activity) 
//				+ " num_loc:" + String.valueOf(num_location));
		
		Random random = new Random();
		int idx_activity = Math.abs(random.nextInt()) % num_activity;
		int idx_startlocation = Math.abs(random.nextInt()) % num_location;
		int idx_endlocation = Math.abs(random.nextInt()) % num_location;
		
		String startTime = "";
		String endTime = "";
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss aa");
		Calendar c=Calendar.getInstance();
		endTime = sdf.format(c.getTime());
		c.add(Calendar.SECOND, -10);
		startTime = sdf.format(c.getTime());
		
		Timeline = ActivityList.get(idx_activity) + ";" + startTime + ";" + endTime 
				+ ";" + LocationList.get(idx_startlocation) + ";" + LocationList.get(idx_endlocation);
		
		return Timeline;
	}	

}
