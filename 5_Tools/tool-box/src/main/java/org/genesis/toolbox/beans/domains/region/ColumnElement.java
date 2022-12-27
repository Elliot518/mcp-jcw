package org.genesis.toolbox.beans.domains.region;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ColumnElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: column element
 * @date 2018/7/12 14:15
 */
public class ColumnElement {
    private String name;
    private String var;
    private Boolean isPK;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Boolean getPK() {
        return isPK;
    }

    public void setPK(Boolean PK) {
        isPK = PK;
    }
}

