package com.igeek.university.service.impl;

import com.igeek.university.dao.ICategoryDao;
import com.igeek.university.dao.ISpecialtyDao;
import com.igeek.university.entity.*;
import com.igeek.university.service.IFilterService;
import com.igeek.university.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements IFilterService {
    @Autowired
    ISpecialtyDao specialtyDao;

    @Autowired
    ICategoryDao categoryDao;

    @Override
    public Result query(QueryItem item) {
        Result result = new Result();
        String[] s = item.getTwoType().split(",");
        List<Map> list = new ArrayList<>();
        if (item.getAddress().equals("全国")) {
            list = specialtyDao.queryFilterG(item.getFirstType()
                    , "%" + s[0] + "%", "%" + s[1] + "%"
                    , item.getScore_min(), item.getScore_max());
        } else {
            list = specialtyDao.queryFilter(item.getFirstType()
                    , "%" + s[0] + "%", "%" + s[1] + "%"
                    , item.getAddress(), item.getScore_min()
                    , item.getScore_max());
        }

        List<SpecialtyDto> dtos = new ArrayList<>();
        for (Map map : list) {
            SpecialtyDto dto = new SpecialtyDto();
            dto.setAddress(map.get("ADDRESS").toString());
            dto.setUname(map.get("UNAME").toString());
            dto.setEname(map.get("ENAME").toString());
            dto.setFeature(map.get("FEATURE").toString());
            dto.setRemark(map.get("REMARK").toString());
            dto.setMoney(map.get("MONEY").toString());
            dto.setGroup_name(map.get("GROUP_NAME").toString());
            dto.setLength(map.get("LENGTH").toString());
            dto.setScode(map.get("SCODE").toString());
            String score = map.get("SCORE").toString();
            dto.setScore("".equals(score) ? 0 : Integer.parseInt(score));
            String ranking = map.get("RANKING").toString();
            dto.setRanking("".equals(ranking) ? 0 : Integer.parseInt(ranking));
            dto.setXname(map.get("XNAME").toString());
            dto.setSname(map.get("SNAME").toString());
            dtos.add(dto);
        }
        result.setData(dtos);
        result.setCount(dtos.size());
        return result;
    }

    @Override
    public String query2(String firstType,int score) {
        Result result = new Result();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Map> list = specialtyDao.queryRanking2(year - 1, firstType, year, score);
        List<Map> list1 = specialtyDao.getRanking(firstType,year,score);
        return year+"排名："+list1.get(0).get("RANKING").toString()+"，"+(year-1)+"排名："+list.get(0).get("RANKING").toString()+"，"+(year-1)+"对应分数："+ list.get(0).get("SCORE").toString();
    }

    @Override
    public Result write(WriteItem item) {
        Result result = new Result();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        //还没有2022的排名，所有年份-1
        List<Map> list = specialtyDao.queryRanking(year - 1, item.getFirstType(), year, item.getScore());
        String[] s = item.getTwoType().split(",");
        int score = Integer.parseInt(list.get(0).get("SCORE").toString());
        year = year - 1;
        //System.out.println(item);

        List<String> adds3 = new ArrayList<String>(Arrays.asList(item.getAddress().split(",")));
        List<String> adds2=new ArrayList<String>();

        //for循环遍历list集合中的重复元素
        for (String str:adds3){
            //如果包含了重复元素，则不将元素保存到这个新的集合
            if (!adds2.contains(str)){
                //把list遍历后的元素存放在新集合中
                adds2.add(str);
            }
        }
        adds2.remove("全国");

        //adds2.forEach(System.out::print);
        //System.out.println("------------");
        String[] adds1 = {"江苏省", "上海市", "浙江省", "北京市", "山东省", "湖北省", "四川省"};
        for (String str : adds1) {
            if (!adds2.contains(str) && adds2.size()<7) {
                adds2.add(str);
            }
        }
        //adds2.forEach(System.out::print);
        String[] adds = (String[]) adds2.toArray(new String[7]);
        for (String add : adds) {
            System.out.print(add);
        }


        List<Map> listW = new ArrayList<>();
        //冲10
        chong(listW,item,s,adds,score+1,year);

        //稳20
        int so = wen(listW,item,s,adds,score,year);

        //保10
        bao(listW,item,s,adds,score+so,year);

        List<SpecialtyDto> dtos = new ArrayList<>();
        for (Map map : listW) {
            SpecialtyDto dto = new SpecialtyDto();
            dto.setXname(map.get("NAME").toString());
            dto.setAddress(map.get("ADDRESS").toString());
            dto.setEname(map.get("ENAME").toString());
            dto.setFeature(map.get("FEATURE").toString());
            dto.setRemark(map.get("REMARK").toString());
            dto.setGroup_name(map.get("GROUP_NAME").toString());
            dto.setGroup_code(map.get("GROUP_CODE").toString());
            String score1 = map.get("MIN_SCORE").toString();
            dto.setMin_score("".equals(score1) ? 0 : Integer.parseInt(score1));
            String ranking = map.get("RANKING").toString();
            dto.setRanking("".equals(ranking) ? 0 : Integer.parseInt(ranking));
            dto.setGet_num(map.get("GET_NUM").toString());
            dtos.add(dto);
        }

        List<String> l2 = new ArrayList<String>(Arrays.asList(item.getCategoryId().split(",")));
        List<String> l3 = new ArrayList<>();
        //for循环遍历list集合中的重复元素
        for (String str:l2){
            //如果包含了重复元素，则不将元素保存到这个新的集合
            if (!l3.contains(str)){
                //把list遍历后的元素存放在新集合中
                l3.add(str);
            }
        }
        List<Category> l4 = new ArrayList<>();

        for (String s1 : l3) {
            l4.addAll(categoryDao.findByCategoryId(s1));
        }

        List<String> listGc = dtos.stream().map(SpecialtyDto::getGroup_code).collect(Collectors.toList());
        List<WordDto> wordDtolist = new ArrayList<>();
        List<WordSpe> wordSpelist = new ArrayList<>();
        for (String group_code : listGc) {
            List<Map> li = specialtyDao.queryWord(group_code);
            WordDto dto = new WordDto();
            List<WordSpe> lispe = new ArrayList<>();
            for (Map map : li) {
                dto.setGroup_code(map.get("GROUP_CODE").toString());
                dto.setGroup_name(map.get("NAME").toString());
                WordSpe ws = new WordSpe();
                ws.setScode(map.get("SCODE").toString());
                ws.setSname(map.get("SNAME").toString());
                lispe.add(ws);
            }

            if (lispe.size() > 6) {
                List<WordSpe> lnew = new ArrayList<>();

                List<WordSpe> li1 = lispe.stream().collect(Collectors.toList());
                for (WordSpe ws : lispe) {
                    for (Category c : l4) {
                        if (ws.getSname().indexOf(c.getName()) != -1) {
                            lnew.add(ws);
                            li1.remove(ws);
                            break;
                        }
                    }
                }

                int d = 0;
                while (lnew.size() < 6) {
                    lnew.add(li1.get(d));
                    d++;
                }
                lnew = lnew.subList(0, 6);
                lispe = lnew;
            }

            while (lispe.size() < 6) {
                WordSpe ws = new WordSpe();
                ws.setSname("");
                ws.setScode("");
                lispe.add(ws);
            }

            wordDtolist.add(dto);
            wordSpelist.addAll(lispe);
        }

        WordUtil wu = new WordUtil();
        wu.createWord(wordDtolist, wordSpelist);
        result.setData(dtos);
        result.setCount(dtos.size());
        return result;
    }

    //稳20
    public int wen(List<Map> listW,WriteItem item,String[] s,String[] adds,int score,int year){
        int a = 0;//地址的下标
        int b = 0;//分数的波动
        boolean bo=false;//是否插入成功
        int x=1;
        List<Map> listX=new ArrayList<>();
        while (listW.size() < 30) {
            switch (a) {
                case 0:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[0]
                            , score + b, year));
                    break;
                case 1:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[1]
                            , score + b, year));
                    break;
                case 2:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[2]
                            , score + b, year));
                    break;
                case 3:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[3]
                            , score + b, year));
                    break;
                case 4:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[4]
                            , score + b, year));
                    break;
                case 5:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[5]
                            , score + b, year));
                    break;
                case 6:
                    bo= listX.addAll(specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[6]
                            , score + b, year));
                    break;
                case 7:
                    bo= listX.addAll(specialtyDao.writeRankingW2(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%"
                            , score + b, year));
                    break;
                default:
                    a = -1;
                    b--;
                    break;
            }
            if(bo){
                if(listX.size()>=4){
                    b--;
                    a = -1;
                    listX = listX.subList(0, 4);
                    listW.addAll(listX);
                    listX.clear();

                }
                if(a==-1){
                    listW.addAll(listX);
                    listX.clear();
                }
                bo=false;
            }
            a++;
        }
        listW = listW.subList(0, 30);
        return b;
    }

    //保10
    public void bao(List<Map> listW,WriteItem item,String[] s,String[] adds,int score,int year){
        int a = 0;//地址的下标
        int b = 0;//分数的波动
        boolean bo=false;//是否插入成功
        int x=1;
        while (listW.size() < 40) {
            switch (a) {
                case 0:
                    List<Map> list=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[0]
                            , score + b, year);
                    if(list.size()>0){
                        chongGet985(list,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 1:
                    List<Map> list1=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[1]
                            , score + b, year);
                    if(list1.size()>0){
                        chongGet985(list1,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 2:
                    List<Map> list2=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[2]
                            , score + b, year);
                    if(list2.size()>0){
                        chongGet985(list2,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 3:
                    List<Map> list3=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[3]
                            , score + b, year);
                    if(list3.size()>0){
                        chongGet985(list3,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 4:
                    List<Map> list4=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[4]
                            , score + b, year);
                    if(list4.size()>0){
                        chongGet985(list4,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 5:
                    List<Map> list5=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[5]
                            , score + b, year);
                    if(list5.size()>0){
                        chongGet985(list5,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 6:
                    List<Map> list6=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[6]
                            , score + b, year);
                    if(list6.size()>0){
                        chongGet985(list6,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 7:
                    List<Map> list7=specialtyDao.writeRankingW2(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%"
                            , score + b, year);
                    if(list7.size()>0){
                        chongGet985(list7,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                default:
                    a = -1;
                    b--;
                    break;
            }
            if(bo){
                b--;
                bo=false;
            }
            a++;
        }
        listW = listW.subList(0, 40);
    }
    //冲10
    public void chong(List<Map> listW,WriteItem item,String[] s,String[] adds,int score,int year){
        int a = 0;//地址的下标
        int b = 0;//分数的波动
        boolean bo=false;//是否插入成功
        int x=1;
        while (listW.size() < 10) {
            switch (a) {
                case 0:
                    List<Map> list=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[0]
                            , score + b, year);
                    if(list.size()>0){
                        chongGet985(list,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 1:
                    List<Map> list1=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[1]
                            , score + b, year);
                    if(list1.size()>0){
                        chongGet985(list1,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 2:
                    List<Map> list2=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[2]
                            , score + b, year);
                    if(list2.size()>0){
                        chongGet985(list2,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 3:
                    List<Map> list3=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[3]
                            , score + b, year);
                    if(list3.size()>0){
                        chongGet985(list3,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 4:
                    List<Map> list4=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[4]
                            , score + b, year);
                    if(list4.size()>0){
                        chongGet985(list4,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 5:
                    List<Map> list5=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[5]
                            , score + b, year);
                    if(list5.size()>0){
                        chongGet985(list5,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 6:
                    List<Map> list6=specialtyDao.writeRankingW(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%", adds[6]
                            , score + b, year);
                    if(list6.size()>0){
                        chongGet985(list6,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                case 7:
                    List<Map> list7=specialtyDao.writeRankingW2(item.getFirstType()
                            , "%" + s[0] + "%", "%" + s[1] + "%"
                            , score + b, year);
                    if(list7.size()>0){
                        chongGet985(list7,listW);
                        bo=true;
                        a = -1;
                    }
                    break;
                default:
                    a = -1;
                    b++;
                    break;
            }
            if(bo){
                b++;
                bo=false;
            }
            a++;
        }
        listW = listW.subList(0, 10);
        Collections.reverse(listW);
    }
    //筛选985，211，双一流
    public void chongGet985(List<Map> list,List<Map> listW){
        for (Map map : list) {
            String feature = map.get("FEATURE").toString();
            switch (feature){
                case "985":
                    listW.add(map);
                    return;
                case "211":
                    listW.add(map);
                    return;
                case "双一流":
                    listW.add(map);
                    return;
                default:
                    listW.add(map);
                    return;
            }
        }
    }
}
