package com.glg.baselib.util.appupdate;

public class BaseUpdate  {

    private String updateDes;
    private String url;
    private String storePath;
    private boolean forceUpdate;

    public BaseUpdate(String updateDes, String url, String storePath, boolean forceUpdate) {
        this.updateDes = updateDes;
        this.url = url;
        this.storePath = storePath;
        this.forceUpdate = forceUpdate;
    }

    public String getUpdateDes() {
        return updateDes;
    }

    public void setUpdateDes(String updateDes) {
        this.updateDes = updateDes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }
}
