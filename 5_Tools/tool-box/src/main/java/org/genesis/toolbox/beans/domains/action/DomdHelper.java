package org.genesis.toolbox.beans.domains.action;

import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.region.RegionEntity;
import org.genesis.toolbox.beans.domains.region.RegionFieldItem;
import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.constants.debug.DebugLevel;
import org.genesis.toolbox.fundamental.TwoTuple;
import org.genesis.toolbox.util.Debugger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DomdHelper
 * @Package org.genesis.toolbox.beans.domains.action
 * @Description: domd helper class
 * @date 2018/7/27 16:02
 */
public class DomdHelper {
    public static void printEntityMap(EntityMap map, MyTextPane textPane) {
        HashMap<String, RegionEntity> sourceRegions = map.getSourceRegions();
        HashMap<String, RegionEntity> targetRegion = map.getTargetRegion();

        //int sleepTime = PROGRESS_MAX * 100 / sourceRegions.size();

        Debugger.printLine(textPane);
        Debugger.printHead(textPane, "**** 1)&nbsp;Entity Map ****" , "#885FA0");
        Debugger.printSeparator(textPane);

        Debugger.printHtml(textPane, "**** Source Regions: ****" , DebugLevel.DBG, "#008000");
        printRegionDetail(sourceRegions, "#FFA500", "#E95242", "#008000", "#008000", textPane);

        Debugger.printLine(textPane);

        Debugger.printHtml(textPane, "**** Target Region: ****" , DebugLevel.DBG, "#80CAF4");
        printRegionDetail(targetRegion, "#8A2BE2", "#E95242", "#80CAF4", "#008000", textPane);

        Debugger.printLine(textPane);
        Debugger.printSeparator(textPane);
    }

    public static void printRegionDetail(HashMap<String, RegionEntity> regions, String regionColor, String isMainColor, String fieldColor, String splitColor, MyTextPane textPane) {
        for (Map.Entry<String, RegionEntity> entry : regions.entrySet()) {
            String region = entry.getKey();
            RegionEntity regionEntity = entry.getValue();
            Debugger.printHtml(textPane, "Region: " + region, DebugLevel.DBG, regionColor);
            Debugger.printHtml(textPane, "Is Main: " + regionEntity.getMain(), DebugLevel.DBG, isMainColor);
            Debugger.printHtml(textPane, "-------- Region Fields: -------", DebugLevel.DBG, splitColor);
            for (String field : regionEntity.getColumns()) {
                Debugger.printHtml(textPane, "&nbsp;-&nbsp;" + field, DebugLevel.DBG, fieldColor);
            }
            Debugger.printHtml(textPane, "___________________________________________________________", DebugLevel.DBG, splitColor);
        }
    }

    public static void printInMemoryTables(HashMap<String, InMemoryTable> memoryTables, MyTextPane textPane) {
        Debugger.printLine(textPane);
        Debugger.printHead(textPane, "**** 2) &nbsp;Input Tables ****", "#885FA0");
        Debugger.printSeparator(textPane);

        for (Map.Entry<String, InMemoryTable> memoryTable: memoryTables.entrySet()) {
            InMemoryTable memTable = memoryTable.getValue();
            printInMemoryTable(memTable, textPane);
        }
        Debugger.printSeparator(textPane);
    }

    public static void printInMemoryTable(InMemoryTable memTable, MyTextPane textPane) {
        Debugger.printHtml(textPane, "Region: " + memTable.getTableName(), DebugLevel.DBG, "#0198D9");
        Debugger.printTableSplitor(textPane);
        printInMemoryTableData(memTable, textPane);
        Debugger.printTableSplitor(textPane);
        Debugger.printLine(textPane);
    }

    public static void printInMemoryTableData(InMemoryTable table, MyTextPane textPane) {
        String color = "#008000";

        List<HashMap<String, String>> tableColumns = table.getTableColumns();
        int rowCount = 0;
        for (HashMap<String, String> line:tableColumns) {
            String headLine = "| ";
            String tableLine = "| ";

            for (Map.Entry<String, String> cell:line.entrySet()) {
                if (rowCount == 0) {
                    headLine += cell.getKey() + " | ";
                }
                tableLine += cell.getValue() + " | ";
            }
            if (rowCount == 0) {
                Debugger.printHtml(textPane, headLine, 0, "#4169E1");
            }
            Debugger.printHtml(textPane, tableLine, 0, "#008000");

            rowCount++;
        }
    }

    public static TwoTuple<Boolean, String> isRegionItemExists(RegionFieldItem regionItem, HashMap<String, InMemoryTable> inputTables) {

        TwoTuple<Boolean, String> result = null;

        // judge object name exists
        String objName = regionItem.getObjectName();
        InMemoryTable memTable = inputTables.get(objName);
        if (memTable == null) {
           result = new TwoTuple<>(false, "region:" + regionItem.getObjectName() + " not exists in input tables!!");
           return result;
        }

        // judge attribute exists
        String attributeName = regionItem.getAttributeName();
        List<HashMap<String, String>> tableColumns = memTable.getTableColumns();
        if (tableColumns == null) {
            result = new TwoTuple<>(false, "No region data!");
            return result;
        }
        HashMap<String, String> tableHead = tableColumns.get(0);
        if (tableHead.get(attributeName) == null) {
            result = new TwoTuple<>(false, "attribute: " + attributeName + " not exists in input tables!!");
            return result;
        }

        result = new TwoTuple<>(true, "OK");

        return result;
    }
}
