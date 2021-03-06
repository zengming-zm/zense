package edu.cmu.sv.lifelogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.cmu.sv.lifelogger.database.BroadcastService;
import edu.cmu.sv.lifelogger.database.TimelineManager;
import edu.cmu.sv.lifelogger.entities.TimelineItem;
import edu.cmu.sv.lifelogger.entities.TimelineSegment;
import edu.cmu.sv.lifelogger.helpers.TimelineItemHelper;
import edu.cmu.sv.lifelogger.helpers.TimelineSegmentHeader;
import edu.cmu.sv.mobisens_ui.R;



public class TimelineTestActivity extends Activity{
	private static final String TAG = "TimelineTestActivity";
	
	private static LinearLayout MY_MAIN_LAYOUT;
	private ArrayList<TimelineSegment> timelineItemList;
	Context cxt;
	
	String strDeviceID;
	String strTimeline;

/*
	private Intent intent;
	public  ArrayList<TimelineSegment> tsResult = new ArrayList<TimelineSegment>();
	private  ArrayList<TimelineItem> data0 = new ArrayList<TimelineItem>();
	private  int numline = 0;
*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		cxt=this;
		
		setContentView(R.layout.main_vert_lin_layout);
		MY_MAIN_LAYOUT = (LinearLayout) findViewById(R.id.mainLayout);

		timelineItemList = TimelineManager.getAllTimelineItems();
		

		for (TimelineSegment tls: timelineItemList){
			//add timeline segment
			TimelineSegmentHeader tsh = new TimelineSegmentHeader(this, tls.getDate().toString(), MY_MAIN_LAYOUT);

			ArrayList<TimelineItem> tlItems= tls.getData();
			for (TimelineItem item: tlItems){
				//add its items
				TimelineItemHelper tmh = new TimelineItemHelper(this, item, MY_MAIN_LAYOUT, itemListener);

			}

		}
		//		TextViewHelper ctvp = new TextViewHelper(this, "BLAHBLAH", MY_MAIN_LAYOUT);


	}
	


	View.OnClickListener itemListener = new View.OnClickListener() {
		public void onClick(View view) {
			// custom dialog
			final Dialog dialog = new Dialog(cxt);
			dialog.setContentView(R.layout.timeline_dialog);
			dialog.setTitle("Please annotate activity");

			// set the custom dialog components - text, image and button
//			TextView text = (TextView) dialog.findViewById(R.id.text);
//			text.setText("Android custom dialog example!");
//			ImageView image = (ImageView) dialog.findViewById(R.id.image);
//			image.setImageResource(R.drawable.ic_launcher);

			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
		}

		
	};
}
