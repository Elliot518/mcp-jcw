package org.genesis.toolbox.beans.domains.region;

import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MethodElement
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: method element
 * @date 2018/7/12 14:31
 */
public class MethodElement {
    private String name;
    private Boolean isPK;
    private String resultType;
    private String path;
    private List<ParameterElement> parameterElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPK() {
        return isPK;
    }

    public void setPK(Boolean PK) {
        isPK = PK;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ParameterElement> getParameterElements() {
        return parameterElements;
    }

    public void setParameterElements(List<ParameterElement> parameterElements) {
        this.parameterElements = parameterElements;
    }
}

