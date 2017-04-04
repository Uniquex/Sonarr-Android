package pw.vhome.android.sonarr.util;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import pw.vhome.android.sonarr.dataobj.Episode;

/**
 * Created by wvitz on 21.03.2017.
 */


public class JsonHandler extends AsyncTask<URL, Void, ArrayList<Episode>> {

    private static final String TAG = JsonHandler.class.getSimpleName();

    @Override
    protected ArrayList<Episode> doInBackground(URL... params) {
        URL searchUrl = params[0];
        String apiSearchResults = null;
        try {
            apiSearchResults = HttpHandler.getResponseFromHttpUrl(searchUrl);
            if(apiSearchResults == null){
                return null;
            }
            Log.v(TAG, "HTTP response: "+apiSearchResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonBuilder.getArray(apiSearchResults);
    }

    @Override
    protected void onPostExecute(ArrayList<Episode> apiSearchResults) {
        if (apiSearchResults != null && !apiSearchResults.equals("")) {
            Log.v(TAG, "Result: " + apiSearchResults);
    }
    }
}