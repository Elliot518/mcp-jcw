package org.genesis.toolbox.beans.domains.region;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: UdfElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: element of user defined function
 * @date 2018/7/12 14:44
 */
public class UdfElement {
    private String name;
    private String resultType;
    private String parameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}

