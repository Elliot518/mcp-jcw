package com.mcp.infrastructure.common.util.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * @author : KG
 * description: csv文件读写
 * create date: 2:54 PM 2020/1/2
 * modified by:
 */
@Slf4j
public class CsvUtils {
    /**
     * description : 写csv文件 传参数文件名 路径 csv文件表头 需要写入的数据
     */
    public static File writeCsvFile(String fileName, String path, String[] fileHeaders, List<List<String>> list) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            csvFile = new File(path + fileName + ".csv");
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),
                    1024);
            // 初始化 CSVPrinter
            csvPrinter = new CSVPrinter(csvFileOutputStream, csvFileFormat);
            List<String> ls = null;
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ls = new ArrayList<>();
                    ls = list.get(i);
                    for (int j = 0; j < ls.size(); j++) {
                        csvPrinter.print(ls.get(j));
                    }
                    csvPrinter.println();// 换行
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("csv文件写入异常");
        } finally {
            try {
                csvFileOutputStream.flush();
                csvFileOutputStream.close();
                csvPrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * description : 读取csv文件 传参数 文件 表头 从第几行开始
     */
    public static List<CSVRecord> readCsvFile(String filePath, String[] fileHeaders) {
        BufferedReader br = null;
        CSVParser csvFileParser = null;
        List list = null;
        // 创建CSVFormat（header mapping）
//        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders).withSkipHeaderRecord();
        try {
            File file = new File(filePath);
            // 初始化FileReader object(解决乱码问题)
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));

            // 初始化 CSVParser object
            csvFileParser = new CSVParser(br, csvFileFormat);

            // CSV文件records
            return csvFileParser.getRecords();

//            List data = new ArrayList();
//            list = new ArrayList();
//            for (int i = num; i < csvRecords.size(); i++) {
//                CSVRecord record = csvRecords.get(i);
//                for (int j = 0; j < fileHeaders.length; j++) {
//                    data.add(record.get(fileHeaders[j]));
//                }
//                list.add(data);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("csv文件读取异常");
        } finally {
            try {
                br.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
