package org.genesis.toolbox.beans.repository.impl;

import org.genesis.toolbox.beans.domains.action.DomdHelper;
import org.genesis.toolbox.beans.domains.action.InMemoryDbHelper;
import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.region.RegionEntity;
import org.genesis.toolbox.beans.domains.region.RegionFieldItem;
import org.genesis.toolbox.beans.repository.DataInspector;
import org.genesis.toolbox.beans.service.impl.ADFRestServiceImpl;
import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.fundamental.TwoTuple;
//import org.genesis.toolbox.util.AdfUtil;
import org.genesis.toolbox.util.Debugger;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DataInspectorImpl
 * @Package org.genesis.toolbox.beans.repository.impl
 * @Description: data inspector implementation
 * @date 2018/7/31 10:25
 */
@Repository
public class DataInspectorImpl implements DataInspector {

    //    @Autowired
//    private ExcelTemplateButler excelTemplateButler;
//
//
//    @Autowired
    private ADFRestServiceImpl adfRestServiceImpl = new ADFRestServiceImpl();

    // protected static ADFService adfService;

    @Override
    public Boolean validateInputData(EntityMap entityMap, HashMap<String, InMemoryTable> inputTables, MyTextPane textPane) {

        Boolean validateResult = true;

        Debugger.printLine(textPane);
        Debugger.printHead(textPane, "##############################", "#FF4500");
        Debugger.printHead(textPane, "##&nbsp;&nbsp;3)&nbsp; Data Quality Report &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;##", "#FF4500");
        Debugger.printHead(textPane, "##############################", "#FF4500");
        Debugger.printSeparator(textPane);

        Debugger.printSubHead(textPane, "-- 3-1&gt; Region Names & columns Check --", "#008000");
        Debugger.printLine(textPane);
        for (Map.Entry<String, InMemoryTable> memTable : inputTables.entrySet()) {
            String tableName = memTable.getKey();
            HashMap<String, RegionEntity> souceRegions = entityMap.getSourceRegions();


            /**
             * Step 1: check region name exists in entity map
             */
            if (souceRegions.containsKey(tableName)) {
                // table exists
                Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + tableName + " exists", 0, "#008000");
            } else {
                Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + tableName + " does not exist in entity map!!", 0, "#DC143C");
                validateResult = false;
            }
        }

        Debugger.printLine(textPane);

        /**
         * Step 2: Region Field Mapping
         */
        Debugger.printSubHead(textPane, "-- 3-2&gt; Region Field Mapping --", "#008000");
        Debugger.printLine(textPane);
        HashMap<RegionFieldItem, RegionFieldItem> regionFieldMapping = entityMap.getRegionFieldMapping();
        for (Map.Entry<RegionFieldItem, RegionFieldItem> item : regionFieldMapping.entrySet()) {
            RegionFieldItem targetItem = item.getKey();
            RegionFieldItem sourceItem = item.getValue();
            String mappingLine = "&nbsp;&nbsp;" + targetItem.getObjectName() + "." + targetItem.getAttributeName() + " &lt;---- " + sourceItem.getObjectName() + "." + sourceItem.getAttributeName();

            TwoTuple<Boolean, String> result = DomdHelper.isRegionItemExists(sourceItem, inputTables);
            if (result.first) {
                Debugger.printTwoColumns(textPane, mappingLine, result.second, 0, "#006CBC", "#008000");
            } else {
                Debugger.printTwoColumns(textPane, mappingLine, result.second, 0, "#006CBC", "#DC143C");
                validateResult = false;
            }
        }

        Debugger.printLine(textPane);

        /**
         * Step 3: Output without rule applied
         */
        Debugger.printSubHead(textPane, "-- 3-3&gt; Output without rule applied --", "#008000");
        Debugger.printLine(textPane);
        if (!validateResult) {
            String failResult = "&nbsp;&nbsp;Can not generate output, fix errors first!!";
            Debugger.printBanner(textPane, failResult, 0, "#DC143C");
        } else {
            // generating output
            InMemoryTable outputTable = new InMemoryTable();
            Map.Entry<String, RegionEntity> targetEntry = entityMap.getTargetRegion().entrySet().iterator().next();
            String targetRegionName = targetEntry.getKey();
            outputTable.setTableName(targetRegionName);
            List<HashMap<String, String>> targetTableData = new ArrayList<>();
            outputTable.setTableColumns(targetTableData);

            HashMap<String, String> targetRow = new HashMap<>();
            addTargetTableRow(regionFieldMapping, inputTables, targetTableData, targetRow);

            DomdHelper.printInMemoryTable(outputTable, textPane);

        }

        //todo
        /**
         * Step 4:  check region on dev
         */
        /*Debugger.printSubHead(textPane, "-- 3-4&gt; check region on dev --", "#008000");
        Debugger.printLine(textPane);

        // using RestAPI to valid check data
        try {
            //AdfUtil.connect();
            //get token
            String token = adfRestServiceImpl.login();
            if (token.equals("0")) {
                Debugger.printHtml(textPane, "&nbsp;&nbsp;- username and password not correct when access dev", 0, "#DC143C");
                return null;
            }
            if (token.equals("-1")) {
                Debugger.printHtml(textPane, "&nbsp;&nbsp;- please check the service url", 0, "#DC143C");
                return null;
            }
            for (Map.Entry<String, InMemoryTable> inputEntry : inputTables.entrySet()) {
                String regionsPath = inputEntry.getKey().toString().trim();
                //todo get region info by path  regioninfo:{validpath, count}
                String validRegionPath = adfRestServiceImpl.getValidPath(regionsPath, token);
                int recordCount = adfRestServiceImpl.count(validRegionPath, token);
                //todo using api to get the valid path
                if (recordCount == -1) {
                    Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " does not exist in dev!!", 0, "#DC143C");
                    continue;
                } else if (recordCount == 0) {
                    Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " no data in dev!!", 0, "#DC143C");
                    continue;
                } else if (recordCount == -2) {
                    Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " not authorized for this region!!", 0, "#DC143C");
                    continue;
                } else if (recordCount == -3) {
                    Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " unknown error!!", 0, "#DC143C");
                    continue;
                } else {
                    Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " exists in dev", 0, "#008000");
                    //todo get column info -> devRecordSet
                    //get column

                    if (inputEntry.getValue().getTableColumns() == null) {
                        Debugger.printHtml(textPane, "&nbsp;&nbsp;- region: " + validRegionPath + " no data loaded from DOMD!!", 0, "#DC143C");
                        continue;
                    }
                    //todo if inputEntry is empty
                    Set<String> devRecordSet = adfRestServiceImpl.getColumn(validRegionPath, token);
                    for (Map.Entry<String, String> recordEntry : inputEntry.getValue().getTableColumns().get(0).entrySet()) {
                        if (!devRecordSet.contains(recordEntry.getKey())) {
                            Debugger.printHtml(textPane, "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- field: " + recordEntry.getKey() + " not exists in dev", 0, "#DC143C");
                        }
                    }
                }
            }
        } catch (Exception e) {
            Debugger.printHtml(textPane, "&nbsp;&nbsp" + " some error happens!! Please verify the format of the DOMD and check the status of VPN ", 0, "#DC143C");
        }*/

        return validateResult;
    }

    private void addTargetTableRow(HashMap<RegionFieldItem, RegionFieldItem> regionFieldMapping, HashMap<String,
            InMemoryTable> inputTables, List<HashMap<String, String>> targetTableData, HashMap<String, String> targetRow) {
        InMemoryTable sourceTable = null;
        for (Map.Entry<RegionFieldItem, RegionFieldItem> item : regionFieldMapping.entrySet()) {
            RegionFieldItem targetItem = item.getKey();
            RegionFieldItem sourceItem = item.getValue();
            String targetAttribute = targetItem.getAttributeName();
            String sourceAttribute = sourceItem.getAttributeName();

            if (sourceTable == null || !sourceTable.getTableName().equals(sourceItem.getObjectName())) {
                sourceTable = InMemoryDbHelper.findTableByName(inputTables, sourceItem.getObjectName());
            }

            List<HashMap<String, String>> sourceTableData = sourceTable.getTableColumns();
            for (HashMap<String, String> sourceRow : sourceTableData) {
                for (Map.Entry<String, String> sourceCell : sourceRow.entrySet()) {
                    if (sourceCell.getKey().equals(sourceAttribute)) {
                        targetRow.put(targetAttribute, sourceCell.getValue());
                    }
                }
            }

            targetTableData.add(targetRow);
        }
    }

