package org.genesis.toolbox.beans.domains.database;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: InMemoryDbContext
 * @Package org.genesis.toolbox.beans.domains.database
 * @Description: excel data in memory database context
 * @date 2018/7/16 23:17
 */
public class InMemoryDbContext {
    private HashMap<String, InMemoryTable> inputTables;
    private InMemoryTable outputTable;

    public HashMap<String, InMemoryTable> getInputTables() {
        return inputTables;
    }

    public void setInputTables(HashMap<String, InMemoryTable> inputTables) {
        this.inputTables = inputTables;
    }

    public InMemoryTable getOutputTable() {
        return outputTable;
    }

    public void setOutputTable(InMemoryTable outputTable) {
        this.outputTable = outputTable;
    }
}
