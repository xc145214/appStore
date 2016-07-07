package com.appStore.entity;

/**
 * app实体（table="app_info"）
 * Created by Administrator on 2016/7/7.
 */
public class App extends BaseEntiy{


    /**
     * column：title。
     * comment：标题。
     */
    private String title;

    /**
     * column:appid。
     * comment:应用id。
     */
    private String appid;

    /**
     * column:thumbnail_url。
     * comment:文件地址。
     */
    private String thumbnail_url;

    /**
     * column:intro。
     * comment:介绍说明。
     */
    private String intro;

    /**
     * column:url。
     * comment:访问地址。
     */
    private String url;

    /**
     * column:developer。
     * comment:开发者。
     */
    private String developer;

    /**
     * column:score。
     * comment:评分。
     */
    private int score =0;

    public App() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
