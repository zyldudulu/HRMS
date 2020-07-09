package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.Attendance;
import com.gem.entity.Information;
import com.gem.service.AttendanceService;
import com.gem.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 考勤控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    LeaveService leaveService;

    /*
     * @Author dudulu
     * @Description 获取考勤信息列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/list")
    public String list(Model model) {
        LocalDate now=LocalDate.now();
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("time",now);
        List<Attendance> list=attendanceService.list(wrapper);
        model.addAttribute("list_pirt",list);
        model.addAttribute("now",now);
        return "attendance";
    }

    /*
     * @Author dudulu
     * @Description 获取员工考勤信息列表
     * @Param [session, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/u_list")
    public String list(HttpSession session,Model model) {
        Information information=(Information) session.getAttribute("information");
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",information.getId());
        wrapper.orderByDesc("time");
        List<Attendance>list=attendanceService.list(wrapper);
        model.addAttribute("list_pirt",list);
        LocalDate now=LocalDate.now();
        model.addAttribute("now",now);
        return "u_attendance";
    }

    /*
     * @Author dudulu
     * @Description 查询用户考勤信息
     * @Param [request, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/search")
    public String search(HttpServletRequest request,Model model) {
        String time=request.getParameter("dudulutime");
        LocalDate ld=LocalDate.parse(time);
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("time",ld);
        List<Attendance>list=attendanceService.list(wrapper);
        model.addAttribute("list_pirt",list);
        model.addAttribute("now",time);
        return "attendance";
    }

    /*
     * @Author dudulu
     * @Description 员工签到
     * @Param [session]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("signin")
    public String signin(HttpSession session) throws UnsupportedEncodingException {
        LocalDate now=LocalDate.now();
        Information information=(Information) session.getAttribute("information");
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("time",now);
        wrapper.eq("uid",information.getId());
        Attendance attendance=attendanceService.getOne(wrapper);
        attendance.setStarttime(LocalTime.now());
        attendance.setStatus("在勤");
        attendanceService.updateById(attendance);
        return "redirect:/attendance/u_list?p1_data="+ URLEncoder.encode("签到成功","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 员工签退
     * @Param [session]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("signout")
    public String signout(HttpSession session) throws UnsupportedEncodingException {
        LocalDate now=LocalDate.now();
        Information information=(Information) session.getAttribute("information");
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("time",now);
        wrapper.eq("uid",information.getId());
        Attendance attendance=attendanceService.getOne(wrapper);
        attendance.setEndtime(LocalTime.now());
        attendance.setStatus("签退");
        attendanceService.updateById(attendance);
        return "redirect:/attendance/u_list?p1_data="+ URLEncoder.encode("签退成功","UTF-8");
    }

}
