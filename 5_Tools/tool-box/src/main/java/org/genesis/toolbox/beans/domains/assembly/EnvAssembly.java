package org.genesis.toolbox.beans.domains.assembly;

import lombok.Data;
import org.genesis.toolbox.beans.domains.group.CodeConfigGroup;
import org.genesis.toolbox.beans.domains.group.DomdGroup;
import org.genesis.toolbox.beans.domains.group.FolderGroup;
import org.genesis.toolbox.beans.domains.group.RegionGroup;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EnvAssembly
 * @Package org.genesis.fastcode.beans.domains
 * @Description: environment assembly for EnvService
 * @date 2018/6/4 14:41
 */
@Data
public class EnvAssembly {
    private CodeConfigGroup codeConfigGroup;
    private RegionGroup regionGroup;
    private FolderGroup folderGroup;
    private DomdGroup domdGroup;
}
