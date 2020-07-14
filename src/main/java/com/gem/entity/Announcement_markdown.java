package com.gem.entity;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 添加markdown支持的公告
 * @Date: 2020-07-13
 **/
public class Announcement_markdown {

    //编号
    Long id;
    //标题
    String title;
    //内容
    String data;
    //markdown语法
    String mdata;

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

    public String getMdata() {
        return mdata;
    }

    public void setMdata(String mdata) {
        this.mdata = mdata;
    }

    @Override
    public String toString() {
        return "Announcement_markdown{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", mdata='" + mdata + '\'' +
                '}';
    }

    public Announcement_markdown(Long id, String title, String data, String mdata) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.mdata = mdata;
    }
}
