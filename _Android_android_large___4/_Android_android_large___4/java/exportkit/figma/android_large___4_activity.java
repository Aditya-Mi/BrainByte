
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		android_large___4
	 *	@date 		Thursday 16th of March 2023 07:57:18 PM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	

package exportkit.figma;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class android_large___4_activity extends Activity {

	
	private View __bg__android_large___4;
	private ImageView image_2;
	private TextView get_yourself_secured_;
	private TextView be_safe_;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_large___4);

		
		__bg__android_large___4 = (View) findViewById(R.id.__bg__android_large___4);
		image_2 = (ImageView) findViewById(R.id.image_2);
		get_yourself_secured_ = (TextView) findViewById(R.id.get_yourself_secured_);
		be_safe_ = (TextView) findViewById(R.id.be_safe_);
	
		
		__bg__android_large___4.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				
				Intent nextScreen = new Intent(getApplicationContext(), android_large___5_activity.class);
				startActivity(nextScreen);
			
		
			}
		});
		
		
		//custom code goes here
	
	}
}
	
	