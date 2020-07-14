package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.Announcement;
import com.gem.entity.Announcement_markdown;
import com.gem.entity.Information;
import com.gem.service.AnnouncementService;
import com.gem.util.MarkDown2HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.gem.util.MarkDown2HtmlUtils.markdown2Html;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 公告控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    /*
     * @Author qzh
     * @Description 获取公告列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/list")
    public String list(Model model) {
        QueryWrapper<Announcement>wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Announcement> list=announcementService.list(wrapper);
        List<Announcement_markdown>list_mk=new ArrayList<>();
        for(Announcement announcement:list)
        {
            String data=announcement.getData();
            data=data.replaceAll("<br>","\n");
            data=MarkDown2HtmlUtils.markdown2Html(data);
            data=data.replaceAll("\n","<br>");
            Announcement_markdown announcement_markdown=new Announcement_markdown(announcement.getId(),announcement.getTitle(),announcement.getData(),data);
            list_mk.add(announcement_markdown);
        }
        model.addAttribute("list_pirt",list_mk);
        return "announcement";
    }

    /*
     * @Author qzh
     * @Description 获取公告列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/u_list")
    public String u_list(Model model) throws UnsupportedEncodingException {
        QueryWrapper<Announcement>wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Announcement> list=announcementService.list(wrapper);
        List<Announcement_markdown>list_mk=new ArrayList<>();
        for(Announcement announcement:list)
        {
            String data=announcement.getData();
            data=data.replaceAll("<br>","\n");
            data=MarkDown2HtmlUtils.markdown2Html(data);
            data=data.replaceAll("\n","<br>");
            Announcement_markdown announcement_markdown=new Announcement_markdown(announcement.getId(),announcement.getTitle(),announcement.getData(),data);
            list_mk.add(announcement_markdown);
        }
        model.addAttribute("list_pirt",list_mk);
        return "u_announcement";
    }

    /*
     * @Author qzh
     * @Description 管理员发布公告
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/add")
    public String add(HttpServletRequest request) throws UnsupportedEncodingException {
        String title=request.getParameter("title");
        String data=request.getParameter("data");
        data=data.replaceAll("\r\n","<br>");
        if(title.length()<0||title.length()>25)return "redirect:/announcement/admin/list?p2_data="+ URLEncoder.encode("标题不合法","UTF-8");
        if(data.length()<0)return "redirect:/announcement/admin/list?p2_data="+ URLEncoder.encode("内容不合法","UTF-8");
        Announcement announcement=new Announcement();
        announcement.setTitle(title);
        announcement.setData(data);
        boolean flag=announcementService.save(announcement);
        if(flag)return "redirect:/announcement/admin/list?p1_data="+ URLEncoder.encode("发布成功","UTF-8");
        else return "redirect:/announcement/admin/list?p2_data="+ URLEncoder.encode("发布失败","UTF-8");
    }

    /*
     * @Author qzh
     * @Description 删除公告
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/del")
    public String del(HttpServletRequest request) throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        announcementService.removeById(id);
        return "redirect:/announcement/admin/list?p1_data="+ URLEncoder.encode("删除成功","UTF-8");
    }

    /*
     * @Author qzh
     * @Description 更新公告信息
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/update")
    public String update(HttpServletRequest request) throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        String title=request.getParameter("title");
        String data=request.getParameter("data");
        data=data.replaceAll("\r\n","<br>");
        if(title.length()<0||title.length()>25)return "redirect:/announcement/admin/list?p2_data="+ URLEncoder.encode("标题不合法","UTF-8");
        if(data.length()<0)return "redirect:/announcement/admin/list?p2_data="+ URLEncoder.encode("内容不合法","UTF-8");
        Announcement announcement=new Announcement();
        announcement.setId(new Long(id));
        announcement.setTitle(title);
        announcement.setData(data);
        announcementService.updateById(announcement);
        return "redirect:/announcement/admin/list?p1_data="+ URLEncoder.encode("更新成功","UTF-8");
    }


}
