package com.jerryfamily.mymuseumtrip;

import android.app.Application;

/**
 * 이색박물관 글로벌 구조체
 */

public class StrangeMuseum {
    private String seq;
    private String title;
    private String title_display;
    private String description;
    private String address;
    private String homepage_url;
    private String telephone;
    private String imageM_url;
    private String imageL_url;

    public StrangeMuseum() {
    }

    public StrangeMuseum(String seq, String title, String title_display,
                         String description, String address, String homepage_url,
                         String telephone, String imageM_url, String imageL_url) {
        this.seq = seq;
        this.title = title;
        this.title_display = title_display;
        this.description = description;
        this.address = address;
        this.homepage_url = homepage_url;
        this.telephone = telephone;
        this.imageM_url = imageM_url;
        this.imageL_url = imageL_url;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_display() {
        return title_display;
    }

    public void setTitle_display(String title_display) {
        this.title_display = title_display;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomepage_url() {
        return homepage_url;
    }

    public void setHomepage_url(String homepage_url) {
        this.homepage_url = homepage_url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImageM_url() {
        return imageM_url;
    }

    public void setImageM_url(String imageM_url) {
        this.imageM_url = imageM_url;
    }

    public String getImageL_url() {
        return imageL_url;
    }

    public void setImageL_url(String imageL_url) {
        this.imageL_url = imageL_url;
    }
}
