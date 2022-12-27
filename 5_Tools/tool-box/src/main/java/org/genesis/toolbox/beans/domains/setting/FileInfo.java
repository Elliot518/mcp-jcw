package org.genesis.toolbox.beans.domains.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: FileInfo
 * @Package org.genesis.fastcode.beans.domains
 * @Description: file information, eg: output folder, template location
 * @date 2018/5/25 17:21
 */
@Component
public class FileInfo {
    @Value("${output.folder}")
    private String outputFolder;

    @Value("${template.location}")
    private String tempLocation;

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getTempLocation() {
        return tempLocation;
    }

    public void setTempLocation(String tempLocation) {
        this.tempLocation = tempLocation;
    }
}
