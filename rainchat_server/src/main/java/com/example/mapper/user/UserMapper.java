package com.example.mapper.user;

import com.example.domain.user.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll(User user);

    User login(User user);

    void updateByPrimaryKeySelective(User user);

    void deleteById(User user);

    void insert(User user);

    User selectByPhone(User user);

    User selectById(User user);

    User userInfo(Long userId);
}
