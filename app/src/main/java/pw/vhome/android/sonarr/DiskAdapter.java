package pw.vhome.android.sonarr;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import pw.vhome.android.sonarr.dataobj.Disk;

/**
 * Created by vitz on 29.03.17.
 */

public class DiskAdapter extends ArrayAdapter<Disk> {

    private String TAG = ListViewAdapter.class.getSimpleName();
    private Context _context;
    int _layoutId;

    public DiskAdapter(Context context, int layoutId, ArrayList<Disk> disks) {
        super(context, layoutId, disks);
        _context = context;
        _layoutId = layoutId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View v = view;

        Disk dk = getItem(position);

        String path = dk.getPath();
        String label = dk.getLabel();
        String freeSpace = dk.getFreeSpace();
        String totalSpace = dk.getTotalSpace();

        if (v == null) {
            LayoutInflater li;
            li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.disk_item, null);
        }

        Disk p = getItem(position);

        if(p != null) {
            TextView tv_path = (TextView)v.findViewById(R.id.tv_disk_path);
            TextView tv_label = (TextView)v.findViewById(R.id.tv_disk_label);
            PieView pieView = (PieView) v.findViewById(R.id.pieView);

            Double freeSpaceD = Double.parseDouble(p.getFreeSpace());
            Double totalSpaceD = Double.parseDouble(p.getTotalSpace());

            if(p.getFreeSpace() != null && p.getTotalSpace() != null){

                Long percentage = 100-(Math.round((freeSpaceD/totalSpaceD)*100));
                pieView.setPercentage(percentage);

                //pieView.setPercentageBackgroundColor(R.color.colorAccent);

                //pieView.setMainBackgroundColor(R.color.coolGrey);

                PieAngleAnimation animation = new PieAngleAnimation(pieView);
                animation.setDuration(1500);

                pieView.startAnimation(animation);


                Log.i(TAG, Float.toString(pieView.getPercentage()));

                pieView.setInnerText(percentage.toString()+" %");
            }
            if(tv_label != null){
                tv_label.setText(label);
            }
            if(tv_path != null){
                tv_path.setText(path);
            }

        }
        return v;
    }

//    public class  mClickListener implements View.OnClickListener {
//
//        private Episode ep;
//        private Context context;
//
//        public mClickListener(Episode ep, Context context){
//            this.ep = ep;
//            this.context = context;
//        }
//
//
//        public void onClick(View v) {
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("episode", this.ep);
//            Intent intent = new Intent(v.getContext(), Pitem_activity.class);
//            intent.putExtras(bundle);
//            context.startActivity(intent);
//        }
//    };

}