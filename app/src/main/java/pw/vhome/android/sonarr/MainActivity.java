package pw.vhome.android.sonarr;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import android.util.Log;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mikepenz.crossfader.Crossfader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.UIUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pw.vhome.android.sonarr.dataobj.Episode;
import pw.vhome.android.sonarr.util.CrossfadeWrapper;
import pw.vhome.android.sonarr.util.HttpHandler;
import pw.vhome.android.sonarr.util.JsonHandler;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv_EpisodeList;


    private MiniDrawer mDrawer;
    private Drawer result;
    private Crossfader crossFader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Iconify.with(new FontAwesomeModule());

        lv_EpisodeList = (ListView) findViewById(R.id.episode_list);

        buildDrawer(savedInstanceState);

        makeAPIQuery();


    }

    private void buildDrawer(Bundle savedInstanceState) {



        this.result = new DrawerBuilder()
                .withActivity(this)
        //        .withToolbar(toolbar)
                .withInnerShadow(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_Week).withIcon(new IconDrawable(this, FontAwesomeIcons.fa_calendar).color(R.color.colorPrimary).actionBarSize()),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_Month).withIcon(new IconDrawable(this, FontAwesomeIcons.fa_calendar).color(R.color.colorPrimary).actionBarSize()),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_Disk_Status).withIcon(new IconDrawable(this, FontAwesomeIcons.fa_hdd_o).color(R.color.colorPrimary).actionBarSize()),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_settings).withIcon(new IconDrawable(this, FontAwesomeIcons.fa_cog).color(R.color.primary).actionBarSize())
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener(){
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch(position) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                startActivity(new Intent(view.getContext(), DiskStatus.class));
                            case 4:
                                break;
                            case 5:
                                startActivity(new Intent(view.getContext(), SettingsActivity.class));
                                break;
                            default:
                                break;

                        }

                        //Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        return false;}})
                .withGenerateMiniDrawer(true)
                .withSavedInstance(savedInstanceState)
                .buildView();

        this.mDrawer = this.result.getMiniDrawer();

        //get the widths in px for the first and second panel
        int firstWidth = (int) UIUtils.convertDpToPixel(300, this);
        int secondWidth = (int) UIUtils.convertDpToPixel(60, this);

        crossFader = new Crossfader()
                .withContent(findViewById(R.id.episode_list))
                .withFirst(result.getSlider(), firstWidth)
                .withSecond(mDrawer.build(this), secondWidth)
                .withSavedInstance(savedInstanceState)
                .build();

        // inform the MiniDrawer about the crossfader.
        mDrawer.withCrossFader(new CrossfadeWrapper(crossFader));

    }

    private void makeAPIQuery() {

        URL apiSearchUrl = new HttpHandler(getApplicationContext()).buildUrlEpisodes("2017-01-01", "2017-05-05");

        try {
            ArrayList<Episode> episodes = new JsonHandler().execute(apiSearchUrl).get();

            fillTextViews(episodes);


        } catch (InterruptedException irexc) {
            Log.w(TAG, "InterruptedException: " + irexc.toString());
        } catch (ExecutionException exexc) {
            Log.w(TAG, "ExectutionException: " + exexc.toString());
        }

    }

    private void fillTextViews(ArrayList<Episode> episodes) {

        if(episodes == null){
            Toast.makeText(this, "Could not connect to Server", Toast.LENGTH_LONG).show();

        }else{
            ListViewAdapter lvadapter = new ListViewAdapter(getBaseContext(), R.layout.list_item, episodes);

            lv_EpisodeList.setAdapter(lvadapter);
        }

    }


}