package com.boscotec.game;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.FragmentManager;

import com.boscotec.game.fragment.FragmentCalculate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(this);

        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.flContent, new FragmentCalculate())
                    .commit();
        }
     }

    @Override
    public void onClick(View view) {
     int id = view.getId();

     if(id == R.id.fab){
         Toast.makeText(this, "Feature to be implemented in Version 2.0", Toast.LENGTH_SHORT).show();
     }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            return true;
        }else if (id == R.id.search) {
            return true;
        }else if(id == R.id.share){
            shareText();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareText() {
        String textToShare = "Download app by Boscotec " + "https://github.com/Boscotec/game/blob/master/source/game.apk";
        String mimeType = "text/plain";
        String title = "Share "+ getApplication().getApplicationInfo().name;

        /* ShareCompat.IntentBuilder provides a fluent API for creating Intents */
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }


}
