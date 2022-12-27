package org.genesis.toolbox.beans.domains.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MySetting
 * @Package org.genesis.fastcode.beans.domains
 * @Description: my settings
 * @date 2018/5/25 16:47
 */
@Component
public class MySetting {
    @Value("${setting.author}")
    private String author;
    @Value("${setting.version}")
    private String version;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String versionInfo = "Author: %s \nVersion: %s\n";

        return String.format(versionInfo, getAuthor(), getVersion());
    }
}
