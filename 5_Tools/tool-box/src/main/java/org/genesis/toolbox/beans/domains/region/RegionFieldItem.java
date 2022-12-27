package org.genesis.toolbox.beans.domains.region;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: RegionFieldItem
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: region field item
 * @date 2018/8/1 14:46
 */
public class RegionFieldItem {
    private String systemName;
    private String objectName;
    private  String attributeName;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}

