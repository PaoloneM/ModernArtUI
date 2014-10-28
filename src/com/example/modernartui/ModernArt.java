package com.example.modernartui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Color;


public class ModernArt extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        // set default color for text views
        TextView txtView = (TextView)findViewById(R.id.Tw1);
        txtView.setBackgroundColor(Color.YELLOW);
        txtView = (TextView)findViewById(R.id.Tw2);
        txtView.setBackgroundColor(Color.GREEN);
        txtView = (TextView)findViewById(R.id.Tw3);
        txtView.setBackgroundColor(Color.BLUE);
        txtView = (TextView)findViewById(R.id.Tw4);
        txtView.setBackgroundColor(Color.GRAY);
        txtView = (TextView)findViewById(R.id.Tw5);
        txtView.setBackgroundColor(Color.RED);
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
}
