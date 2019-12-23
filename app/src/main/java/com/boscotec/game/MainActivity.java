package com.boscotec.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.FragmentManager;

import com.boscotec.game.fragment.FragmentCalculate;
import com.boscotec.game.interfaces.Listener;
import com.boscotec.game.web.CustomTabActivityHelper;
import com.boscotec.game.web.WebviewFallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements Listener {
    private CustomTabActivityHelper mCustomTabActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomTabActivityHelper = new CustomTabActivityHelper();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText();
            }
        });

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.flContent, new FragmentCalculate())
                    .commit();
        }
    }

    @Override
    public void openCustomTab(String url) {
        mCustomTabActivityHelper.mayLaunchUrl(Uri.parse(url), null, null);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        intentBuilder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        intentBuilder.setShowTitle(true);
        intentBuilder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        CustomTabActivityHelper.openCustomTab(this, intentBuilder.build(), Uri.parse(url), new WebviewFallback());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCustomTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCustomTabActivityHelper.unbindCustomTabsService(this);
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

        if (id == R.id.predicts) {
            openCustomTab("https://www.predictz.com");
            return true;
        } else if (id == R.id.solution) {
            openCustomTab("https://www.solutiontipster.com/category/rsk-papers/");
            return true;
        } else if (id == R.id.update) {
            googlePlayStore();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareText() {
        String textToShare = "Download Game by Boscotec " + "https://github.com/Boscotec/game/blob/master/source/game.apk";
        String mimeType = "text/plain";
        String title = "Share " + getApplication().getApplicationInfo().name;

        /* ShareCompat.IntentBuilder provides a fluent API for creating Intents */
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }

    private void googlePlayStore(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri.Builder uriBuilder = Uri.parse("https://play.google.com/store/apps/details").buildUpon().appendQueryParameter("id", getPackageName());
        intent.setData(uriBuilder.build());
        startActivity(intent);
    }

}