//
//    private PageResults<String> getFirstPage(String regionPath) {
//        // prefix of the region
//        // edm,omp, plan
//        // three thread
//        PageResults<String> res = null;
//        res = AdfUtil.query(regionPath);
//        return res;
//    }
//
//    //-1: region not exists
//    //0: no data
//    //>0: data exists
//    private List<Object> getRegionInfo(String regionPath) {
//        // prefix of the region
//        // edm,omp, planxx
//        List<Object> res = new LinkedList<Object>();
//        long recordNum = 0;
//        String validRegionPath = null;
//        if (regionPath.indexOf('/') == -1) {
//            try {
//                recordNum = AdfUtil.count("/omp/" + regionPath);
//                validRegionPath = "/omp/" + regionPath;
//            } catch (ServerOperationException e) {
//                try {
//                    recordNum = AdfUtil.count("/plan/" + regionPath);
//                    validRegionPath = "/plan/" + regionPath;
//                } catch (ServerOperationException e1) {
//                    try {
//                        recordNum = AdfUtil.count("/edm/" + regionPath);
//                        validRegionPath = "/edm/" + regionPath;
//                    } catch (ServerOperationException e2) {
//                        recordNum = -1;
//                    }
//                }
//            } finally {
//                res.add(recordNum);
//                res.add(validRegionPath);
//                return res;
//            }
//        } else {
//            try {
//                recordNum = AdfUtil.count(regionPath);
//                validRegionPath = regionPath;
//            } catch (ServerOperationException e) {
//                recordNum = -1;
//            } finally {
//                res.add(recordNum);
//                res.add(validRegionPath);
//                return res;
//            }
//
//        }
//
    }





