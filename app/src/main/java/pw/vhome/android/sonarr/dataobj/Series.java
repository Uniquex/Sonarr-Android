package pw.vhome.android.sonarr.dataobj;

/**
 * Created by wvitz on 21.03.2017.
 */

public class Series {
    private String title;
    private String imdbID;
    private String status;



    public Series(String title, String status, String imdbID) {
        this.title = title;
        this.status = status;
        this.imdbID = imdbID;
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
}
