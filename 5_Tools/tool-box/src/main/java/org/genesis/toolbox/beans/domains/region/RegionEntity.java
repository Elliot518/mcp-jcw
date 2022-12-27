package org.genesis.toolbox.beans.domains.region;

import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: RegionEntity
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: region entity
 * @date 2018/7/10 17:26
 */
public class RegionEntity {
    private String path;
    private Boolean isMain;
    private List<String> columns;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
