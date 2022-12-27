package org.genesis.toolbox.beans.domains.action;

import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.region.RegionFieldItem;

import java.util.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: InMemoryDbHelper
 * @Package org.genesis.toolbox.beans.domains.action
 * @Description: inmemory table helper to operate on in memory tables
 * @date 2018/8/2 14:18
 */
public class InMemoryDbHelper {
    public static InMemoryTable findTableByName(HashMap<String, InMemoryTable> tables, String name) {
        InMemoryTable table = tables.get(name);

        return table;
    }

    public static InMemoryTable transformTableDataByAttribute(InMemoryTable table, String sourceAttribute, String targetAttribute) {
        InMemoryTable outputTable = new InMemoryTable();
        outputTable.setTableName(table.getTableName());
        List<HashMap<String, String>> outputTableData = new ArrayList<>();
        outputTable.setTableColumns(outputTableData);

        List<HashMap<String, String>> tableData = table.getTableColumns();
        for (HashMap<String, String> tableRow : tableData) {
            HashMap<String, String> outputRow = new HashMap<>();

            Map.Entry<String, String> rowEntry;
            Iterator<Map.Entry<String, String>> iterator = tableRow.entrySet().iterator();
            while (iterator.hasNext()) {
                rowEntry = iterator.next();
                if (rowEntry.getKey().equals(sourceAttribute)) {
                    // replace source attribute with target attribute
                    outputRow.put(targetAttribute, rowEntry.getValue());
                } else {
                    continue;
                }
            }

            outputTableData.add(outputRow);
        }

        return outputTable;
    }
}

