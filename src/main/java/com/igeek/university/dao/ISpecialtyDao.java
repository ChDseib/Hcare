package com.igeek.university.dao;

import com.igeek.university.entity.Specialty;
import com.igeek.university.entity.University;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface ISpecialtyDao extends JpaRepository<Specialty,Integer> {
    @Query(value = "select address,x.name xname,ename,y.name uname,remark,feature,group_name,scode,sname,score,ranking,length,money from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限') c where f.group_code=c.group_code) x,(select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where u.address=?4 and g.code=u.code) y,specialty s where x.group_code=y.gcode and y.gcode=s.group_code and s.score between ?5 and ?6",nativeQuery = true)
    List<Map> queryFilter(String fname, String dname1, String dname2, String address, int min, int max);

    @Query(value = "select address,x.name xname,ename,y.name uname,remark,feature,group_name,scode,sname,score,ranking,length,money from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限') c where f.group_code=c.group_code) x,(select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where g.code=u.code) y,specialty s where x.group_code=y.gcode and y.gcode=s.group_code and s.score between ?4 and ?5",nativeQuery = true)
    List<Map> queryFilterG(String fname, String dname1, String dname2,int min, int max);

    //@Query(value = "select address,x.name xname,ename,y.name uname,remark,feature,group_name,scode,sname,score,ranking,length,money from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限') c where f.group_code=c.group_code) x,(select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where g.code=u.code) y,specialty s where x.group_code=y.gcode and y.gcode=s.group_code and s.score between ?4 and ?5",nativeQuery = true)
    //List<Map> writeFilterC(String fname, String dname1, String dname2,int score);

    @Query(value="select score from ranking where (ranking,fname,year) in(select max(ranking),fname,year from ranking where year=?1 and fname=?2 and ranking<=(select ranking from ranking where fname=?2 and year=?3 and score=?4))",nativeQuery = true)
    List<Map> queryRanking(int year1,String fname,int year2,int score );

    @Query(value="select score,ranking from ranking where (ranking,fname,year) in(select max(ranking),fname,year from ranking where year=?1 and fname=?2 and ranking<=(select ranking from ranking where fname=?2 and year=?3 and score=?4))",nativeQuery = true)
    List<Map> queryRanking2(int year1,String fname,int year2,int score );

    //今年排名
    @Query(value="select ranking from ranking where fname=?1 and year=?2 and score=?3",nativeQuery = true)
    List<Map> getRanking(String fname,int year,int score);

    //@Query(value="select  z.address,z.name,z.ename,z.remark,z.feature, z.group_name,z.group_code,gr.min_score,gr.ranking,gr.get_num from (select x.group_code,x.name,x.ename,y.address,y.remark,y.feature,y.group_name from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限')c where f.group_code=c.group_code) x, (select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where u.address=?4 and g.code=u.code) y where x.group_code=y.gcode) z,group_rank gr where z.group_code=gr.group_code and gr.min_score=?5 and gr.year=?6 limit 10",nativeQuery = true)
    //List<Map> writeRanking(String name,String dname1, String dname2,String address,int score,int year);

    //@Query(value="select  z.address,z.name,z.ename,z.remark,z.feature, z.group_name,z.group_code,gr.min_score,gr.ranking,gr.get_num from (select x.group_code,x.name,x.ename,y.address,y.remark,y.feature,y.group_name from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限')c where f.group_code=c.group_code) x, (select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where u.address!='江苏省' and u.address!='北京市' and u.address!='浙江省' and u.address!='上海市' and u.address!='山东省' and u.address!='湖北省' and u.address!='四川省' and g.code=u.code) y where x.group_code=y.gcode) z,group_rank gr where z.group_code=gr.group_code and gr.min_score=?4 and gr.year=?5 limit 10",nativeQuery = true)
    //List<Map> writeRanking1(String name,String dname1, String dname2,int score,int year);

    @Query(value="select  z.address,z.name,z.ename,z.remark,z.feature, z.group_name,z.group_code,gr.min_score,gr.ranking,gr.get_num from (select x.group_code,x.name,x.ename,y.address,y.remark,y.feature,y.group_name from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限')c where f.group_code=c.group_code) x, (select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where u.address=?4 and g.code=u.code) y where x.group_code=y.gcode) z,group_rank gr where z.group_code=gr.group_code and gr.min_score=?5 and gr.year=?6 limit 20",nativeQuery = true)
    List<Map> writeRankingW(String name,String dname1, String dname2,String address,int score,int year);

    @Query(value="select  z.address,z.name,z.ename,z.remark,z.feature, z.group_name,z.group_code,gr.min_score,gr.ranking,gr.get_num from (select x.group_code,x.name,x.ename,y.address,y.remark,y.feature,y.group_name from (select f.group_code,name,ename from (select group_code,name from first_type where name=?1) f,(select group_code,ename from course_group_dto where ename like ?2 or ename like ?3 or ename='不限')c where f.group_code=c.group_code) x, (select distinct(group_code) gcode,name,address,remark,feature,group_name from university u,specialty_group g where u.address!='江苏省' and u.address!='北京市' and u.address!='浙江省' and u.address!='上海市' and u.address!='山东省' and u.address!='湖北省' and u.address!='四川省' and g.code=u.code) y where x.group_code=y.gcode) z,group_rank gr where z.group_code=gr.group_code and gr.min_score=?4 and gr.year=?5 limit 20",nativeQuery = true)
    List<Map> writeRankingW2(String name,String dname1, String dname2,int score,int year);

    @Query(value="select group_code,name,scode,sname from specialty where group_code=?1",nativeQuery = true)
    List<Map> queryWord(String group_code);

    List<Specialty> findByNameContainingAndSnameContainingAndYear(String name, String sname,int year, Pageable page);
    List<Specialty> findByNameContainingAndYear(String name,int year, Pageable page);
    List<Specialty> findBySnameContainingAndYear(String sname,int year, Pageable page);
    List<Specialty> findByYear(int year, Pageable page);
    int countByNameContainingAndSnameContainingAndYear(String name, String sname,int year);
    int countByNameContainingAndYear(String name,int year);
    int countBySnameContainingAndYear(String sname,int year);
    int countByYear(int year);
}
