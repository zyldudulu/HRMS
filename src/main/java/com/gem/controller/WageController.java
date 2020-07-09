package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.Information;
import com.gem.entity.User;
import com.gem.entity.Wage;
import com.gem.entity.Wageinfo;
import com.gem.service.WageService;
import com.gem.service.WageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 工资控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/wage")
public class WageController {

    @Autowired
    WageService wageService;

    @Autowired
    WageinfoService wageinfoService;

    /*
     * @Author dudulu
     * @Description 获取考勤工资
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("querynowwage")
    @ResponseBody
    public String querynowwage(String id) {
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        Wage wage=wageService.getOne(wrapper);
        String ans;
        if(wage==null)ans="0";
        else ans=""+wage.getNowwage();
        return ans;
    }

    /*
     * @Author dudulu
     * @Description 获取总工资
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("queryallwage")
    @ResponseBody
    public String queryallwage(String id) {
        double ans=0;
        QueryWrapper<Wage> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid", id);
        Wage wage=wageService.getOne(wrapper1);
        if(wage==null)ans=0;
        else ans=wage.getNowwage();
        QueryWrapper<Wageinfo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("uid", id).eq("status","未结算");
        List<Wageinfo> list=wageinfoService.list(wrapper2);
        for(Wageinfo wageinfo:list) {
            ans+=wageinfo.getVal();
        }
        return ""+ans;
    }

    /*
     * @Author dudulu
     * @Description 获取考勤工资
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("uquerynowwage")
    @ResponseBody
    public String uquerynowwage(String id) {
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        Wage wage=wageService.getOne(wrapper);
        String ans;
        if(wage==null)ans="0";
        else ans=""+(int)wage.getNowwage();
        return ans;
    }

    /*
     * @Author dudulu
     * @Description 获取总工资
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("uqueryallwage")
    @ResponseBody
    public String uqueryallwage(String id) {
        int ans=0;
        QueryWrapper<Wage> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid", id);
        Wage wage=wageService.getOne(wrapper1);
        if(wage==null)ans=0;
        else ans=(int)wage.getNowwage();
        QueryWrapper<Wageinfo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("uid", id).eq("status","未结算");
        List<Wageinfo> list=wageinfoService.list(wrapper2);
        for(Wageinfo wageinfo:list) {
            ans+=wageinfo.getVal();
        }
        return ""+ans;
    }

    /*
     * @Author dudulu
     * @Description 获取已结算工资
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("uqueryold")
    @ResponseBody
    public String uqueryold(String id) {
        int ans=0;
        QueryWrapper<Wage> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid", id);
        Wage wage=wageService.getOne(wrapper1);
        if(wage==null)ans=0;
        else ans=(int)wage.getOldwage();
        return ""+ans;
    }

    /*
     * @Author dudulu
     * @Description 结算工资
     * @Param [request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/settlement")
    public String settlement(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        //更新wage
        QueryWrapper<Wage> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid", id);
        Wage wage=wageService.getOne(wrapper1);
        wage.setOldwage(wage.getOldwage()+wage.getNowwage());
        wage.setNowwage(0);
        wageService.updateById(wage);
        //更新wageinfo
        QueryWrapper<Wageinfo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("uid", id).eq("status","未结算");
        List<Wageinfo> list=wageinfoService.list(wrapper2);
        for(Wageinfo wageinfo:list) {
            wageinfo.setStatus("已结算");
            wageinfoService.updateById(wageinfo);
        }
        return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("结算成功","UTF-8");
    }

}
