package org.genesis.toolbox.beans.domains.excel;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EntityColumnSet
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: source regions columns indexes
 * @date 2018/6/21 16:27
 */
public class EntityColumnSet {
    /**
     * source columns
     */
    private int systemColumn;
    private int objectColumn;
    private int attributeColumn;

    public int getSystemColumn() {
        return systemColumn;
    }

    public void setSystemColumn(int systemColumn) {
        this.systemColumn = systemColumn;
    }

    public int getObjectColumn() {
        return objectColumn;
    }

    public void setObjectColumn(int objectColumn) {
        this.objectColumn = objectColumn;
    }

    public int getAttributeColumn() {
        return attributeColumn;
    }

    public void setAttributeColumn(int attributeColumn) {
        this.attributeColumn = attributeColumn;
    }
}








