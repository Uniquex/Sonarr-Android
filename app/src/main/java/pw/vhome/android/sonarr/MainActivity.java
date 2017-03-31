package pw.vhome.android.sonarr;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.util.HttpHandler;
import pw.vhome.android.sonarr.util.JsonHandler;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv_EpisodeList;

    //Drawer
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_EpisodeList = (ListView) findViewById(R.id.episode_list);

        //tvEpisodes = (TextView) findViewById(R.id.episode_list);
        makeAPIQuery();


    }

    private void makeAPIQuery() {
        //String apiQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = HttpHandler.buildUrl("2017-03-01", "2017-05-05");
        //mUrlDisplayTextView.setText(githubSearchUrl.toString());
        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        try {
            ArrayList<Episode> episodes = new JsonHandler().execute(githubSearchUrl).get();

            fillTextViews(episodes);


        } catch (InterruptedException irexc) {
            Log.w(TAG, "InterruptedException: " + irexc.toString());
        } catch (ExecutionException exexc) {
            Log.w(TAG, "ExectutionException: " + exexc.toString());
        }

    }

    private void fillTextViews(ArrayList<Episode> episodes) {
        ListViewAdapter lvadapter = new ListViewAdapter(getBaseContext(), R.layout.list_item, episodes);

        lv_EpisodeList.setAdapter(lvadapter);
    }


}