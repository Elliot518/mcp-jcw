package org.genesis.toolbox.beans.core.impl;

import org.genesis.toolbox.beans.core.ICodeGenerator;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.config.CodeGenConfig;
import org.genesis.toolbox.beans.domains.group.CodeConfigGroup;
import org.genesis.toolbox.helper.CodeHelper;
import org.genesis.toolbox.util.MybatisPlusGeneratorUtils;
import org.springframework.stereotype.Service;

/**
 * @author KG
 * @developer
 * @description
 * @date Created in 2022年10月08日 2:37 PM
 * @modified_by
 */
public class MybatisPlusCodeGenerator implements ICodeGenerator {
    @Override
    public void generate(EnvAssembly envAssembly) {
        CodeConfigGroup configGroup = envAssembly.getCodeConfigGroup();
        if (configGroup == null) {
            return;
        }
        CodeGenConfig config = configGroup.getCodeGenConfig();
        if (config == null) {
            return;
        }

        // generate mybatis plus code hierarchy
        MybatisPlusGeneratorUtils.createCodeStructure(config);

        // rename service file names to repository file names
        CodeHelper.renameServicesToRepositories(config);

        // rename service impls to repository impls
        CodeHelper.renameServiceImplsToRepositoryImpls(config);

        // add time table field annotations
        //CodeHelper.addTimeTableFields();
    }
}
