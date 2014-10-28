package com.example.modernartui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class ModernArt extends Activity {

    private static final int COLORSCALE = 0;
    private static final int GREYSCALE = 1;
    private static final int VIEWS_NUMBER = 5;
	private static final String TAG = "ModernArt-App";
	private static final String VIEW_ACTUAL_BG_KEY = "viewActualBackGroundColor";
	private static final String VIEW_BASE_BG_KEY = "viewBaseBackGroundColor";
    
	private ArrayList<TextView> mViews;
	private int[] mViewsActualBgColors = new int[VIEWS_NUMBER];
	private int[] mViewsBaseBgColors = new int[VIEWS_NUMBER];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        // if no saved instance available builds random backgrounds color 
        if (savedInstanceState == null) {
        	
        	for (int i = 0; i < (VIEWS_NUMBER); i++){
		        // Background colors array
		        mViewsActualBgColors[i] = this.getRandomColor(COLORSCALE);
		        mViewsBaseBgColors[i] = this.getRandomColor(COLORSCALE);
		        
        	}
	        // randomly forces one of the text views background to gray scale
	        Random randomView = new Random();
	        mViewsActualBgColors[randomView.nextInt(VIEWS_NUMBER)] = this.getRandomColor(GREYSCALE);
        	
        }
        else {
        	// load saved colors
        	for (int i = 0; i < (VIEWS_NUMBER); i++){
        		
		        mViewsActualBgColors[i] = savedInstanceState.getInt(VIEW_ACTUAL_BG_KEY + i);
		        
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
        
        // Setup seekbar listener
        SeekBar mSlider = (SeekBar) findViewById(R.id.seekBar1);
        mSlider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                return;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            	return;
            }

            /**
            * seekBar   The SeekBar whose progress has changed
            * progress  The current progress level. This will be in the range 0..max where max was set by setMax(int). (The default value for max is 100.)
            * fromUser  True if the progress change was initiated by the user.
            */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
            	calcBGcolors(progress);
                updateTextViewsBGcolor();
            }
        });


    }


	private void updateTextViewsBGcolor() {
		// set default color for text views
        for (TextView v : mViews) { v.setBackgroundColor(mViewsActualBgColors[mViews.indexOf(v)]); }
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
    
    
    private void calcBGcolors(int progress){
    	
        for (int i = 0; i < (VIEWS_NUMBER); i++){
        	
            int baseRed = Color.red(mViewsBaseBgColors[i]);
            int baseGreen = Color.green(mViewsBaseBgColors[i]);
            int baseBlue = Color.blue(mViewsBaseBgColors[i]);
            int negRed = 255 - baseRed;
            int negGreen = 255 - baseGreen;
            int negBlue = 255 - baseBlue;
            int distRed = Math.max(baseRed, negRed) - Math.min(baseRed, negRed);
            int distGreen = Math.max(baseGreen, negGreen) - Math.min(baseGreen, negGreen);
            int distBlue = Math.max(baseBlue, negBlue) - Math.min(baseBlue, negBlue);
            int calcRed = Math.min(baseRed, negRed) + distRed * progress / 100;
            int calcGreen = Math.min(baseGreen, negGreen) + distGreen * progress / 100;
            int calcBlue = Math.min(baseBlue, negBlue) + distBlue * progress / 100;
        	mViewsActualBgColors[i] = Color.rgb(calcRed, calcGreen, calcBlue);
        	
        }
    	
    }
    
   
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.i(TAG, "Entered onSaveInstanceState");

        // save the current foreground feed
        for (int i = 0; i < (VIEWS_NUMBER); i++){
        	
        	savedInstanceState.putInt(VIEW_ACTUAL_BG_KEY + i, mViewsActualBgColors[i]);         	
        	savedInstanceState.putInt(VIEW_BASE_BG_KEY + i, mViewsBaseBgColors[i]);         	
            Log.i(TAG, "Saved #" + i + " color " +  mViewsActualBgColors[i]);

        }
        
        // as recommended by android basics training, 
        // always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }


}
