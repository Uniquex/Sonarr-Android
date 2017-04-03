package pw.vhome.android.sonarr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pw.vhome.android.sonarr.dataobj.Disk;
import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.util.HttpHandler;
import pw.vhome.android.sonarr.util.JsonHandler;
import pw.vhome.android.sonarr.util.JsonHandlerDisks;

public class DiskStatus extends AppCompatActivity {
    TextView path;


    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.path = (TextView) findViewById(R.id.ds_path);

        makeAPIQuery();
    }

    private void makeAPIQuery() {
        //String apiQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = new HttpHandler(getApplicationContext()).buildUrlDisks();

        try {

            ArrayList<Disk> disks = new JsonHandlerDisks().execute(githubSearchUrl).get();

            path.setText("");

            for(int x = 0; x < disks.size(); x++){
                path.append(this.path.getText()+disks.get(x).toString());
            }


        } catch (InterruptedException irexc) {
            Log.w(TAG, "InterruptedException: " + irexc.toString());
        } catch (ExecutionException exexc) {
            Log.w(TAG, "ExectutionException: " + exexc.toString());
        }

    }

}
