package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 奖惩信息实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_wageinfo")
public class Wageinfo {

    //编号
    @TableId(type = IdType.AUTO)
    private Long id;

    //用户id
    private Long uid;

    //金额
    private double val;

    //内容
    private String data;

    //状态
    private String status;

    @Override
    public String toString() {
        return "Wageinfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", val=" + val +
                ", data='" + data + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}