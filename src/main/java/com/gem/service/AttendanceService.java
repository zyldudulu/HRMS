package com.gem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.entity.Attendance;
import com.gem.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 考勤服务层
 * @Date: 2020-07-09
 **/
@Service
public class AttendanceService extends ServiceImpl<AttendanceMapper, Attendance> {
}
