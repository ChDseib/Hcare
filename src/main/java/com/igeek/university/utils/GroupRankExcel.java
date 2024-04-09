package com.igeek.university.utils;

import com.igeek.university.entity.Category;
import com.igeek.university.entity.GroupRank;
import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Specialty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GroupRankExcel {

  //  public static void main(String[] args) {
    //    getData();
    //}

    //public static List<GroupRank> getData() {
      //  List<GroupRank> list = getAllByExcel("grouprank.xlsx");
        //list.forEach(System.out::println);
        //return list;
    //}



    public static List<GroupRank> getAllByExcel(InputStream in, String fileName) {
        Workbook wb = readExcel(in,fileName);
        Sheet sheet = null;
        Row row = null;
        List<GroupRank> list = null;
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
                    //计划数
                    Cell cellAppName1 = row.getCell(0);
                    if(cellAppName1 == null){
                        break;
                    }
                    cellAppName1.setCellType(CellType.STRING);
                    String getNum =cellAppName1.getRichStringCellValue().toString();

                    // 读取表格中数据，院校专业组代码
                    Cell cellAppName = row.getCell(1);
                    if(cellAppName == null){
                        break;
                    }
                    cellAppName.setCellType(CellType.STRING);
                    String groupcode = cellAppName.getRichStringCellValue().getString();



                    //录取分数
                    Cell cellAppName2 = row.getCell(2);
                    if(cellAppName2 == null){
                        break;
                    }
                    cellAppName2.setCellType(CellType.STRING);
                    String maxscore =cellAppName2.getRichStringCellValue().toString();

                    //录取分数
                    Cell cellAppName3 = row.getCell(3);
                    if(cellAppName3 == null){
                        break;
                    }
                    cellAppName3.setCellType(CellType.STRING);
                    String minscore =cellAppName3.getRichStringCellValue().toString();

                    //排名
                    Cell cellAppName4 = row.getCell(4);
                    if(cellAppName4 == null){
                        break;
                    }
                    cellAppName4.setCellType(CellType.STRING);
                    String ranking =cellAppName4.getRichStringCellValue().toString();

                  //年份
                    Cell cellAppName5 = row.getCell(5);
                    cellAppName5.setCellType(CellType.STRING);
                    String year = cellAppName5.getRichStringCellValue().getString();
                    Integer year1 = Integer.parseInt(year);


                    GroupRank groupRank = new GroupRank();
                    groupRank.setGet_num(getNum);
                    groupRank.setGroup_code(groupcode);

                    groupRank.setMax_score(maxscore);
                    groupRank.setMin_score(minscore);
                    groupRank.setRanking(ranking);
                    groupRank.setYear(year1);


                    list.add(groupRank);
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
