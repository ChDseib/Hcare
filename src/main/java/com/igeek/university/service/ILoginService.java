package com.igeek.university.service;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.University;
import com.igeek.university.entity.User;

public interface ILoginService {
    String login(User user);
    void add(User user) throws Exception;
}
