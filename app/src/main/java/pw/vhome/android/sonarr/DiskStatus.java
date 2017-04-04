package pw.vhome.android.sonarr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pw.vhome.android.sonarr.dataobj.Disk;
import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.util.HttpHandler;
import pw.vhome.android.sonarr.util.JsonHandler;
import pw.vhome.android.sonarr.util.JsonHandlerDisks;

public class DiskStatus extends AppCompatActivity {
    GridView gridView;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.gridView = (GridView) findViewById(R.id.disk_list);

        makeAPIQuery();
    }

    private void makeAPIQuery() {

        URL apiSearchUrl = new HttpHandler(getApplicationContext()).buildUrlDisks();

        try {
            ArrayList<Disk> disks = new JsonHandlerDisks().execute(apiSearchUrl).get();

            fillTextViews(disks);


        } catch (InterruptedException irexc) {
            Log.w(TAG, "InterruptedException: " + irexc.toString());
        } catch (ExecutionException exexc) {
            Log.w(TAG, "ExectutionException: " + exexc.toString());
        }

    }

    private void fillTextViews(ArrayList<Disk> disks) {

        if (disks == null) {
            Toast.makeText(this, "Could not connect to Server", Toast.LENGTH_LONG).show();

        } else {
            DiskAdapter dadapter = new DiskAdapter(getApplicationContext(), R.layout.disk_item, disks);

            gridView.setAdapter(dadapter);
        }

    }

}