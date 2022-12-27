package org.genesis.toolbox.beans.domains.config;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: GlobalConfig
 * @Package org.genesis.toolbox.beans.domains
 * @Description: config for global variables
 * @date 2018/6/1 16:40
 */
public class GlobalAppConfig {
    private String author;
    private String basePackage;
    private String autoCurationXmlFile;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAutoCurationXmlFile() {
        return autoCurationXmlFile;
    }

    public void setAutoCurationXmlFile(String autoCurationXmlFile) {
        this.autoCurationXmlFile = autoCurationXmlFile;
    }
}


