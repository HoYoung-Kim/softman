package com.jerryfamily.mymuseumtrip;

import java.util.ArrayList;

/**
 * Item detail 조회
 * */

public class ItemDetailInfo {

    private String id;
    private String nameKr;
    private String author;                      // 작가 이름
    private String museumName2;                 // 박물관 이름
    private String purposeName2;                // 분류
    private String sizeInfo;                    // 사이즈
    private String materialName1;               // 재질
    private String desc;                        // 설명
    private ArrayList<String> imageListM;      // 중간 사이즈 이미지 리스트
    private ArrayList<String> imageListL;      // 큰 사이즈 이미지 리스트

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameKr() {
        return nameKr;
    }

    public void setNameKr(String nameKr) {
        this.nameKr = nameKr;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMuseumName2() {
        return museumName2;
    }

    public void setMuseumName2(String museumName2) {
        this.museumName2 = museumName2;
    }

    public String getPurposeName2() {
        return purposeName2;
    }

    public void setPurposeName2(String purposeName2) {
        this.purposeName2 = purposeName2;
    }

    public String getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public String getMaterialName1() {
        return materialName1;
    }

    public void setMaterialName1(String materialName1) {
        this.materialName1 = materialName1;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<String> getImageListM() {
        return imageListM;
    }

    public void setImageListM(ArrayList<String> imageListM) {
        this.imageListM = imageListM;
    }

    public ArrayList<String> getImageListL() {
        return imageListL;
    }

    public void setImageListL(ArrayList<String> imageListL) {
        this.imageListL = imageListL;
    }
}
