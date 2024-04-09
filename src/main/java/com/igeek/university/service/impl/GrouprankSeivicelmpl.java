package com.igeek.university.service.impl;

import com.igeek.university.dao.ICourseGroupDtoDao;
import com.igeek.university.dao.IGroupRankDao;
import com.igeek.university.entity.GroupRank;
import com.igeek.university.entity.Result;

import com.igeek.university.entity.Specialty;
import com.igeek.university.service.IGrouprankService;
import com.igeek.university.utils.GroupRankExcel;
import com.igeek.university.utils.SpecialtyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrouprankSeivicelmpl implements IGrouprankService {
    @Autowired
    IGroupRankDao groupRankDao;
    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(groupRankDao.findAll(
                PageRequest.of(page-1,10)).getContent());
        result.setCount(groupRankDao.count());
        return result;
    }

    @Override
    public void addGroupRank(GroupRank groupRank) throws Exception {
        groupRankDao.save(groupRank);
    }

    @Override
    public void delGroupRank(int grid) throws Exception {
        groupRankDao.deleteById(grid);
    }

    @Override
    public void editGroupRank(GroupRank groupRank) throws Exception {
        groupRankDao.save(groupRank);
    }


    @Override
    public List<GroupRank> excel(MultipartFile file) throws Exception {
        List<GroupRank> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            System.out.println("============fileName=============="+file.getOriginalFilename());
            //读取excel表格内容---重点
            list = GroupRankExcel.getAllByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
        groupRankDao.saveAll(list);
        return list;
    }
}
