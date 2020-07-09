package com.gem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void selectOne()
    {
        QueryWrapper<User>wrapper=new QueryWrapper<User>();
        wrapper.eq("username","root").eq("password","root");
        User user=userMapper.selectOne(wrapper);
        System.out.println(user);
    }

}