package com.jerryfamily.mymuseumtrip;

/**
 * Created by 호영 on 2018-05-21.
 */

class ExhibitionInfo {
    private String title;
    private String description;
    private String supervision;
    private String place;
    private String url;
    private String telephone;
    private String date;

    public ExhibitionInfo() {
    }

    public ExhibitionInfo(String title, String description, String supervision, String place, String url, String telephone, String date) {
        this.title = title;
        this.description = description;
        this.supervision = supervision;
        this.place = place;
        this.url = url;
        this.telephone = telephone;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
