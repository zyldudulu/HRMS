package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.Information;
import com.gem.entity.User;
import com.gem.service.InformationService;
import com.gem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.gem.util.MD5Util.crypt;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 用户控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    InformationService informationService;

    /*
     * @Author dudulu
     * @Description 首页
     * @Param []
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/index")
    public String index() {
        return "index";
    }

    /*
     * @Author dudulu
     * @Description 用户首页
     * @Param []
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/u_index")
    public String u_index() {
        return "u_index";
    }

    /*
     * @Author dudulu
     * @Description 用户个人信息
     * @Param []
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/u_profile")
    public String u_profile() {
        return "u_profile";
    }

    /*
     * @Author dudulu
     * @Description 判断登陆
     * @Param [session, username, password]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/login")
    @ResponseBody
    public String login(HttpSession session,String username,String password) {
        password=crypt(password);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        User user = userService.getOne(wrapper);
        if (user != null) {
            session.setAttribute("user",user);
            Information information=informationService.getById(user.getUsername());
            if(information!=null)session.setAttribute("information",information);
            if(user.getId()==1)return "1";
            else return "2";
        }
        else return "0";
    }

    /*
     * @Author dudulu
     * @Description 管理员修改密码
     * @Param [session, request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("admin/change_admin")
    public String change_admin(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        String p1=request.getParameter("password1");
        String p2=request.getParameter("password2");
        String p3=request.getParameter("password3");
        User user=userService.getById(1);
        if(!user.getPassword().equals(crypt(p1))) return "redirect:/user/admin/index?p2_data="+ URLEncoder.encode("原密码错误","UTF-8");
        if(p2.length()<6||p2.length()>16)return "redirect:/user/admin/index?p2_data="+ URLEncoder.encode("新密码不合法，请重新填写","UTF-8");
        if(!p2.equals(p3))return "redirect:/user/admin/index?p2_data="+ URLEncoder.encode("两次密码不一致","UTF-8");
        user.setPassword(crypt(p2));
        userService.updateById(user);
        session.setAttribute("user",user);
        return "redirect:/user/admin/index?p1_data="+ URLEncoder.encode("修改成功","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 员工修改密码
     * @Param [session, request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/change_user")
    public String change_user(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        String p1=request.getParameter("password1");
        String p2=request.getParameter("password2");
        String p3=request.getParameter("password3");
        User user=(User)session.getAttribute("user");
        if(!user.getPassword().equals(crypt(p1))) return "redirect:/user/u_index?p2_data="+ URLEncoder.encode("原密码错误","UTF-8");
        if(p2.length()<6||p2.length()>16)return "redirect:/user/admin/index?p2_data="+ URLEncoder.encode("新密码不合法，请重新填写","UTF-8");
        if(!p2.equals(p3)) return "redirect:/user/u_index?p2_data="+ URLEncoder.encode("两次密码不一致","UTF-8");
        user.setPassword(crypt(p2));
        userService.updateById(user);
        session.setAttribute("user",user);
        return "redirect:/user/u_index?p1_data="+ URLEncoder.encode("修改成功","UTF-8");
    }


    /*
     * @Author dudulu
     * @Description 退出登陆
     * @Param [session]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
