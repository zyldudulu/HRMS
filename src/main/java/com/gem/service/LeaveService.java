package com.gem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.entity.Leave;
import com.gem.mapper.LeaveMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 请假服务层
 * @Date: 2020-07-09
 **/
@Service
public class LeaveService extends ServiceImpl<LeaveMapper, Leave> {
}
