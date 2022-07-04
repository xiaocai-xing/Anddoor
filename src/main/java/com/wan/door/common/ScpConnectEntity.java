package com.wan.door.common;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo
 * @description: com.wan.door.common
 * @date:2022/6/28
 */
public class ScpConnectEntity {
    private String remoteuserName;

    private String remotepassWord;

    private String remoteurl;

    private String targetPath;



    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getRemoteuserName() {
        return remoteuserName;
    }

    public void setRemoteuserName(String remoteuserName) {
        this.remoteuserName = remoteuserName;
    }

    public String getRemotepassWord() {
        return remotepassWord;
    }

    public void setRemotepassWord(String remotepassWord) {
        this.remotepassWord = remotepassWord;
    }

    public String getRemoteurl() {
        return remoteurl;
    }

    public void setRemoteurl(String remoteurl) {
        this.remoteurl = remoteurl;
    }
}
