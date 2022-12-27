package org.genesis.toolbox.beans.repository;

import org.genesis.toolbox.beans.domains.config.DomdConfig;
import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @InterfaceName: ExcelTemplateButler
 * @Package org.genesis.toolbox.beans.excel
 * @Description: manage excel templates
 * @date 2018/6/21 17:16
 */
public interface ExcelTemplateButler {
    EntityMap getEntityMap(DomdConfig domdConfig);

    HashMap<String, InMemoryTable> getInputTables(DomdConfig domdConfig);

    HashMap<String, InMemoryTable> getInputTablesBySpace(DomdConfig domdConfig);

}

