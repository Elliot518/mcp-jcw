package org.genesis.toolbox.beans.repository.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.genesis.toolbox.beans.domains.config.DomdConfig;
import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.region.RegionEntity;
import org.genesis.toolbox.beans.domains.region.RegionFieldItem;
import org.genesis.toolbox.beans.repository.ExcelTemplateButler;
import org.genesis.toolbox.constants.code.KW;
import org.genesis.toolbox.constants.config.FoldersConf;
import org.genesis.toolbox.util.PathUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ExcelTemplateButlerImpl
 * @Package org.genesis.toolbox.beans.excel.impl
 * @Description: impl of excel templates manager
 * @date 2018/6/21 17:26
 */
@Repository
public class ExcelTemplateButlerImpl implements ExcelTemplateButler {
    private static DataFormatter formatter = new DataFormatter();

    @Override
    public EntityMap getEntityMap(DomdConfig domdConfig) {

        EntityMap map = new EntityMap();
        HashMap<String, RegionEntity> sourceRegions = new HashMap<>();
        HashMap<String, RegionEntity> targetRegion = new HashMap<>();

        String tempFilePath = PathUtil.getAppConfigPath() + KW.PATH_SP + FoldersConf.CFG_EXCEL + KW.PATH_SP + domdConfig.getExcelTemplateName();
        System.out.println("Template File: " + tempFilePath);

        HashMap<RegionFieldItem, RegionFieldItem> regionFieldMapping = new HashMap<>();
        File file = new File(tempFilePath);
        try (Workbook book = new XSSFWorkbook(new FileInputStream(file))) {
            Sheet sheet = book.getSheetAt(domdConfig.getEntityMap().getSheetIndex());
            int maxRegionColumnNum = 0;
            String mainRegion = "";
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                /**
                 * source calculation
                 */
                String sourceSystemName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getSourceEntity().getSystemColumn()));
                String sourceObjName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getSourceEntity().getObjectColumn()));

                if (sourceObjName.trim().isEmpty() || sourceObjName.trim().equals(KW.NA)) {
                    continue;
                }

                String sourceColName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getSourceEntity().getAttributeColumn()));
                String sourceRegionPath = KW.PATH_SP + sourceSystemName + KW.PATH_SP + sourceObjName;
                RegionEntity sourceRegionEntity = sourceRegions.get(sourceObjName);

                RegionFieldItem sourceRegionItem = new RegionFieldItem();
                sourceRegionItem.setSystemName(sourceSystemName);
                sourceRegionItem.setObjectName(sourceObjName);
                sourceRegionItem.setAttributeName(sourceColName);

                if (sourceRegionEntity == null) {
                    sourceRegionEntity = new RegionEntity();
                    sourceRegionEntity.setPath(sourceRegionPath);
                    List<String> regionFields = new ArrayList<>();
                    sourceRegionEntity.setMain(false);
                    sourceRegionEntity.setColumns(regionFields);
                    sourceRegions.put(sourceObjName, sourceRegionEntity);
                }
                sourceRegionEntity.getColumns().add(sourceColName);
                if (sourceRegionEntity.getColumns().size() > maxRegionColumnNum) {
                    maxRegionColumnNum = sourceRegionEntity.getColumns().size();
                    mainRegion = sourceObjName;
                }

                /**
                 * target calculation
                 */
                String targetSystemName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getTargetEntity().getSystemColumn()));
                String targetObjName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getTargetEntity().getObjectColumn()));

                if (targetSystemName.trim().isEmpty() || targetObjName.trim().equals(KW.NA)) {
                    continue;
                }

                String targetColName = formatter.formatCellValue(row.getCell(domdConfig.getEntityMap().getTargetEntity().getAttributeColumn()));
                String targetRegionPath = KW.PATH_SP + targetSystemName + KW.PATH_SP + targetObjName;
                RegionEntity targetRegionEntity = targetRegion.get(targetObjName);

                RegionFieldItem targetRegionItem = new RegionFieldItem();
                targetRegionItem.setSystemName(targetSystemName);
                targetRegionItem.setObjectName(targetObjName);
                targetRegionItem.setAttributeName(targetColName);

                regionFieldMapping.put(targetRegionItem, sourceRegionItem);
                map.setRegionFieldMapping(regionFieldMapping);

                if (targetRegionEntity == null) {
                    targetRegionEntity = new RegionEntity();
                    targetRegionEntity.setPath(targetRegionPath);
                    List<String> regionFields = new ArrayList<>();
                    targetRegionEntity.setMain(false);
                    targetRegionEntity.setColumns(regionFields);
                    targetRegion.put(targetObjName, targetRegionEntity);
                }
                targetRegionEntity.getColumns().add(targetColName);

            }

            // set main region flag
            sourceRegions.get(mainRegion).setMain(true);
            map.setSourceRegions(sourceRegions);
            map.setTargetRegion(targetRegion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    // todo check by space
    @Override
    public HashMap<String, InMemoryTable> getInputTablesBySpace(DomdConfig domdConfig) {
        String tempFilePath = PathUtil.getAppConfigPath() + KW.PATH_SP + FoldersConf.CFG_EXCEL + KW.PATH_SP + domdConfig.getExcelTemplateName();
        System.out.println("Template File: " + tempFilePath);

        File file = new File(tempFilePath);
        HashMap<String, InMemoryTable> inputTables = new HashMap<>(16);
        try (Workbook book = new XSSFWorkbook(new FileInputStream(file))) {
            Sheet sheet = book.getSheetAt(domdConfig.getInputSheetIndex());
            String tableName = "";
            int rowCount = 0;
            InMemoryTable inMemoryTable = null;
            List<String> colValues = null;
            List<String> colHeads = null;
            int test = sheet.getLastRowNum();
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                int colIndex = 0;

                // judge table name
                String col1 = formatter.formatCellValue(row.getCell(0));
                String col2 = formatter.formatCellValue(row.getCell(1));
                //System.out.println("col1: " + col1 + ", col2: " + col2);
                if (col2.trim().equals("")) {
                    tableName = col1;
                    System.out.println("table name: " + tableName);
                    rowCount = 0;
                    inMemoryTable = new InMemoryTable();
                    inMemoryTable.setTableName(tableName);
                    inMemoryTable.setTableColumns(new ArrayList<>());

                    inputTables.put(tableName, inMemoryTable);
                    colHeads = new ArrayList<>();
                }

                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                HashMap<String, String> tableData = null;
                while (true) {
                    String cellValue = formatter.formatCellValue(row.getCell(colIndex));
                    if (cellValue.trim().equals("")) {
                        break;
                    }

                    List<HashMap<String, String>> tableColumns = inMemoryTable.getTableColumns();
                    // judge table head
                    if (rowCount == 1) {
                        colHeads.add(cellValue);
                    } else {
                        if (colIndex == 0) {
                            tableData = new HashMap<>(16);
                            tableColumns.add(tableData);
                        }

                        String colHead = colHeads.get(colIndex);
                        tableData.put(colHead, cellValue);
                    }

                    colIndex++;
                }

                rowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputTables;
    }

    // todo
    @Override
    public HashMap<String, InMemoryTable> getInputTables(DomdConfig domdConfig) {
        String tempFilePath = PathUtil.getAppConfigPath() + KW.PATH_SP + FoldersConf.CFG_EXCEL + KW.PATH_SP + domdConfig.getExcelTemplateName();
        System.out.println("Template File: " + tempFilePath);
//        String workingDir = System.getProperty("user.dir");
//        String tempFilePath = workingDir + "/src/main/resources/config/excel";
//        File file = new File(tempFilePath);
//        String[] fileList = file.list();
//        //String regexExcel = "*.xl*";
//
//        String excelFile = null;
//        for(String filename: fileList){
//            System.out.println("Template File: " + filename);
//        }
//        tempFilePath = tempFilePath+excelFile;
//        System.out.println("Template File: " + tempFilePath);
        File file = new File(tempFilePath);

        HashMap<String, InMemoryTable> inputTables = new HashMap<>(16);
        try (
                Workbook book = new XSSFWorkbook(new FileInputStream(file))) {
            Sheet sheet = book.getSheet("Scenarios");
            if (sheet == null)
                sheet = book.getSheet("Input Data");

            String tableName = "";
            int rowCount = 0;
            InMemoryTable inMemoryTable = null;
            List<String> colValues = null;
            List<String> colHeads = null;
            //the row index for single table
            int tableRowIndex = 0;
            boolean newTable = false;
            int tableColNum = 0;
            int beginCol = 0;

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (isRowEmpty(row) && !isRowEmpty(sheet.getRow(i + 1))) {
                    tableRowIndex = 0;
                    newTable = true;
                    tableColNum = 0;
                    beginCol = 0;
                    continue;
                }
                if (isRowEmpty(row) && isRowEmpty(sheet.getRow(i + 1))) {
                    newTable = false;
                    continue;
                }

                if (!newTable) {
                    continue;
                }

                //table name
                if (tableRowIndex == 0) {
                    int k = 0;
                    while (tableName.equals("")) {
                        tableName = formatter.formatCellValue(row.getCell(k));
                        k++;
                    }
                    tableName = getTableName(tableName);
                    inMemoryTable = new InMemoryTable();
                    inMemoryTable.setTableName(tableName);
                    inMemoryTable.setTableColumns(new ArrayList<>());

                    inputTables.put(tableName, inMemoryTable);
                    colHeads = new ArrayList<>();
                    //if tableRowIndex == 1: column
                    tableName = "";
                    tableRowIndex++;
                } else {
                    if (tableRowIndex == 1) {
                        //column list
                        boolean begin = false;
                        int t = 0;
                        while (true) {
                            String colName = formatter.formatCellValue(row.getCell(t));
                            if (begin && colName.equals("")) {
                                break;
                            } else if (!begin && colName.equals("")) {
                                t++;
                                beginCol++;
                                continue;
                            } else {
                                begin = true;
                                colHeads.add(colName);
                                t++;
                                tableColNum++;
                            }
                        }
                        tableRowIndex++;

                    } else {
                        // data
                        List<HashMap<String, String>> tableColumns = inMemoryTable.getTableColumns();
                        HashMap<String, String> tableData = null;
                        tableData = new HashMap<>(16);
                        tableColumns.add(tableData);
                        boolean begin = false;
                        for (int t = beginCol; t < beginCol + tableColNum; t++) {
                            String colName = formatter.formatCellValue(row.getCell(t));
                            tableData.put(colHeads.get(t - beginCol), colName);
                        }
                        tableRowIndex++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputTables;
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }

    private String getTableName(String original) {
        String res = original.substring(original.indexOf(':') == -1 ? 0 : original.indexOf(':') + 1).trim();
        return res;
    }
}
