package org.genesis.toolbox.beans.domains.config;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: AutoCurationConfig
 * @Package org.genesis.toolbox.beans.domains.config
 * @Description: auto curation config
 * @date 2018/7/10 15:52
 */
public class AutoCurationConfig {
    private String subPackage;
    private String xmlPathPrefix;
    private String executionClass;

    public String getSubPackage() {
        return subPackage;
    }

    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    public String getXmlPathPrefix() {
        return xmlPathPrefix;
    }

    public void setXmlPathPrefix(String xmlPathPrefix) {
        this.xmlPathPrefix = xmlPathPrefix;
    }

    public String getExecutionClass() {
        return executionClass;
    }

    public void setExecutionClass(String executionClass) {
        this.executionClass = executionClass;
    }
}
