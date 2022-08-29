package com.example.service.user;

import com.example.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> list(User user);
    User login(User user);

    void logout();

    void update(User user);

    void levelUp(Long userId,int value);

    void delete(User user);

    void insert(User user);

    User selectByPhone(User user);

    User selectById(User user);

    User userInfo(Long userId);

}
