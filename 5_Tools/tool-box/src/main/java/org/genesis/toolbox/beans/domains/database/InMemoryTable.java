package org.genesis.toolbox.beans.domains.database;

import java.util.HashMap;
import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: InMemoryTable
 * @Package org.genesis.toolbox.beans.domains.database
 * @Description: excel table data in memory
 * @date 2018/7/16 23:19
 */
public class InMemoryTable {
    private String tableName;
    private String tablePath;
    private List<HashMap<String, String>> tableColumns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTablePath() {
        return tablePath;
    }

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public List<HashMap<String, String>> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<HashMap<String, String>> tableColumns) {
        this.tableColumns = tableColumns;
    }
}



