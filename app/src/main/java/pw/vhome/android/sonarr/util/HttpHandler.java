package pw.vhome.android.sonarr.util;

        import android.net.Uri;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.Scanner;

/**
 * Created by Ravi Tamada on 01/09/16.
 * www.androidhive.info
 */
public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();
    private static final String SONARR_BASE_URL = "https://sonarr.vhome.pw/api";
    private static final String FUNCTION = "calendar";
    private static final String QUERYPARAM = "apikey";
    private static final String API_KEY = "4e1ca3f72b744853825c0f5f1ec38a99";

    // API URL Example
    //"http://sonarr.ip:port/api/calendar?apikey=YourApiKey&start=2014-01-26&end=2014-01-27"

    // Image URL Example
    //

    public HttpHandler() {
    }

    public static URL buildUrl(String startDate, String endDate) {
        String response = null;

            Uri builtUri = Uri.parse(SONARR_BASE_URL).buildUpon()
                    .appendPath(FUNCTION)
                    .appendQueryParameter(QUERYPARAM, API_KEY)
                    .appendQueryParameter("start", startDate)
                    .appendQueryParameter("end", endDate)
                    .build();

            Log.d(TAG, builtUri.toString());

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