package org.genesis.toolbox.beans.config;

import org.genesis.toolbox.beans.repository.DataInspector;
import org.genesis.toolbox.beans.repository.impl.DataInspectorImpl;
import org.genesis.toolbox.beans.service.DataCheckService;
import org.genesis.toolbox.beans.service.impl.DataCheckServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ServiceConfig
 * @Package org.genesis.toolbox.beans.config
 * @Description: service configurations
 * @date 2018/7/31 10:19
 */

@Configuration
public class ServiceConfig {
    @Bean
    public DataInspector dataInspector() {
        return new DataInspectorImpl();
    }

    @Bean
    public DataCheckService dataCheckProvider() {
        DataCheckService service = new DataCheckServiceImpl();
        ((DataCheckServiceImpl) service).setDataInspector(dataInspector());

        return service;
    }
}
