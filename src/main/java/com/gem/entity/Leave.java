package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 请假实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_leave")
public class Leave {

    // 编号
    @TableId(type = IdType.AUTO)
    private Long id;

    //用户id
    private Long uid;

    //日期
    private LocalDate time;

    //申请原因
    private String reason;

    //审核状态
    private String status;

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", uid=" + uid +
                ", time=" + time +
                ", reason='" + reason + '\'' +
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

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }
}

