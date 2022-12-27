package org.genesis.toolbox.beans.core;

import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: CodeGenerator
 * @Package org.genesis.toolbox.beans.core
 * @Description: abstract code generator
 * @date 2018/6/25 10:18
 */
public abstract class CodeGenerator {
    public abstract void generate(EnvAssembly envAssembly);

    public void output(EnvAssembly envAssembly) {
        // core method to generate code
        generate(envAssembly);
    }
}
