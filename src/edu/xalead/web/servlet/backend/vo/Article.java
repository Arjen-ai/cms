package edu.xalead.web.servlet.backend.vo;

import java.sql.Date;
import java.time.LocalDate;

public class Article {
    private int a_id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private String source;
    private boolean headline;
    private boolean topic;

    public boolean isHeadline() {
        return headline;
    }

    public boolean isTopic() {
        return topic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public boolean getHeadline() {
        return headline;
    }

    public void setHeadline(boolean headline) {
        this.headline = headline;
    }

    public boolean getTopic() {
        return topic;
    }

    public void setTopic(boolean topic) {
        this.topic = topic;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime(LocalDate createTime) {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
