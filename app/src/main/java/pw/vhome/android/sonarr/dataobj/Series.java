package pw.vhome.android.sonarr.dataobj;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by wvitz on 21.03.2017.
 */

public class Series implements Serializable{
    private String title;
    private String imdbID;
    private String status;
    private String poster;



    public Series(String title, String status, String imdbID, String poster) {
        this.title = title;
        this.status = status;
        this.imdbID = imdbID;
        this.poster  = poster;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
