package pw.vhome.android.sonarr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
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

    private String TAG = ListViewAdapter.class.getSimpleName();
    private Context _context;
    int _layoutId;

    public ListViewAdapter(Context context, int layoutId, ArrayList<Episode> episodes) {
        super(context, layoutId, episodes);
        _context = context;
        _layoutId = layoutId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View v = view;

        Episode ep = getItem(position);

        String stitle = ep.getSeries().getTitle();
        int eN = ep.getEpisodeNumber();
        int sN = ep.getSeasonNumber();
        Date airdate = ep.getAirDateUtc();
        String poster = ep.getSeries().getPoster();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");


        if (v == null) {
            LayoutInflater li;
            li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.list_item, null);
        }

        Episode p = getItem(position);

        if(p != null) {
            ImageView iv_Poster = (ImageView)v.findViewById(R.id.item_poster);
            TextView tv_Series = (TextView)v.findViewById(R.id.item_series);
            TextView tv_eN = (TextView)v.findViewById(R.id.item_episodeN);
            TextView tv_sN = (TextView)v.findViewById(R.id.item_seasonN);
            TextView tv_eAir = (TextView)v.findViewById(R.id.item_date);


            if(iv_Poster != null){
                Picasso.with(_context)
                        .load(Uri.parse(poster))
                        .resize(84, 119)
                        .into(iv_Poster);
            }
            if(tv_Series != null){
                tv_Series.setText(stitle);
            }
            if(tv_eN != null){
                tv_eN.setText("E"+(eN < 10 ? "0" : "") + eN);
            }
            if(tv_sN != null){
                tv_sN.setText("S"+(sN < 10 ? "0" : "") + sN);
            }
            if(tv_eAir != null){
                tv_eAir.setText(sdf.format(airdate));
            }
            if(ep.hasFile()){
                tv_sN.setTextColor(Color.GREEN);
            }
            else{
                tv_sN.setTextColor(Color.DKGRAY);
            }

            mClickListener mCL = new mClickListener(ep, getContext());

            v.setOnClickListener(mCL);
        }
        return v;
    }

    public class  mClickListener implements View.OnClickListener {

        private Episode ep;
        private Context context;

        public mClickListener(Episode ep, Context context){
            this.ep = ep;
            this.context = context;
        }


        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("episode", this.ep);
            Intent intent = new Intent(v.getContext(), Pitem_activity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };

}