package com.example.service.user.impl;

import com.example.domain.extend.RedisUtils;
import com.example.domain.extend.TokenUtil;
import com.example.domain.user.User;
import com.example.mapper.user.UserMapper;
import com.example.service.tools.ToolService;
import com.example.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private TokenUtil tokenUtil;

    @Autowired
    private ToolService toolService;

    public List<User> list(User user){
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        return userMapper.selectAll(user);
    }
    public User userInfo(Long userId){
        return userMapper.userInfo(userId);
    }

    public User selectById(User user){
        return userMapper.selectById(user);
    }
    public User login(User user) {
        user.setStatus("已启用");
        return userMapper.login(user);
    }
    @Override
    public void logout() {
        redisUtils.remove(tokenUtil.getToken());
    }

    public  void update(User user){
        if(StringUtils.isNullOrEmpty(user.getHeadImg())){
            user.setHeadImg(null);
        } else {
            User obj=new User();
            obj.setId(tokenUtil.getUserId());
            User oldObj=selectById(obj);
            if(!oldObj.getHeadImg().equals(user.getHeadImg())){
                toolService.deleteFile(oldObj.getHeadImg());
            }
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(User user){
        userMapper.deleteById(user);
    }

    public void insert(User user){
        user.setRegistrationDate(new Date());
        user.setStatus("已启用");
        user.setLevel(0);
        user.setExperience(0);
        userMapper.insert(user);
    }

    public void levelUp(Long userId,int value){
        User obj = new User();
        obj.setId(userId);
        User user= userMapper.selectById(obj);
        int level = user.getLevel();
        int max=(3*level*level+level+1)*33;
        if(user.getExperience()+value>=max) {
            user.setExperience(user.getExperience()+value-max);
            user.setLevel(user.getLevel()+1);
        } else user.setExperience(user.getExperience()+value);
        update(user);
    }
    public User selectByPhone(User user){
        return userMapper.selectByPhone(user);
    }
}
