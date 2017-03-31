package pw.vhome.android.sonarr.dataobj;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wvitz on 21.03.2017.
 */

public class EpisodeFile implements Serializable{
    private String quality;
    private double size;
    private String path;
    private Date dateAdded;

    public EpisodeFile(String quality, double size, String path, Date dateAdded) {
        this.quality = quality;
        this.size = size;
        this.path = path;
        this.dateAdded = dateAdded;
    }

    public String getQuality() {

        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
