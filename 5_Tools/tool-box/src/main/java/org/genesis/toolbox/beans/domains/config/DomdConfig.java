package org.genesis.toolbox.beans.domains.config;

import org.genesis.toolbox.beans.domains.excel.EntityColumnSet;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.excel.EntityMapSet;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DomdConfig
 * @Package org.genesis.toolbox.beans.domains.config
 * @Description: entity config for source and target
 * @date 2018/6/22 11:16
 */
public class DomdConfig {
    private String excelTemplateName;

    private EntityMapSet entityMap;

    private int inputSheetIndex;

    public EntityMapSet getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(EntityMapSet entityMap) {
        this.entityMap = entityMap;
    }

    public String getExcelTemplateName() {
        return excelTemplateName;
    }

    public void setExcelTemplateName(String excelTemplateName) {
        this.excelTemplateName = excelTemplateName;
    }

    public int getInputSheetIndex() {
        return inputSheetIndex;
    }

    public void setInputSheetIndex(int inputSheetIndex) {
        this.inputSheetIndex = inputSheetIndex;
    }
}
