package com.mcp.infrastructure.common.util.poi;

import com.monitorjbl.xlsx.StreamingReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : KG
 * description:
 * create date: 5:14 PM 2020/4/23
 * modified by:
 */
@Slf4j
public class SplitExcelUtils {
    /*主程序启动*/
    public static void main(String[] args) {

        try {
            String originalFilePath = "/Users/jgu55/data/attrition/RV1012/test.xlsx";
            int pageSize = 3000;
            String destFilePath = "/Users/jgu55/Downloads/test";
            boolean isHead = true;
            splitFile(originalFilePath, pageSize, isHead, destFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param originalFile 原文件路径+文件名
     * @param pageSize     每个文件条数
     * @param headFlag     是否带表头
     * @param destFilePath 目标路径(不需要带文件名，例如：D:\\文件夹)
     * @throws Exception
     */
    public static void splitFile(String originalFile, int pageSize, boolean headFlag, String destFilePath) throws Exception {
        Workbook workbook = null;
        InputStream inputStream = null;
        File destFile = new File(destFilePath);
        if (!destFile.exists()) {
            FileUtils.forceMkdir(destFile);
        }

        try {
            File file = new File(originalFile);
            String fileName = FilenameUtils.getBaseName(file.getName());
            inputStream = new FileInputStream(file);
            workbook = StreamingReader.builder()
                    .rowCacheSize(1000)
                    .bufferSize(4096)
                    .open(inputStream);
            //存储表头
            List<String> headData = new ArrayList<>();
            //存储所有数据
            List<List<String>> dataList = new ArrayList<>();
            //行号
            int rowIndex = 0;
            //页码
            int pageIndex = 1;

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row == null) {
                    continue;
                }

                if (headFlag && rowIndex == 0) {
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        headData.add(row.getCell(i).getStringCellValue());
                    }
                } else {
                    List<String> rowData = new ArrayList<>();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        if (row.getCell(i) != null) {
                            rowData.add(row.getCell(i).getStringCellValue().trim());
                        } else {
                            rowData.add("");
                        }
                    }
                    dataList.add(rowData);
                    if (dataList.size() >= pageSize) {
                        int pageStartNum = (pageIndex - 1) * pageSize + 1;
                        String newfileName = genFlieName(destFilePath, fileName, pageStartNum, rowIndex);
                        writeFile(newfileName, dataList, headData);
                        dataList.clear();
                        pageIndex++;
                    }
                }
                rowIndex++;
            }
            if (dataList.size() > 0) {

                int pageStartNum = (pageIndex - 1) * pageSize + 1;
                String newfileName = genFlieName(destFilePath, fileName, pageStartNum, rowIndex);
                writeFile(newfileName, dataList, headData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String genFlieName(String destFilePath, String fileName, int pageStartNum, int rowIndex) {
        return destFilePath + "/" + fileName + "(" + pageStartNum + "_" + rowIndex + ")" + ".xlsx";
    }

    private static XSSFWorkbook getXSSFWorkbook(String filePath) {
        XSSFWorkbook workbook = null;
        BufferedOutputStream outputStream = null;
        try {
            File fileXlsxPath = new File(filePath);
            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            workbook.createSheet("Sheet1");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }

    /**
     * 生成文件
     *
     * @param destFilePath 生成文件路径
     * @param listdata     文件数据
     * @param head         excel 文件表头数据
     */
    private static void writeFile(String destFilePath, List<List<String>> listdata, List<String> head) {
        SXSSFWorkbook sxssfWorkbook = null;
        BufferedOutputStream outputStream = null;
        try {
            int statRow = 0;
            sxssfWorkbook = new SXSSFWorkbook(getXSSFWorkbook(destFilePath), 100);
            SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
            if (head != null && head.size() > 0) {
                SXSSFRow row = sheet.createRow(statRow);
                for (int k = 0; k < head.size(); k++) {
                    String cellValue = head.get(k);
                    row.createCell(k).setCellValue(cellValue);
                }
                statRow = statRow + 1;
            }

            //总条数
            int rowsNum = listdata.size();
            for (int j = 0; j < rowsNum; j++) {
                SXSSFRow row = sheet.createRow(statRow + j);
                List<String> rowData = listdata.get(j);
                //列数
                int colNum = rowData.size();
                for (int k = 0; k < colNum; k++) {
                    String cellValue = rowData.get(k);
                    row.createCell(k).setCellValue(cellValue);
                }
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(destFilePath));
            sxssfWorkbook.write(outputStream);
            outputStream.flush();
            sxssfWorkbook.dispose();// 释放workbook所占用的所有windows资源
            System.out.println("文件分割 " + destFilePath + " 成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
