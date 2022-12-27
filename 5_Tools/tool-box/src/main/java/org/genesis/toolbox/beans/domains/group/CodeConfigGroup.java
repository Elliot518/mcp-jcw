package org.genesis.toolbox.beans.domains.group;

import lombok.Data;
import org.genesis.toolbox.beans.domains.config.CodeGenConfig;
import org.genesis.toolbox.beans.domains.config.DomdConfig;
import org.genesis.toolbox.beans.domains.config.FolderConfig;
import org.genesis.toolbox.beans.domains.config.GlobalAppConfig;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: CodeConfigGroup
 * @Package org.genesis.toolbox.beans.domains.group
 * @Description: code config group contains all sub configs
 * @date 2018/6/22 11:12
 */
@Data
public class CodeConfigGroup {
    private GlobalAppConfig globalAppConfig;
    private FolderConfig folderConfig;
    private DomdConfig domdConfig;
    private CodeGenConfig codeGenConfig;
}


