package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 工资实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_wage")
public class Wage {

    //编号
    @TableId(type = IdType.AUTO)
    private Long id;

    //用户id
    private Long uid;

    //待结算工资
    private double nowwage;

    //已结算工资
    private double oldwage;

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

    public double getNowwage() {
        return nowwage;
    }

    public void setNowwage(double nowwage) {
        this.nowwage = nowwage;
    }

    public double getOldwage() {
        return oldwage;
    }

    public void setOldwage(double oldwage) {
        this.oldwage = oldwage;
    }

    @Override
    public String toString() {
        return "Wage{" +
                "id=" + id +
                ", uid=" + uid +
                ", nowwage=" + nowwage +
                ", oldwage=" + oldwage +
                '}';
    }
}