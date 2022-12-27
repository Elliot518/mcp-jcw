package org.genesis.toolbox.beans.domains.region;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: RegionElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: region element in xml curation
 * @date 2018/7/11 15:01
 */
public class RegionElement {
    private String path;
    private String columns;
    private String expressions;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getExpressions() {
        return expressions;
    }

    public void setExpressions(String expressions) {
        this.expressions = expressions;
    }
}

