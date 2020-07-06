package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    //查询所有用户
    @Override
    public List<User> findUsers() {
        //return userDao.findAll();
        //命名redis的key
        String key="allUsers";
        //先从缓存当中其数据
        List<User> userList = (List<User>)redisTemplate.boundValueOps(key).get();
        //缓存中有数据就直接返回
        if (userList != null){
            System.out.println("从redis里面取出的数据"+userList);
            return userList;
        }
        //缓存中没有数据就查询数据库，并将数据放入到缓存中
        userList = userDao.findAll();
        for (User user : userList) {
            System.out.println("从mysql中取出数据"+user);
        }
        redisTemplate.boundValueOps(key).set(userList);
        return userList;
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.getOne(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }
}
