package org.genesis.toolbox.beans.domains.group;

import org.genesis.toolbox.beans.domains.config.AutoCurationConfig;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.region.RegionEntity;

import java.util.HashMap;
import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: RegionGroup
 * @Package org.genesis.toolbox.beans.domains.group
 * @Description: region group for computing
 * @date 2018/6/22 14:29
 */
public class RegionGroup {
    private AutoCurationConfig autoCurationConfig;

    public AutoCurationConfig getAutoCurationConfig() {
        return autoCurationConfig;
    }

    public void setAutoCurationConfig(AutoCurationConfig autoCurationConfig) {
        this.autoCurationConfig = autoCurationConfig;
    }
}
