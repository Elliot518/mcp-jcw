package org.genesis.toolbox.beans.domains.region;

import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: RegionElementGroup
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: region element group in xml curation
 * @date 2018/7/11 15:03
 */
public class RegionElementGroup {
    private String mainRegionPath;
    private String mainRegionColumns;
    private List<RegionElement> otherRegions;

    public String getMainRegionPath() {
        return mainRegionPath;
    }

    public void setMainRegionPath(String mainRegionPath) {
        this.mainRegionPath = mainRegionPath;
    }

    public String getMainRegionColumns() {
        return mainRegionColumns;
    }

    public void setMainRegionColumns(String mainRegionColumns) {
        this.mainRegionColumns = mainRegionColumns;
    }

    public List<RegionElement> getOtherRegions() {
        return otherRegions;
    }

    public void setOtherRegions(List<RegionElement> otherRegions) {
        this.otherRegions = otherRegions;
    }
}
