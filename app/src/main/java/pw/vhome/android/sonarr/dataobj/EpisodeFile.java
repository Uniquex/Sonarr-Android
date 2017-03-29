package pw.vhome.android.sonarr.dataobj;

import java.util.Date;

/**
 * Created by wvitz on 21.03.2017.
 */

public class EpisodeFile {
    private String quality;
    private int qualityID;
    private String path;
    private Date dateAdded;

    public EpisodeFile(String quality, int qualityID, String path, Date dateAdded) {
        this.quality = quality;
        this.qualityID = qualityID;
        this.path = path;
        this.dateAdded = dateAdded;
    }

    public String getQuality() {

        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getQualityID() {
        return qualityID;
    }

    public void setQualityID(int qualityID) {
        this.qualityID = qualityID;
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
