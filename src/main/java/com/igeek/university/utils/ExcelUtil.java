package com.igeek.university.utils;

import com.igeek.university.entity.SpecialtyGroup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
//    public static void main(String[] args) {
//        getData();
//    }
//    public static List<SpecialtyGroup> getData() {
//        List<SpecialtyGroup> list = getAllByExcel1("aa.xlsx");
//        list.forEach(System.out::println);
//        return list;
//    }

    public static List<SpecialtyGroup> getAllByExcel(InputStream in,String fileName) {
        Workbook wb = readExcel(in,fileName);
        Sheet sheet = null;
        Row row = null;
        List<SpecialtyGroup> list = null;
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
//            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rowNum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    // 读取表格中数据，第一列
                    Cell cellAppName = row.getCell(0);

                    if(cellAppName == null){
                        return list;
                    }
                    cellAppName.setCellType(CellType.STRING);
                    Cell cellAppName1 = row.getCell(1);
                    String code = cellAppName.getRichStringCellValue().getString();
                    Cell cellAppName2 = row.getCell(2);
                    String groupCode = cellAppName2.getRichStringCellValue().getString();
                    Cell cellAppName3 = row.getCell(3);
                    String groupName = cellAppName3.getRichStringCellValue().getString();
                    Cell cellAppName4 = row.getCell(4);
                    cellAppName4.setCellType(CellType.STRING);
                    String groupYear = cellAppName4.getRichStringCellValue().getString();
                    //System.out.println(groupName);
                    SpecialtyGroup sg = new SpecialtyGroup();
                    sg.setCode(code);
                    sg.setGroup_code(groupCode);
                    sg.setGroup_name(groupName);
                    sg.setYear(groupYear);
                    list.add(sg);
                } else {
                    break;
                }
            }
        }
        return list;
    }


    //读取excel
    public static Workbook readExcel(InputStream inStr,String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.indexOf('.'));
        try {
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(inStr);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(inStr);
            } else {
                return wb = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

}