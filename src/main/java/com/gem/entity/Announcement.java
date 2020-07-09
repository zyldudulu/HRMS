package com.gem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 公告实体类
 * @Date: 2020-07-09
 **/
@Data
@TableName("crm_announcement")
public class Announcement {

    //编号
    @TableId(type = IdType.AUTO)
    Long id;

    //标题
    String title;

    //内容
    String data;


    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
