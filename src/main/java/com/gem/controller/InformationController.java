package com.gem.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.entity.User;
import com.gem.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.Model;
import com.gem.entity.Information;
import com.gem.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static com.gem.util.MD5Util.crypt;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 员工信息控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    InformationService informationService;

    @Autowired
    UserService userService;

    /*
     * @Author dudulu
     * @Description 获取员工信息列表
     * @Param [model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/list")
    public String list(Model model) {
        List<Information> list=informationService.list(null);
        model.addAttribute("list_pirt",list);
        return "user";
    }

    /*
     * @Author dudulu
     * @Description 添加员工信息
     * @Param [model, request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/add")
    public String add(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
        String name=request.getParameter("name");
        String gender=request.getParameter("gender");
        String department=request.getParameter("department");
        String swage=request.getParameter("wage");
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        if(swage.matches(regex)==false)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("工资格式不合法","UTF-8");
        double wage=Double.parseDouble(swage);
        String sbirthday=request.getParameter("birthday");
        LocalDate birthday = LocalDate.parse(sbirthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String sentry=request.getParameter("entry");
        LocalDate entry = LocalDate.parse(sentry, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String phone=request.getParameter("phone");
        String pattern = "^1[\\d]{10}";
        boolean isMatch = Pattern.matches(pattern, phone);
        if(!isMatch)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("手机号不合法","UTF-8");
        String address=request.getParameter("address");
        Information information=new Information();
        information.setHead("default.jpg");
        information.setName(name);
        information.setGender(gender);
        information.setBirthday(birthday);
        information.setPhone(phone);
        information.setAddress(address);
        information.setDepartment(department);
        information.setWage(wage);
        information.setEntry(entry);
        boolean flag=informationService.save(information);
        if(flag)return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("添加成功","UTF-8");
        else return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("添加失败","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 删除员工信息
     * @Param [request]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/del")
    public String del(HttpServletRequest request) throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        informationService.removeById(id);
        return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("删除成功","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 获取姓名
     * @Param [id]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("queryname")
    @ResponseBody
    public String queryname(String id) {
        Information information=informationService.getById(id);
        return information.getName();
    }

    /*
     * @Author dudulu
     * @Description 更新员工信息
     * @Param [request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String sid=request.getParameter("id");
        Long id=new Long(sid);
        String name=request.getParameter("name");
        String gender=request.getParameter("gender");
        String department=request.getParameter("department");
        String swage=request.getParameter("wage");
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        if(swage.matches(regex)==false)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("工资格式不合法","UTF-8");
        Long wage=new Long(swage);
        String sbirthday=request.getParameter("birthday");
        LocalDate birthday = LocalDate.parse(sbirthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String sentry=request.getParameter("entry");
        LocalDate entry = LocalDate.parse(sentry, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String phone=request.getParameter("phone");
        String resertcheck=request.getParameter("ResertCheck");
        String pattern = "^1[\\d]{10}";
        boolean isMatch = Pattern.matches(pattern, phone);
        if(!isMatch)return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("手机号不合法","UTF-8");
        String address=request.getParameter("address");
        Information information=new Information();
        information.setId(id);
        information.setHead("default.jpg");
        information.setName(name);
        information.setGender(gender);
        information.setBirthday(birthday);
        information.setPhone(phone);
        information.setAddress(address);
        information.setDepartment(department);
        information.setWage(wage);
        information.setEntry(entry);
        boolean flag=informationService.updateById(information);
        if(resertcheck!=null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", id);
            User user = userService.getOne(wrapper);
            user.setPassword(crypt("123456"));
            flag&=userService.updateById(user);
        }
        if(flag)return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("修改信息成功","UTF-8");
        else return "redirect:/information/admin/list?p2_data="+ URLEncoder.encode("修改信息失败","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 批量删除员工信息
     * @Param [request, response]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/delmany")
    public String delmay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Information>list=informationService.list(null);
        if(list==null)return "redirect:/information/admin/list";
        for(Information information:list) {
            Long id=information.getId();
            String sid=String.valueOf(id);
            String s=request.getParameter(sid);
            if(s!=null) informationService.removeById(id);
        }
        return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("批量删除成功","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 查询员工信息
     * @Param [request, model]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("admin/search")
    public String search(HttpServletRequest request,Model model) {
        String id=request.getParameter("id_search");
        String name=request.getParameter("name_search");
        String department=request.getParameter("department_search");
        QueryWrapper<Information> wrapper=new QueryWrapper<>();
        wrapper.like("id",id);
        wrapper.like("name",name);
        wrapper.like("department",department);
        List<Information>list=informationService.list(wrapper);
        model.addAttribute("id_search",id);
        model.addAttribute("name_search",name);
        model.addAttribute("department_search",department);
        model.addAttribute("list_pirt",list);
        return "user";
    }

    /*
     * @Author dudulu
     * @Description 导出excel
     * @Param []
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/export")
    public String export() {
        // 1. 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 2. 创建工作表
        XSSFSheet sheet = workbook.createSheet("WriterDataTest");
        sheet.setColumnWidth(0, 10*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 20*256);
        sheet.setColumnWidth(7, 20*256);
        sheet.setColumnWidth(8, 20*256);
        // 3. 模拟待写入数据
        List<String>list_title=new ArrayList<String>();
        list_title.add("ID");
        list_title.add("姓名");
        list_title.add("性别");
        list_title.add("生日");
        list_title.add("电话");
        list_title.add("地址");
        list_title.add("部门");
        list_title.add("工资");
        list_title.add("入职日期");
        List<Information>list=informationService.list(null);

        //4. 遍历数据写入表中
        int rowNum = 0;
        int cellNum=0;
        Row row=sheet.createRow(rowNum++);
        Cell cell;
        for(String title:list_title)
        {
            cell=row.createCell(cellNum++);
            cell.setCellValue(title);
        }
        for(Information information:list)
        {
            cellNum=0;
            row=sheet.createRow(rowNum++);

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getId());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getName());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getGender());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getBirthday().toString());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getPhone());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getAddress());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getDepartment());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getWage());

            cell=row.createCell(cellNum++);
            cell.setCellValue(information.getEntry().toString());
        }
        try {
            File file = new File("file\\information.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information/admin/download?fileName=information.xlsx";
    }

    /*
     * @Author dudulu
     * @Description 导入到数据库
     * @Param []
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/import")
    public String importFile() throws UnsupportedEncodingException {
        try {
            FileInputStream file = new FileInputStream(new File("D:\\image\\import.xlsx"));
            //使用Test.xlsx文件创建工作簿对象
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //获取第一个sheet内容
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 逐行遍历
            Iterator<Row> rowIterable = sheet.iterator();
            boolean flag=true;
            int cnt=0;
            while (rowIterable.hasNext()){
                Row row = rowIterable.next();
                // 逐列遍历
                Iterator<Cell> cellIterator = row.cellIterator();
                Information information=new Information();
                cnt=0;
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    if(flag)continue;
                    switch (cell.getCellType()){
                        case Cell.CELL_TYPE_NUMERIC:
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cnt++;
                            String val=cell.getStringCellValue();
                            if(cnt==1)information.setName(val);
                            else if(cnt==2)information.setGender(val);
                            else if(cnt==3)information.setBirthday(LocalDate.parse(val, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                            else if(cnt==4)information.setPhone(val);
                            else if(cnt==5)information.setAddress(val);
                            else if(cnt==6)information.setDepartment(val);
                            else if(cnt==7)information.setWage(new Long(val));
                            else if(cnt==8)information.setEntry(LocalDate.parse(val, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                            break;
                    }
                }
                information.setHead("default.jpg");
                if(information.getName()!=null)informationService.save(information);
                flag=false;
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information/admin/list?p1_data="+ URLEncoder.encode("导入成功","UTF-8");
    }

    /*
     * @Author dudulu
     * @Description 下载文件
     * @Param [request, response, fileName]
     * @return void
     * @Date 2020/7/9
     **/
    @GetMapping("/admin/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        if (fileName != null) {
            //设置文件路径
            File file = new File("file\\"+fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /*
     * @Author dudulu
     * @Description 上传导入的表格
     * @Param [file]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/admin/upload")
    public String upload(@RequestParam("head") MultipartFile file) throws IOException {
        String filePath = "D:\\image\\import.xlsx";
        File dest = new File(filePath);
        file.transferTo(dest);;
        return "redirect:/information/admin/import";
    }

    /*
     * @Author dudulu
     * @Description 修改头像
     * @Param [session, id, file]
     * @return java.lang.String
     * @Date 2020/7/9
     **/
    @PostMapping("/uploadhead")
    public String uploadhead(HttpSession session,Long id, @RequestParam("head") MultipartFile file) throws IOException {
        String picName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = "D:\\image\\" + picName;
        File dest = new File(filePath);
        file.transferTo(dest);
        Information information=informationService.getById(id);
        information.setHead(picName);
        informationService.updateById(information);
        session.removeAttribute("information");
        session.setAttribute("information",information);
        return "redirect:/user/u_profile?p1_data="+ URLEncoder.encode("上传成功","UTF-8");
    }

}
