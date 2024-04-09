package com.igeek.university.service;
import com.igeek.university.entity.GroupRank;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGrouprankService {
    Result showList(int page);
    void addGroupRank(GroupRank groupRank) throws Exception;
    void delGroupRank(int grid) throws Exception;
    void editGroupRank(GroupRank groupRank) throws Exception;
    List<GroupRank> excel(MultipartFile file) throws Exception;
}
