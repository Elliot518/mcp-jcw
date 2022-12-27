package org.genesis.toolbox.beans.domains.region;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ParameterElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: parameter element for method
 * @date 2018/7/12 14:33
 */
public class ParameterElement {
    private String name;
    private String value;
    private String relationship;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}


