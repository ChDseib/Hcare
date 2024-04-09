package com.igeek.university.utils;

import com.igeek.university.entity.WordDto;
import com.igeek.university.entity.WordSpe;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>生成word工具类<br>*/
public class WordUtil {

    private Configuration configuration = null;

    //本地环境
    /**模板文件存放的目录*/
//    private static final String  baseDir = "./Word/";
//    /**模板文件名称*/
//    private static final String  templateFile = "Confirm.ftl";
//    /**word生成的输出目录*/
//    private static final String  outputDir = "./Word/";

    //Linux环境
    private static final String  baseDir = "/root/Word/";
    private static final String  templateFile = "Confirm.ftl";
    private static final String  outputDir = "/root/Word/";

    public WordUtil(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) {
        WordUtil test = new WordUtil();
        //test.createWord();
    }

    /**<p>转换成word<br>*/
    public void createWord(List<WordDto> wordDtolist,List<WordSpe> wordSpelist){
        //构造参数
        Map<String,Object> dataMap=getData(wordDtolist,wordSpelist);

        configuration.setClassForTemplateLoading(this.getClass(), "");//模板文件所在路径
        Template t=null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(baseDir));
            t = configuration.getTemplate(templateFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File(outputDir+"模拟报考.doc"); //导出文件
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out); //将填充数据填入模板文件并输出到目标文件
            System.out.println("生成成功...");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>初始化数据map <br>
     * 		封装数据的map
     */
    private Map<String,Object> getData(List<WordDto> wordDtolist,List<WordSpe> wordSpelist) {
        Map<String,Object> dataMap=new HashMap<String,Object>();
        dataMap.put("name", "");
        dataMap.put("student", "");
        dataMap.put("city", "");
        dataMap.put("country", "");
        for (int i = 1; i <=40 ; i++) {
            dataMap.put(("groupCode"+i).toString(), wordDtolist.get(i-1).getGroup_code());
            dataMap.put(("groupName"+i).toString(), wordDtolist.get(i-1).getGroup_name());
        }
        for (int i = 1; i <=240 ; i++) {
            dataMap.put(("scode"+i).toString(), wordSpelist.get(i-1).getScode());
            dataMap.put(("sname"+i).toString(), wordSpelist.get(i-1).getSname());
        }
        return dataMap;
    }
}