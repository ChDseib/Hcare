package com.igeek.university.utils;

import com.igeek.university.entity.Specialty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpecialtyUtil {
//    public static void main(String[] args) {
//        getData();
//    }
//    public static List<SpecialtyGroup> getData() {
//        List<SpecialtyGroup> list = getAllByExcel1("aa.xlsx");
//        list.forEach(System.out::println);
//        return list;
//    }

    public static List<Specialty> getAllByExcel(InputStream in,String fileName) {
        Workbook wb = readExcel(in,fileName);
        Sheet sheet = null;
        Row row = null;
        List<Specialty> list = null;
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
                    // 读取表格中数据，院校专业组代码
                    Cell cellAppName = row.getCell(1);
                    if(cellAppName == null){
                        break;
                    }
                    cellAppName.setCellType(CellType.STRING);
                    String groupcode = cellAppName.getRichStringCellValue().getString();

                    //院校专业组名称
                    Cell cellAppName1 = row.getCell(2);
                    if(cellAppName1 == null){
                        break;
                    }
                    cellAppName1.setCellType(CellType.STRING);
                    String name = cellAppName1.getRichStringCellValue().getString();

                    //院校专业代号
                    Cell cellAppName2 = row.getCell(4);
                    if(cellAppName2 == null){
                        break;
                    }
                    cellAppName2.setCellType(CellType.STRING);
                    String scode = cellAppName2.getRichStringCellValue().getString();

                    //院校专业名称
                    Cell cellAppName3 = row.getCell(5);
                    if(cellAppName3 == null){
                        break;
                    }
                    String sname = cellAppName3.getRichStringCellValue().getString();

                    //计划数
                    Cell cellAppName4 = row.getCell(6);
                    if(cellAppName4 == null){
                        break;
                    }
                    cellAppName4.setCellType(CellType.STRING);
                    String planNum =cellAppName4.getRichStringCellValue().toString();

                    //录取分数
                    Cell cellAppName5 = row.getCell(7);
                    if(cellAppName5 == null){
                        break;
                    }
                    cellAppName5.setCellType(CellType.STRING);
                    String score =cellAppName5.getRichStringCellValue().toString();

                    //排名
                    Cell cellAppName6 = row.getCell(8);
                    if(cellAppName6 == null){
                        break;
                    }
                    cellAppName6.setCellType(CellType.STRING);
                    String ranking =cellAppName6.getRichStringCellValue().toString();

                    //学制
                    Cell cellAppName7 = row.getCell(9);
                    if(cellAppName7 == null){
                        break;
                    }
                    String length = cellAppName7.getRichStringCellValue().getString();

                    //学费
                    Cell cellAppName8 = row.getCell(10);
                    if(cellAppName8 == null){
                        break;
                    }
                    cellAppName8.setCellType(CellType.STRING);
                    String money =cellAppName8.getRichStringCellValue().toString();


                    Specialty spe = new Specialty();
                    spe.setGroup_code(groupcode);
                    spe.setName(name);
                    spe.setScode(scode);
                    spe.setSname(sname);
                    spe.setPlan_num(planNum);
                    spe.setScore(score);
                    spe.setRanking(ranking);
                    spe.setLength(length);
                    spe.setMoney(money);
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    spe.setYear(year-1);
                    list.add(spe);
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