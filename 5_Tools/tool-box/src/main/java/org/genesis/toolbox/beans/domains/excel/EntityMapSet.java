package org.genesis.toolbox.beans.domains.excel;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EntityMapSet
 * @Package org.genesis.toolbox.beans.domains.excel
 * @Description: columns index set of entity map
 * @date 2018/7/16 14:41
 */
public class EntityMapSet {
    private int sheetIndex;
    private EntityColumnSet sourceEntity;
    private EntityColumnSet targetEntity;

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public EntityColumnSet getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(EntityColumnSet sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public EntityColumnSet getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(EntityColumnSet targetEntity) {
        this.targetEntity = targetEntity;
    }
}

