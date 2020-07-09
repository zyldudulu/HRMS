package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.*;
import com.gem.service.InformationService;
import com.gem.service.WageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 奖惩信息控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/wageinfo")
public class WageinfoController {

    @Autowired
    WageinfoService wageinfoService;

    @Autowired
    InformationService informationService;

    /*
     * @Author dudulu
     * @Description 获取奖惩信息列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/list")
    public String list(Model model) {
        List<Wageinfo> list=wageinfoService.list(null);
        model.addAttribute("list_pirt",list);
        return "wageinfo";
    }

    /*
     * @Author wzl
     * @Description 添加奖惩记录
     * @Param [model, request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/add")
    public String add(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String uid=request.getParameter("uid");
        String sval=request.getParameter("val");
        String data=request.getParameter("data");
        if(data==null)data="无";
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("id", uid);
        Information information=informationService.getOne(wrapper);
        if(information==null)return "redirect:/wageinfo/admin/list?p2_data="+ URLEncoder.encode("该员工不存在","UTF-8");
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        if(sval.matches(regex)==false)return "redirect:/wageinfo/admin/list?p2_data="+ URLEncoder.encode("奖惩金额不合法，请输入整数","UTF-8");
        double val=Double.parseDouble(sval);
        Wageinfo wageinfo=new Wageinfo();
        wageinfo.setId((long)1);
        wageinfo.setUid((long) Integer.parseInt(uid));
        wageinfo.setVal((long) val);
        wageinfo.setData(data);
        wageinfo.setStatus("未结算");
        boolean flag=wageinfoService.save(wageinfo);
        if(flag)return "redirect:/wageinfo/admin/list?p1_data="+ URLEncoder.encode("添加成功","UTF-8");
        else return "redirect:/wageinfo/admin/list?p2_data="+ URLEncoder.encode("添加失败","UTF-8");
    }

    /*
     * @Author wzl
     * @Description 删除奖惩记录
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/del")
    public String del(HttpServletRequest request) throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        wageinfoService.removeById(id);
        return "redirect:/wageinfo/admin/list?p1_data="+ URLEncoder.encode("删除成功","UTF-8");
    }

    /*
     * @Author wzl
     * @Description 更新奖惩记录
     * @Param [request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String id=request.getParameter("id");
        String uid=request.getParameter("uid");
        String sval=request.getParameter("val");
        String data=request.getParameter("data");
        if(data==null)data="无";
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("id", uid);
        Information information=informationService.getOne(wrapper);
        if(information==null)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("该员工不存在","UTF-8");
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        if(sval.matches(regex)==false)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("奖惩金额不合法，请输入整数","UTF-8");
        double val=Double.parseDouble(sval);
        Wageinfo wageinfo=wageinfoService.getById(id);
        wageinfo.setUid((long) Integer.parseInt(uid));
        wageinfo.setVal(val);
        wageinfo.setData(data);
        boolean flag=wageinfoService.updateById(wageinfo);
        if(flag)return "redirect:/wageinfo/admin/list?p1_data="+ URLEncoder.encode("更新成功","UTF-8");
        else return "redirect:/wageinfo/admin/list?p2_data="+ URLEncoder.encode("添加失败","UTF-8");
    }

    /*
     * @Author wzl
     * @Description 批量删除奖惩记录
     * @Param [request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/delmany")
    public String delmany(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Information>list=informationService.list(null);
        if(list==null)return "redirect:/information/admin/list";
        for(Information information:list) {
            Long id=information.getId();
            String sid=String.valueOf(id);
            String s=request.getParameter(sid);
            if(s!=null) wageinfoService.removeById(id);
        }
        return "redirect:/wageinfo/admin/list?p1_data="+ URLEncoder.encode("批量删除成功","UTF-8");
    }

    /*
     * @Author wzl
     * @Description 查询奖惩记录
     * @Param [request, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("admin/search")
    public String search(HttpServletRequest request,Model model) {
        String uid=request.getParameter("uid_search");
        QueryWrapper<Wageinfo> wrapper=new QueryWrapper<>();
        wrapper.like("uid",uid);
        List<Wageinfo>list=wageinfoService.list(wrapper);
        model.addAttribute("uid_search",uid);
        model.addAttribute("list_pirt",list);
        return "wageinfo";
    }

    /*
     * @Author wzl
     * @Description 获取奖惩金额
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("querywageinfo")
    @ResponseBody
    public String querywageinfo(String id) {
        QueryWrapper<Wageinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id).eq("status","未结算");
        List<Wageinfo>list=wageinfoService.list(wrapper);
        double ans=0;
        for(Wageinfo wageinfo:list) {
            ans+=wageinfo.getVal();
        }
        return ""+ans;
    }

    /*
     * @Author wzl
     * @Description 获取奖惩金额
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("uquerywageinfo")
    @ResponseBody
    public String uquerywageinfo(String id) {
        QueryWrapper<Wageinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id).eq("status","未结算");
        List<Wageinfo>list=wageinfoService.list(wrapper);
        double ans=0;
        for(Wageinfo wageinfo:list) {
            ans+=wageinfo.getVal();
        }
        return ""+(int)ans;
    }

    /*
     * @Author wzl
     * @Description 员工奖惩信息列表
     * @Param [session, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/u_list")
    public String list(HttpSession session, Model model) {
        Information information=(Information) session.getAttribute("information");
        QueryWrapper<Wageinfo>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",information.getId());
        List<Wageinfo> list=wageinfoService.list(wrapper);
        model.addAttribute("list_pirt",list);
        return "u_wageinfo";
    }
}
