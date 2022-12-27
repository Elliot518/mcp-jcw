package org.genesis.toolbox.beans.core.impl;

import org.genesis.toolbox.beans.core.CodeGenerator;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.setting.FileInfo;
import org.genesis.toolbox.constants.debug.DebugLevel;
import org.genesis.toolbox.util.Debugger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: BaseCodeGenerator
 * @Package org.genesis.toolbox.beans.core.impl
 * @Description: code generator generic implementation
 * @date 2018/6/25 10:21
 */
public class BaseCodeGenerator extends CodeGenerator {
    @Override
    public void generate(EnvAssembly envAssembly) {
        initialization(envAssembly);
    }

    private void initialization(EnvAssembly envAssembly) {
        Debugger.print("Before code generator...", DebugLevel.DBG);
    }
}
