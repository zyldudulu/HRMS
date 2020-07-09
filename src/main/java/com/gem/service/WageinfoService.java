package com.gem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.entity.Wageinfo;
import com.gem.mapper.WageinfoMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 奖惩信息服务层
 * @Date: 2020-07-09
 **/
@Data
@Service
public class WageinfoService extends ServiceImpl<WageinfoMapper, Wageinfo> {
}
