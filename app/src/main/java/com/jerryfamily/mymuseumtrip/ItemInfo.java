package com.jerryfamily.mymuseumtrip;

/*
* Item code 조회
* */

public class ItemInfo {
    private String nameKr;
    private String id;
    private String author;
    private String imgThumUriM;

    public ItemInfo() {
    }

    public String getNameKr() {
        return nameKr;
    }

    public void setNameKr(String nameKr) {
        this.nameKr = nameKr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgThumUriM() {
        return imgThumUriM;
    }

    public void setImgThumUriM(String imgThumUriM) {
        this.imgThumUriM = imgThumUriM;
    }
}
