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
    private String thumbnailUrl;

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

    /**
     * column:top5app。
     * comment:top5 app。
     */
    private String top5app;


    public String getAppid() {
        return appid;
    }


    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }


    public Integer getScore() {
        return score;
    }


    public void setScore(Integer score) {
        this.score = score;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl == null ? null : thumbnailUrl.trim();
    }


    public String getIntro() {
        return intro;
    }


    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }


    public String getDeveloper() {
        return developer;
    }


    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }


    public String getTop5app() {
        return top5app;
    }


    public void setTop5app(String top5app) {
        this.top5app = top5app == null ? null : top5app.trim();
    }

}
