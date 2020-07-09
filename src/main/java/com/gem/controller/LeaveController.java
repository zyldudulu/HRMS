package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.Attendance;
import com.gem.entity.Information;
import com.gem.entity.Leave;
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
import java.util.List;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 请假控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @Autowired
    AttendanceService attendanceService;

    /*
     * @Author dudulu
     * @Description 获取请假列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/list")
    public String list(Model model) {
        LocalDate now=LocalDate.now();
        QueryWrapper<Leave>wrapper=new QueryWrapper<>();
        wrapper.eq("time",now);
        List<Leave> list=leaveService.list(wrapper);
        model.addAttribute("list_pirt",list);
        model.addAttribute("now",now);
        return "leave";
    }

    /*
     * @Author dudulu
     * @Description 添加请假记录
     * @Param [session, request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("add")
    public String add(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        String data=request.getParameter("data");
        LocalDate now=LocalDate.now();
        Information information=(Information)session.getAttribute("information");
        Leave leave=new Leave();
        leave.setUid(information.getId());
        leave.setReason(data);
        leave.setTime(now);
        leave.setStatus("未审核");
        leaveService.save(leave);
        return "redirect:/attendance/u_list?p1_data="+ URLEncoder.encode("已申请","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 查询请假记录
     * @Param [request, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/search")
    public String search(HttpServletRequest request, Model model) {
        String time=request.getParameter("dudulutime");
        LocalDate ld=LocalDate.parse(time);
        QueryWrapper<Leave>wrapper=new QueryWrapper<>();
        wrapper.eq("time",ld);
        List<Leave>list=leaveService.list(wrapper);
        model.addAttribute("list_pirt",list);
        model.addAttribute("now",time);
        return "leave";
    }

    /*
     * @Author dudulu
     * @Description 管理员同意请假
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/agree")
    public String agree(HttpServletRequest request) throws UnsupportedEncodingException {
        String sid=request.getParameter("id");
        String suid=request.getParameter("uid");
        String stime=request.getParameter("time");
        //更新请假状态
        Leave leave=leaveService.getById(sid);
        leave.setStatus("已同意");
        leaveService.updateById(leave);
        //更新考勤信息
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",suid);
        wrapper.eq("time",stime);
        Attendance attendance=attendanceService.getOne(wrapper);
        attendance.setStatus("请假");
        attendanceService.updateById(attendance);
        return "redirect:/leave/admin/list?p1_data="+ URLEncoder.encode("已同意","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 管理员拒绝请假
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/refuse")
    public String refuse(HttpServletRequest request) throws UnsupportedEncodingException {
        String sid=request.getParameter("id");
        String suid=request.getParameter("uid");
        String stime=request.getParameter("time");
        //更新请假状态
        Leave leave=leaveService.getById(sid);
        leave.setStatus("已拒绝");
        leaveService.updateById(leave);
        //更新考勤信息
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",suid);
        wrapper.eq("time",stime);
        Attendance attendance=attendanceService.getOne(wrapper);
        attendance.setStatus("缺勤");
        attendanceService.updateById(attendance);
        return "redirect:/leave/admin/list?p1_data="+ URLEncoder.encode("已拒绝","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 管理员撤销操作
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/revoke")
    public String revoke(HttpServletRequest request) throws UnsupportedEncodingException {
        String sid=request.getParameter("id");
        String suid=request.getParameter("uid");
        String stime=request.getParameter("time");
        //更新请假状态
        Leave leave=leaveService.getById(sid);
        leave.setStatus("未审核");
        leaveService.updateById(leave);
        //更新考勤信息
        QueryWrapper<Attendance>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",suid);
        wrapper.eq("time",stime);
        Attendance attendance=attendanceService.getOne(wrapper);
        attendance.setStatus("缺勤");
        attendanceService.updateById(attendance);
        return "redirect:/leave/admin/list?p1_data="+ URLEncoder.encode("已撤销","UTF-8");
    }

}
