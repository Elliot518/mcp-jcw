package org.genesis.toolbox.beans.core;

import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;

/**
 * @author KG
 * @developer
 * @description
 * @date Created in 2022年10月08日 2:36 PM
 * @modified_by
 */
public interface ICodeGenerator {
    /**
     * generate code
     * @author KG
     * @date 2022/10/8 2:37 PM
     * @param envAssembly
     */
    void generate(EnvAssembly envAssembly);
}
