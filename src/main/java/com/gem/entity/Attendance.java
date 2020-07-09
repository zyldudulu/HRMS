package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 考勤实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_attendance")
public class Attendance {

    //编号
    @TableId(type = IdType.AUTO)
    private Long id;

    //用户id
    private Long uid;

    //日期
    private LocalDate time;

    //签到时间
    private LocalTime starttime;

    //签退时间
    private LocalTime endtime;

    //状态
    private String status;

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", uid=" + uid +
                ", time=" + time +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", status='" + status + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUid() {
        return uid;
    }

    public LocalDate getTime() {
        return time;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public String getStatus() {
        return status;
    }
}
