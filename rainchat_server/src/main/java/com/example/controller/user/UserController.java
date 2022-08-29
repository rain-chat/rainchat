package com.example.controller.user;
import com.alibaba.fastjson.JSON;
import com.example.domain.article.Collection;
import com.example.domain.extend.AjaxResult;
import com.example.domain.extend.RedisUtils;
import com.example.domain.extend.TokenUtil;
import com.example.domain.user.User;
import com.example.service.article.CollectionService;
import com.example.service.tools.ToolService;
import com.example.service.user.UserService;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ToolService toolService;
    @RequestMapping("/list")
    public String list(@RequestBody User user){
        List<User> userList=userService.list(user);
        PageInfo<User> userPageInfo=new PageInfo<>(userList);
        return JSON.toJSONString(AjaxResult.success("查询成功",userPageInfo));
    }

    @RequestMapping("/myInfo")
    public String myInfo(){
        User user=userService.userInfo(tokenUtil.getUserId());
        return JSON.toJSONString(AjaxResult.success("查询成功",user));
    }

    @RequestMapping("/authorInfo")
    public String authorInfo(@RequestBody User user){
        User obj =userService.userInfo(user.getId());
        //注册日期和电话对读者不可见
        obj.setRegistrationDate(null);
        obj.setPhone(null);
        return JSON.toJSONString(AjaxResult.success("查询成功",obj));
    }
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        User loginUser = userService.login(user);
        AjaxResult ajaxResult = null;
        if(loginUser != null){
            //根据登录账号获取该用户对应的菜单权限（组件权限）
            //List<String> authUrlsList = userService.selectAuthUrlsList(loginUser.getUserId());
            //生成Token(Jwt)
            String token = UUID.randomUUID().toString();
            //把token放入redis缓存，有效时间设置为60分钟
            redisUtils.set(token, loginUser, 60L, TimeUnit.MINUTES);
            Map dataMap = new HashMap();
            dataMap.put("userName", loginUser.getName());
            dataMap.put("headImage", loginUser.getHeadImg());
            ajaxResult = new AjaxResult(true, token, dataMap);
        }else{
            ajaxResult = new AjaxResult(false, "账号或密码错误");
        }
        return JSON.toJSONString(ajaxResult);
    }

    @RequestMapping("/logout")
    public String logout(){
        userService.logout();
        return JSON.toJSONString(AjaxResult.success("退出成功"));
    }

    @RequestMapping("/update")
    public String update(@RequestBody User user){
        userService.update(user);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody User user){
        Collection collection=new Collection();
        collection.setUserId(user.getId().toString());
        collectionService.deleteByUser(collection);
        userService.delete(user);
        if(!StringUtils.isNullOrEmpty(user.getHeadImg())){
            toolService.deleteFile(user.getHeadImg());
        }
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    @RequestMapping("/insert")
    public String insert(@RequestBody User user){
        User isExist=userService.selectByPhone(user);
        if(isExist!=null) return JSON.toJSONString(new AjaxResult(false,"此号码已注册"));
        else{
            userService.insert(user);
            User newUser = userService.selectByPhone(user);
            return login(newUser);
        }
    }
}
