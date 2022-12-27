package org.genesis.toolbox.beans.domains.group;

import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DomdGroup
 * @Package org.genesis.toolbox.beans.domains.group
 * @Description: domd group
 * @date 2018/7/16 15:05
 */
public class DomdGroup {
    private EntityMap entityMap;

    private HashMap<String, InMemoryTable> inputTables;

    public EntityMap getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(EntityMap entityMap) {
        this.entityMap = entityMap;
    }

    public HashMap<String, InMemoryTable> getInputTables() {
        return inputTables;
    }

    public void setInputTables(HashMap<String, InMemoryTable> inputTables) {
        this.inputTables = inputTables;
    }
}
