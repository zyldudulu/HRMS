package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 员工信息实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_information")
public class Information {

    //员工编号
    @TableId(type = IdType.AUTO)
    private Long id;

    //头像
    private String head;

    //姓名
    private String name;

    //性别
    private String gender;

    //生日
    private LocalDate birthday;

    //电话
    private String phone;

    //邮箱
    private String email;

    //部门
    private String department;

    //工资
    private double wage;

    //入职时间
    private LocalDate entry;

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + email + '\'' +
                ", department='" + department + '\'' +
                ", wage=" + wage +
                ", entry=" + entry +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setEntry(LocalDate entry) {
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }

    public String getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getWage() {
        return wage;
    }

    public LocalDate getEntry() {
        return entry;
    }
}
