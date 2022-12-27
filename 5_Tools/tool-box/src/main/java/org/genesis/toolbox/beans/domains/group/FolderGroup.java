package org.genesis.toolbox.beans.domains.group;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: FolderGroup
 * @Package org.genesis.toolbox.beans.domains.group
 * @Description: folder group of sub directories
 * @date 2018/7/10 11:03
 */
public class FolderGroup {
    private String appRootPath;
    private String outputFolder;
    private String autoCurationFolder;

    public String getAppRootPath() {
        return appRootPath;
    }

    public void setAppRootPath(String appRootPath) {
        this.appRootPath = appRootPath;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getAutoCurationFolder() {
        return autoCurationFolder;
    }

    public void setAutoCurationFolder(String autoCurationFolder) {
        this.autoCurationFolder = autoCurationFolder;
    }
}


