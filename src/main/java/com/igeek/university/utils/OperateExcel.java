package com.igeek.university.utils;

import com.igeek.university.entity.University;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OperateExcel {
    public static void main(String[] args) {
        getData();
    }
    public static List<University> getData() {
        List<University> list1 = getAllByExcel1("a.xls");
        List<University> list2 = getAllByExcel2("b.xls");
        List<University> list3 = getAllByExcel3("c.xls");

        list2.forEach(k ->{
            k.setFeature("普通高校");
            k.setLevel("本科");
            list1.forEach(i ->{
                if(k.getName().equals(i.getName())){
                    k.setCode(i.getCode());
                }
            });
            list3.forEach(j -> {
                if(k.getName().equals(j.getName())){
                    k.setFeature(j.getFeature());
                }
            });
        });
        return list2;
    }

    public static List<University> getAllByExcel1(String file) {
            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            List<University> list = null;
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
                        Cell cellAppName = row.getCell(0);

                        if(cellAppName == null){
                            return list;
                        }
                        cellAppName.setCellType(CellType.STRING);
                        String code = cellAppName.getRichStringCellValue().getString();
                        Cell cellAppName1 = row.getCell(1);
                        if(cellAppName1 == null){
                            return list;
                        }
                        //取出院校名称
                        String name = cellAppName1.getRichStringCellValue().getString();
                        University uni = new University();
                        uni.setCode(code);
                        uni.setName(name);
                        list.add(uni);
                    } else {
                        break;
                    }
                }
            }
            return list;
        }

    public static List<University> getAllByExcel2(String file) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<University> list = null;
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
                    Cell cellAppName = row.getCell(0);
                    if(cellAppName == null){
                        return list;
                    }
                    String name = cellAppName.getRichStringCellValue().getString();
                    Cell cellAppName1 = row.getCell(1);
                    String address = cellAppName1.getRichStringCellValue().getString();
                    Cell cellAppName2 = row.getCell(2);
                    String remark = cellAppName2.getRichStringCellValue().getString();
                    if(remark.equals("")||remark==null){
                        remark="公办";
                    }
                    University uni = new University();
                    uni.setName(name);
                    uni.setAddress(address);
                    uni.setRemark(remark);
                    list.add(uni);
                } else {
                    break;
                }
            }
        }
        return list;
    }

    public static List<University> getAllByExcel3(String file) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<University> list = null;
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
                    Cell cellAppName = row.getCell(0);
                    if(cellAppName == null){
                        return list;
                    }
                    String[] name1 = cellAppName.getRichStringCellValue().getString().split(" ");
                    String name = name1[0];

                    Cell cellAppName1 = row.getCell(1);
                    cellAppName1.setCellType(CellType.STRING);
                    String feature = cellAppName1.getRichStringCellValue().getString();
                    University uni = new University();
                    uni.setName(name);
                    uni.setFeature(feature);
                    list.add(uni);
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
