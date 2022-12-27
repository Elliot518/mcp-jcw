package org.genesis.toolbox.beans.service;

import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;

/**
 * @author KG(Kelvin Gu)
 * @InterfaceName: EnvService
 * @Package org.genesis.toolbox.beans.service
 * @Description: env service for config
 * @date 2018/6/22 11:02
 */
public interface EnvService {
    /**
     * one time initialzation for whole app
     * @return EnvAssembly
     */
    EnvAssembly bootstrap(String configMode);
}
