package com.example.modernartui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;


public class ModernArt extends Activity {

    private static final int COLORSCALE = 0;
    private static final int GREYSCALE = 1;
    private static final int VIEWS_NUMBER = 5;
	private static final String TAG = "ModernArt-App";
	private static final String VIEW_BG_KEY = "viewBackGroundColor";
    
	private ArrayList<TextView> mViews;
	private int[] mViewsBgColors = new int[VIEWS_NUMBER];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        // if no saved instance available builds random backgrounds color 
        if (savedInstanceState == null) {
        	
        	for (int i = 0; i < (VIEWS_NUMBER); i++){
		        // Background colors array
		        mViewsBgColors[i] = this.getRandomColor(COLORSCALE);
		        
        	}
	        // randomly forces one of the text views background to gray scale
	        Random randomView = new Random();
	        mViewsBgColors[randomView.nextInt(VIEWS_NUMBER)] = this.getRandomColor(GREYSCALE);
        	
        }
        else {
        	// load saved colors
        	for (int i = 0; i < (VIEWS_NUMBER); i++){
        		
		        mViewsBgColors[i] = savedInstanceState.getInt(VIEW_BG_KEY + i);
		        
        	}

        }

        // Create views array list 
        mViews = new ArrayList<TextView>();

        // Load text views into array
        for (int i = 1; i < (VIEWS_NUMBER+1); i++){

        	// Views array list
            String twName = "Tw" + i;
            int twId = getResources().getIdentifier(twName, "id", getPackageName());
            if (twId != 0){
                mViews.add((TextView) this.findViewById(twId));
            }
                        
        }
        
        // Update text views background colors
        updateTextViewsBGcolor();


    }


	private void updateTextViewsBGcolor() {
		// set default color for text views
        for (TextView v : mViews) { v.setBackgroundColor(mViewsBgColors[mViews.indexOf(v)]); }
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Create a random color or a random grey scale tone
    private int getRandomColor(int colorMode){

        Random randomInt = new Random();
        int randomColor = 0;

        if (colorMode == COLORSCALE) {
            randomColor = Color.rgb(randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256));
        }
        else {
            randomColor = randomInt.nextInt(256);
            randomColor = Color.rgb(randomColor, randomColor, randomColor);
        }

        return randomColor;

    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.i(TAG, "Entered onSaveInstanceState");

        // save the current foreground feed
        for (int i = 0; i < (VIEWS_NUMBER); i++){
        	
        	savedInstanceState.putInt(VIEW_BG_KEY + i, mViewsBgColors[i]);         	
            Log.i(TAG, "Saved #" + i + " color " +  mViewsBgColors[i]);

        }
        
        // as recommended by android basics training, always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }


}
