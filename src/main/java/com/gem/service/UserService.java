package com.gem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.entity.User;
import com.gem.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 用户服务层
 * @Date: 2020-07-09
 **/
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
