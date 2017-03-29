package pw.vhome.android.sonarr;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.dataobj.Series;

/**
 * Created by vitz on 29.03.17.
 */

public class ListViewAdapter extends ArrayAdapter<Episode> {

    private String TAG = MainActivity.class.getSimpleName();
    private Context _context;
    int _layoutId;

    public ListViewAdapter(Context context,
                            int layoutId,
                            ArrayList<Episode> episodes) {
        super(context, layoutId, episodes);
        _context = context;
        _layoutId = layoutId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Episode ep = getItem(position);

        String stitle = ep.getSeries().getTitle();
        int epN = ep.getEpisodeNumber();
        int sN = ep.getSeasonNumber();
        Date airdate = ep.getAirDateUtc();
        Uri poster = ep.getSeries().getPoster();


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)_context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(_layoutId, parent,false);
        }

        ImageView sPoster = (ImageView)view.findViewById(R.id.item_poster);
        TextView iSeries = (TextView)view.findViewById(R.id.item_series);

        sPoster.setImageURI(Uri.parse(poster.toString()));
        iSeries.setText(stitle);


        return view;
    }

}