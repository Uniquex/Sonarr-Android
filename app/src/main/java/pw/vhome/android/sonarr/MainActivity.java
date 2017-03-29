package pw.vhome.android.sonarr;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    private TextView tvEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tvEpisodes = (TextView) findViewById(R.id.episode_list);
        makeAPIQuery();

    }

    private void makeAPIQuery(){
        //String apiQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = HttpHandler.buildUrl("2017-04-01", "2017-04-05");
        //mUrlDisplayTextView.setText(githubSearchUrl.toString());
        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        try {
            ArrayList<Episode> episodes = new JsonHandler().execute(githubSearchUrl).get();
        }catch(InterruptedException irexc){
            Log.w(TAG, "InterruptedException: " + irexc.toString());
        }catch (ExecutionException exexc) {
            Log.w(TAG, "ExectutionException: " + exexc.toString());
        }
    }

    private void fillTexViews(){

    }



//    @Override
//    protected void onPostExecute(Void result) {
//        super.onPostExecute(result);
//        // Dismiss the progress dialog
//        if (pDialog.isShowing())
//            pDialog.dismiss();
//        /**
//         * Updating parsed JSON data into ListView
//         * */
//        ListAdapter adapter = new SimpleAdapter(
//                MainActivity.this, contactList,
//                R.layout.list_item, new String[]{"name", "email",
//                "mobile"}, new int[]{R.id.name,
//                R.id.email, R.id.mobile});
//
//        lv.setAdapter(adapter);
//    }
}


