package pw.vhome.android.sonarr.dataobj;

import java.io.Serializable;

/**
 * Created by wvitz on 03.04.2017.
 */

public class Disk implements Serializable {
    private String path;
    private String label;
    private String freeSpace;
    private String totalSpace;

    public Disk(String path, String label, String freeSpace, String totalSpace) {
        this.path = path;
        this.label = label;
        this.freeSpace = freeSpace;
        this.totalSpace = totalSpace;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }

    @Override
    public String toString(){
        return "Disk: "+ path + "Free space: " + freeSpace + " Total Space: " + totalSpace;
    }

}
