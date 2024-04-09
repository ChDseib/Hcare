package com.igeek.university.utils;

import com.igeek.university.entity.Ranking;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class addRankingExcel {
//    public static void main(String[] args) {
//        getData();
//    }
//    public static List<Ranking> getData() {
//        List<Ranking> list1 = getAllByExcel("2020.xlsx");
////        List<Ranking> list2 = getAllByExcel("wu.xlsx");
////        List<Ranking> list3 = getAllByExcel("2020wen.xlsx");
////        List<Ranking> list4 = getAllByExcel("2020li.xlsx");
////
////
////        list1.forEach(i ->{
////            i.setFname("历史类");
////            i.setYear(2021);
////        });
////        list2.forEach(i ->{
////            i.setFname("物理类");
////            i.setYear(2021);
////        });
////        list1.addAll(list2);
////        list1.forEach(System.out::println);
////        return list1;
//
//        list1.forEach(System.out::println);
//        return list1;
//    }

    public static List<Ranking> getAllByExcel(InputStream in,String fileName) {
        Workbook wb = readExcel(in,fileName);
        Sheet sheet = null;
        Row row = null;
        List<Ranking> list = null;
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
                    cellAppName.setCellType(CellType.STRING);
                    String score = cellAppName.getRichStringCellValue().getString();
                    Cell cellAppName1 = row.getCell(1);
                    cellAppName1.setCellType(CellType.STRING);
                    if(cellAppName1 == null){
                        return list;
                    }
                    String same_num = cellAppName1.getRichStringCellValue().getString();
                    Cell cellAppName2 = row.getCell(2);
                    cellAppName2.setCellType(CellType.STRING);
                    String ranking = cellAppName2.getRichStringCellValue().getString();
                    Cell cellAppName3 = row.getCell(3);
                    cellAppName3.setCellType(CellType.STRING);
                    String fname = cellAppName3.getRichStringCellValue().getString();
                    Cell cellAppName4 = row.getCell(4);
                    cellAppName4.setCellType(CellType.STRING);
                    String year = cellAppName4.getRichStringCellValue().getString();
                    Integer year1 = Integer.parseInt(year);
                    Ranking rank = new Ranking();
                    rank.setScore(score);
                    rank.setSame_num(same_num);
                    rank.setRanking(ranking);
                    rank.setFname(fname);
                    rank.setYear(year1);
                    list.add(rank);
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
