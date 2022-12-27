package org.genesis.toolbox.beans.domains.region;

import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ViewRegionElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: view region element
 * @date 2018/7/12 11:26
 */
public class ViewRegionElement {
    private String name;
    private List<VarElement> varElements;
    private List<ColumnElement> columnElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VarElement> getVarElements() {
        return varElements;
    }

    public void setVarElements(List<VarElement> varElements) {
        this.varElements = varElements;
    }

    public List<ColumnElement> getColumnElements() {
        return columnElements;
    }

    public void setColumnElements(List<ColumnElement> columnElements) {
        this.columnElements = columnElements;
    }
}




