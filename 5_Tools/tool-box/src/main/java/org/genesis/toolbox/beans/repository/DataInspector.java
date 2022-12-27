package org.genesis.toolbox.beans.repository;

import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.ui.component.MyTextPane;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @InterfaceName: DataInspector
 * @Package org.genesis.toolbox.beans.domains.action
 * @Description: inspect the data of domd
 * @date 2018/7/30 17:19
 */
public interface DataInspector {
    Boolean validateInputData(EntityMap entityMap, HashMap<String, InMemoryTable> inputTables, MyTextPane textPane);
}
