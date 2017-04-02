package pw.vhome.android.sonarr.util;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.dataobj.EpisodeFile;
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

                String poster = null;
                EpisodeFile eFile;
                JSONArray images = seriesObj.getJSONArray("images");

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                for(int y = 0; y < images.length(); y++) {
                    if(images.getJSONObject(y).getString("coverType").equals("poster")){
                        poster = seriesObj.getJSONArray("images").getJSONObject(y).getString("url");

                        if(!poster.contains("http"))
                        {
                            StringBuilder sb = new StringBuilder(poster);
                            sb.insert(0, "https://sonarr.vhome.pw").toString();
                            poster = sb.append("?apikey=4e1ca3f72b744853825c0f5f1ec38a99").toString();
                        }
                        Log.v(TAG, "PosterLink: " + poster);
                    }
                }

                String epTitle = episodeObj.getString("title");
                String epOverview = episodeObj.getString("overview");
                int epNumber = episodeObj.getInt("episodeNumber");
                int epSNumber = episodeObj.getInt("seasonNumber");
                boolean epHasFile = episodeObj.getBoolean("hasFile");
                boolean epMonitored = episodeObj.getBoolean("monitored");
                String epAirDateUtc = episodeObj.getString("airDateUtc");


                if(episodeObj.getBoolean("hasFile")){
                    JSONObject epFileObject = episodeObj.getJSONObject("episodeFile");
                    String quality = epFileObject.getJSONObject("quality").getJSONObject("quality").getString("name"); ;
                    Double size = (double)epFileObject.getInt("size");
                    String path = epFileObject.getString("path");
                    Date dateAdded = df.parse(epFileObject.getString("dateAdded"));

                    eFile = new EpisodeFile(quality, size, path, dateAdded);
                }
                else {
                    eFile = new EpisodeFile(null, 0, null, null);
                }




                Date epAirDateUtcParsed;

                epAirDateUtcParsed = df.parse(epAirDateUtc);
                //String newDateString = df.format(startDate);
                //System.out.println(newDateString);
                //Log.d(TAG, "Episode: " + epTitle);

                episode.add(new Episode(new Series(tvTitle, tvStatus, tvImdb, poster), epTitle, epOverview ,epNumber, epSNumber, epHasFile, epMonitored, eFile, epAirDateUtcParsed));

            }







        } catch (JSONException jsonexc){
            Log.w(TAG, "Jason Excpetion: "+jsonexc.toString());
        } catch (ParseException e) {
            Log.w(TAG, "DateParsing Error: " + e.toString());
        } catch (NullPointerException npexc){
            Log.v(TAG, "Nullpointer Exception (No return from api)");
        }



        return episode;
    }
}
