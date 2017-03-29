package pw.vhome.android.sonarr.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.dataobj.Series;

/**
 * Created by wvitz on 29.03.2017.
 */

public class JsonBuilder {

    private static final String TAG = JsonBuilder.class.getSimpleName();

    public static ArrayList<Episode> getArray(String apiQuery){


        ArrayList<Episode> episode = new ArrayList<>();

        try {
            JSONArray episodeArray = new JSONArray(apiQuery);
            //Log.d(TAG, "Json: "+apiQuery);

            for(int x = 0; x < episodeArray.length(); x++){
                JSONObject episodeObj = episodeArray.getJSONObject(x);
                JSONObject seriesObj = episodeObj.getJSONObject("series");

                String tvTitle = seriesObj.getString("title");
                String tvStatus = seriesObj.getString("status");
                String tvImdb = seriesObj.getString("imdbId");

                String epTitle = episodeObj.getString("title");
                int epNumber = episodeObj.getInt("episodeNumber");
                int epSNumber = episodeObj.getInt("seasonNumber");
                boolean epHasFile = episodeObj.getBoolean("hasFile");
                boolean epMonitored = episodeObj.getBoolean("monitored");
                String epAirDateUtc = episodeObj.getString("airDateUtc");

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date epAirDateUtcParsed;

                epAirDateUtcParsed = df.parse(epAirDateUtc);
                //String newDateString = df.format(startDate);
                //System.out.println(newDateString);
                Log.d(TAG, "Episode: "+epTitle);

                episode.add(new Episode(new Series(tvTitle, tvStatus, tvImdb), epTitle, epNumber, epSNumber, epHasFile, epMonitored, epAirDateUtcParsed));
            }







        } catch (JSONException jsonexc){
            Log.w(TAG, "Jason Excpetion: "+jsonexc.toString());
        } catch (ParseException e) {
            Log.w(TAG, "DateParsing Error: " + e.toString());
        }



        return episode;
    }
}
