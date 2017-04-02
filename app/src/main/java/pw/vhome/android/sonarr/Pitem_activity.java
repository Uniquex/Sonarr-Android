package pw.vhome.android.sonarr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import pw.vhome.android.sonarr.dataobj.Episode;

public class Pitem_activity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton floatbutton;
    private ImageView ivPoster;
    private TextView tvSTitle;
    private TextView tvETitle;
    private TextView tvEpNumber;
    private TextView tvSeNumber;
    private TextView tvHasFile;
    private TextView tvPath;
    private TextView tvSize;
    private TextView tvQuality;
    private TextView tvOverview;
    private Episode ep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitem_activity);
        this.toolbar = (Toolbar) findViewById(R.id.pitem_toolbar);
        setSupportActionBar(toolbar);

        floatbutton = (FloatingActionButton) findViewById(R.id.pitem_FAB);
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Iconify.with(new FontAwesomeModule());

        floatbutton.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar_plus_o)
                .color(R.color.colorPrimary)
                .actionBarSize());


        ivPoster = (ImageView) findViewById(R.id.iv_Poster);
        tvSTitle = (TextView) findViewById(R.id.pitem_sT);
        tvETitle = (TextView) findViewById(R.id.pitem_eT);
        tvEpNumber = (TextView) findViewById(R.id.pitem_eN);
        tvSeNumber = (TextView) findViewById(R.id.pitem_sN);
        tvOverview = (TextView) findViewById(R.id.pitem_overview);

        tvHasFile = (TextView) findViewById(R.id.pitem_hasFile);
        tvPath = (TextView) findViewById(R.id.pitem_FPath);
        tvSize = (TextView) findViewById(R.id.pitem_FSize);
        tvQuality = (TextView) findViewById(R.id.pitem_FQuality);




        setEpisode();
        LoadContent();

    }

    public void setEpisode(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Episode ep =(Episode) bundle.getSerializable("episode");

        this.ep = ep;
    }

    public void LoadContent(){

        getSupportActionBar().setTitle(ep.getSeries().getTitle()+" - "+ep.getTitle());

        String posterurl;



        Picasso.with(getApplicationContext())
                .load(Uri.parse(ep.getSeries().getPoster()))
                .into(ivPoster);

        tvSTitle.setText(ep.getSeries().getTitle());
        tvETitle.setText(ep.getTitle());
        tvOverview.setText(ep.getOverview());

        tvSeNumber.setText("S"+(ep.getSeasonNumber() < 10 ? "0" : "") + ep.getSeasonNumber());
        tvEpNumber.setText("E"+(ep.getEpisodeNumber() < 10 ? "0" : "") + ep.getEpisodeNumber());

        tvHasFile.setText(ep.hasFile() ? "hasFile" : "N/A");

        String size = String.format("%.2f",((((Double)(ep.getFile().getSize()))/1024)/1024)/1024);

        if(size.startsWith("-")){
            size = size.substring(1);
        }


        if(ep.hasFile()){
            tvPath.setText(ep.getFile().getPath());
            tvSize.setText(size + " GB");
            tvQuality.setText(ep.getFile().getQuality());
        }
    }

}
