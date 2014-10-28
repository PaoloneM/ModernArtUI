package com.example.modernartui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;


public class ModernArt extends Activity {

    private static int COLORSCALE = 0;
    private static int GREYSCALE = 1;
    private static int VIEWS_NUMBER = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        // Create resources array

        ArrayList<TextView> mViews = new ArrayList<TextView>();

        for (int i = 1; i < (VIEWS_NUMBER+1); i++){

            String twName = "Tw" + i;
            int twId = getResources().getIdentifier(twName, "id", getPackageName());
            if (twId != 0){
                mViews.add((TextView) this.findViewById(twId));
            }

        }

        // set default color for text views
        for (TextView v : mViews) { v.setBackgroundColor(this.getRandomColor(COLORSCALE)); }

        Random randomView = new Random();
        mViews.get(randomView.nextInt(5)).setBackgroundColor(this.getRandomColor(GREYSCALE));


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
}
