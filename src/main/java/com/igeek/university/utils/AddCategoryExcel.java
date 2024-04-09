package com.igeek.university.utils;

import com.igeek.university.entity.Category;
import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Specialty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryExcel {
    public static void main(String[] args) {
        getData();
    }
    public static List<Category> getData() {
        List<Category> list = getAllByExcel("category.xls");
        list.forEach(System.out::println);
        return list;
    }

    public static List<Category> getAllByExcel(String file) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Category> list = null;
        wb = readExcel(file);
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
                    Cell cellTypeP = row.getCell(0);
                    cellTypeP.setCellType(CellType.STRING);
                    String typeP = cellTypeP.getRichStringCellValue().getString();
                    Cell cellType = row.getCell(1);
                    cellType.setCellType(CellType.STRING);
                    String type = cellType.getRichStringCellValue().getString();
                    Cell cellName = row.getCell(2);
                    cellName.setCellType(CellType.STRING);
                    String name = cellName.getRichStringCellValue().getString();

                    Category category = new Category();
                    category.setCategoryPid(typeP);
                    category.setCategoryId(type);
                    category.setName(name);

                    list.add(category);
                } else {
                    break;
                }
            }
        }
        return list;
    }

    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.indexOf('.'));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }
}
