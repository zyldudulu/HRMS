package com.gem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.entity.Announcement;
import com.gem.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 公告服务层
 * @Date: 2020-07-09
 **/
@Service
public class AnnouncementService extends ServiceImpl<AnnouncementMapper, Announcement> {
}
