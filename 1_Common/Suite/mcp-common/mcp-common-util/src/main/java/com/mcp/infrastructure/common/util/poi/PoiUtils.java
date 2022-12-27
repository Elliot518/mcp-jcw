package com.mcp.infrastructure.common.util.poi;

import com.mcp.infrastructure.common.util.time.CalendarUtils;
import com.mcp.infrastructure.common.util.time.DateTimeUtils;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : KG
 * description:
 * create date: 9:06 PM 2020/1/6
 * modified by:
 */
public class PoiUtils {
    private static DataFormatter formatter = new DataFormatter();

    public static Map<String, Integer> getColumnMap(Sheet sheet, int headIndex) {
        Map<String, Integer> map = new HashMap<>(16);

        // get head row
        Row row = sheet.getRow(headIndex);
        // get the first column index for a row
        short minColIx = row.getFirstCellNum();
        // get the last column index for a row
        short maxColIx = row.getLastCellNum();
        // loop from first to last index
        for (short colIx = minColIx; colIx < maxColIx; colIx++) {
            Cell cell = row.getCell(colIx);
            map.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        return map;
    }

    public static String getCellStringByColName(Row row, Map<String, Integer> columnsMap, String colName) {
        Integer colIdx = columnsMap.get(colName);
        if (colIdx == null) {
            return "";
        }

        return formatter.formatCellValue(row.getCell(colIdx));
    }

    public static LocalDateTime getCellDateByColName(Row row, Map<String, Integer> columnsMap, String colName) {
        String strDate = getCellStringByColName(row, columnsMap, colName);

        return DateTimeUtils.strToLocalDateTime(strDate);
    }

    public static LocalDateTime getCellDateByColName2(Row row, Map<String, Integer> columnsMap, String colName) {
        Integer colIdx = columnsMap.get(colName);
        if (colIdx == null) {
            return null;
        }

        String strCellDate = formatter.formatCellValue(row.getCell(colIdx));
        if ("".equals(strCellDate)) {
            return null;
        }

        String dataFormatString = row.getCell(colIdx).getCellStyle().getDataFormatString();
        System.out.println("----- data format string: " + dataFormatString);


        if ("yyyy/mm;@".equals(dataFormatString) || "m/d/yy".equals(dataFormatString)
                || "yy/m/d".equals(dataFormatString) || "mm/dd/yy".equals(dataFormatString)
                || "dd-mmm-yy".equals(dataFormatString) || "yyyy/m/d".equals(dataFormatString)) {
            String strDate = new SimpleDateFormat("yyyy/MM/dd").format(row.getCell(colIdx).getDateCellValue());

            DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.parse(strDate, timeDtf);

            return localDate.atStartOfDay();
        }

        CellType cellType = row.getCell(colIdx).getCellTypeEnum();
        if (cellType == CellType.STRING) {
            return CalendarUtils.convertStringDateToLocalDateTime(strCellDate);
        }

        return null;
    }

    public static Integer getCellIntegerByColName(Row row, Map<String, Integer> columnsMap, String colName) {
        String strNum = getCellStringByColName(row, columnsMap, colName);

        if ("".equals(strNum)) {
            return 0;
        }

        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * data not correct
     */
    public static Integer getCellIntegerByColName2(Row row, Map<String, Integer> columnsMap, String colName) {
        Integer colIdx = columnsMap.get(colName);
        if (colIdx == null) {
            return 0;
        }

        if (row.getCell(colIdx).getCellTypeEnum().equals(CellType.NUMERIC)) {
            double num = row.getCell(colIdx).getNumericCellValue();

            return new Double(num).intValue();
        }

        return 0;
    }

    public static BigDecimal getCellDecimalByColName(Row row, Map<String, Integer> columnsMap, String colName) {
        Integer colIdx = columnsMap.get(colName);
        if (colIdx == null) {
            return BigDecimal.ZERO;
        }

        String strNum = formatter.formatCellValue(row.getCell(colIdx));
        if ("".equals(strNum)) {
            return BigDecimal.ZERO;
        }

        if (row.getCell(colIdx).getCellTypeEnum().equals(CellType.NUMERIC)) {
            double num = row.getCell(colIdx).getNumericCellValue();

            return new BigDecimal(num);
        } else {
            return new BigDecimal(strNum);
        }
    }
}
