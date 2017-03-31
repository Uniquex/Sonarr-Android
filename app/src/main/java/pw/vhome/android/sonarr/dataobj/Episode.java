package pw.vhome.android.sonarr.dataobj;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wvitz on 21.03.2017.
 */

public class Episode implements Serializable{
    private Series series;
    private String title;
    private String overview;
    private int episodeNumber;
    private int seasonNumber;
    private boolean hasFile;
    private boolean monitored;
    private EpisodeFile file;
    private Date airDateUtc;

    public Episode(Series series, String title, String overview, int episodeNumber, int seasonNumber, boolean hasFile, boolean monitored, Date airDateUtc) {
        this.series = series;
        this.title = title;
        this.overview = overview;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.hasFile = hasFile;
        this.monitored = monitored;
        this.airDateUtc = airDateUtc;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Episode(Series series, String title, String overview, int episodeNumber, int seasonNumber, boolean hasFile, boolean monitored, EpisodeFile file, Date airDateUtc) {
        this.series = series;
        this.title = title;
        this.overview = overview;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.hasFile = hasFile;
        this.monitored = monitored;

        this.file = file;
        this.airDateUtc = airDateUtc;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public boolean hasFile() {
        return hasFile;
    }

    public void setHasFile(boolean hasFile) {
        this.hasFile = hasFile;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }

    public EpisodeFile getFile() {
        return file;
    }

    public void setFile(EpisodeFile file) {
        this.file = file;
    }

    public Date getAirDateUtc() {
        return airDateUtc;
    }

    public void setAirDateUtc(Date airDateUtc) {
        this.airDateUtc = airDateUtc;
    }
}
