package org.genesis.toolbox.beans.domains.excel;

import org.genesis.toolbox.beans.domains.region.RegionEntity;
import org.genesis.toolbox.beans.domains.region.RegionFieldItem;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EntityMap
 * @Package org.genesis.toolbox.beans.domains.excel
 * @Description: entity map for the excel domd
 * @date 2018/7/12 15:04
 */
public class EntityMap {
    private HashMap<String, RegionEntity> sourceRegions;
    private HashMap<String, RegionEntity> targetRegion;

    /**
     * region field mapping
     */
    private HashMap<RegionFieldItem, RegionFieldItem> regionFieldMapping;

    public HashMap<String, RegionEntity> getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(HashMap<String, RegionEntity> targetRegion) {
        this.targetRegion = targetRegion;
    }

    public HashMap<String, RegionEntity> getSourceRegions() {
        return sourceRegions;
    }

    public void setSourceRegions(HashMap<String, RegionEntity> sourceRegions) {
        this.sourceRegions = sourceRegions;
    }

    public HashMap<RegionFieldItem, RegionFieldItem> getRegionFieldMapping() {
        return regionFieldMapping;
    }

    public void setRegionFieldMapping(HashMap<RegionFieldItem, RegionFieldItem> regionFieldMapping) {
        this.regionFieldMapping = regionFieldMapping;
    }
}


