package pw.vhome.android.sonarr.util;

        import android.app.Activity;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.net.Uri;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.view.View;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.Scanner;

        import pw.vhome.android.sonarr.SettingsActivity;

/**
 * Created by Ravi Tamada on 01/09/16.
 * www.androidhive.info
 */
public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();
    //private static final String SONARR_BASE_URL = "https://sonarr.vhome.pw/api";
    private String sonarBaseUrl;
    private String port;
    private static final String FUNCTION = "calendar";
    private static final String DPFUNCTION = "diskspace";
    private static final String QUERYPARAM = "apikey";
    private static final String API_KEY = "4e1ca3f72b744853825c0f5f1ec38a99";

    private Context context;

    // API URL Example
    //"http://sonarr.ip:port/api/calendar?apikey=YourApiKey&start=2014-01-26&end=2014-01-27"

    // Image URL Example
    //

    public HttpHandler(Context context) {
        this.context = context;
    }

    public URL buildUrlEpisodes(String startDate, String endDate) {
        String response = null;
        port = null;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.sonarBaseUrl = sharedPref.getString("server_url", null);
        try{
            port = sharedPref.getString("server_port", null);

        } catch (NullPointerException npexc){
            Log.i(TAG, "No Port defined");
        }

        try {
            sonarBaseUrl = sonarBaseUrl.concat(port != null && !port.equals("0") ? (new String(":" + port)) : null);
        } catch (NullPointerException npexc2)
        {
            Log.e(TAG, npexc2.toString());
        }

        sonarBaseUrl = sonarBaseUrl.concat("/api");

        Uri builtUri = Uri.parse(this.sonarBaseUrl).buildUpon()
                .appendPath(FUNCTION)
                .appendQueryParameter(QUERYPARAM, API_KEY)
                .appendQueryParameter("start", startDate)
                .appendQueryParameter("end", endDate)
                .build();

        Log.i(TAG, builtUri.toString());

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL buildUrlDisks() {
        String response = null;
        port = null;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.sonarBaseUrl = sharedPref.getString("server_url", null);
        try{
            port = sharedPref.getString("server_port", null);

        } catch (NullPointerException npexc){
            Log.i(TAG, "No Port defined");
        }

        try {
            sonarBaseUrl = sonarBaseUrl.concat(port != null && !port.equals("0") ? (new String(":" + port)) : null);
        } catch (NullPointerException npexc2)
        {
            Log.e(TAG, npexc2.toString());
        }

        sonarBaseUrl = sonarBaseUrl.concat("/api");

        Uri builtUri = Uri.parse(this.sonarBaseUrl).buildUpon()
                .appendPath(DPFUNCTION)
                .appendQueryParameter(QUERYPARAM, API_KEY)
                .build();

        Log.i(TAG, builtUri.toString());

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}