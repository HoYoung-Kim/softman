package com.jerryfamily.mymuseumtrip;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

/**
 * parsing xml document
 */

public class MuseumXMLParser {

    private static final String TAG = MuseumXMLParser.class.getSimpleName();

    //
    // 카테고리 xml 파서
    //
    public static ArrayList<CategoryInfo> CategoryParseXML(XmlPullParser parser, APIResultData apiResultData) throws XmlPullParserException,IOException {
        ArrayList<CategoryInfo> categoryInfos = null;
        int eventType = parser.getEventType();
        CategoryInfo categoryInfo = null;

        try {
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        categoryInfos = new ArrayList();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("numOfRows")) {
                            apiResultData.setNumOfRows(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("pageNo")) {
                            apiResultData.setPageNo(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("totalCount")) {
                            apiResultData.setTotalCount(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("resultCode")) {
                            apiResultData.setResultCode(parser.nextText());
                        } else if (name.equalsIgnoreCase("resultMsg")) {
                            apiResultData.setResultMsg(parser.nextText());
                        } else if (name.equalsIgnoreCase("data")) {
                            categoryInfo = new CategoryInfo();
                        } else if (categoryInfo != null) {
                            if (name.equalsIgnoreCase("item")) {
                                if (parser.getAttributeValue(null, "key").equalsIgnoreCase("code")) {
                                    categoryInfo.setCode(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("nameKr")) {
                                    categoryInfo.setNameKr(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("nameEn")) {
                                    categoryInfo.setNameEn(parser.getAttributeValue(null, "value"));
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("data") && categoryInfo != null) {
                            if (categoryInfo.getNameEn() == null){
                                categoryInfo.setNameEn("");
                            }

                            categoryInfos.add(categoryInfo);
                        }
                }
                eventType = parser.next();
            }
        }catch (Exception ex){
            Log.e(TAG, "Error = " + ex.toString());
        }

        return categoryInfos;
    }

    //
    // 아이템 xml 파서
    //
    public static ArrayList<ItemInfo> ItemParseXML(XmlPullParser parser, APIResultData apiResultData) throws XmlPullParserException,IOException{
        ArrayList<ItemInfo> itemInfos = null;
        int eventType = parser.getEventType();
        ItemInfo itemInfo = null;

        try {
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        itemInfos = new ArrayList();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("numOfRows")) {
                            apiResultData.setNumOfRows(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("pageNo")) {
                            apiResultData.setPageNo(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("totalCount")) {
                            apiResultData.setTotalCount(Integer.parseInt(parser.nextText()));
                        } else if (name.equalsIgnoreCase("resultCode")) {
                            apiResultData.setResultCode(parser.nextText());
                        } else if (name.equalsIgnoreCase("resultMsg")) {
                            apiResultData.setResultMsg(parser.nextText());
                        } else if (name.equalsIgnoreCase("data")) {
                            itemInfo = new ItemInfo();
                        } else if (itemInfo != null) {
                            if (name.equalsIgnoreCase("item")) {
                                if (parser.getAttributeValue(null, "key").equalsIgnoreCase("id")) {
                                    itemInfo.setId(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("nameKr")) {
                                    itemInfo.setNameKr(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("author")) {
                                    itemInfo.setAuthor(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("imgThumUriM")) {
                                    itemInfo.setImgThumUriM(parser.getAttributeValue(null, "value"));
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("data") && itemInfos != null) {
                            itemInfos.add(itemInfo);
                        }
                }
                eventType = parser.next();
            }
        }catch (Exception ex){
            Log.e(TAG, "Error = " + ex.toString());
        }

        return itemInfos;
    }


    //
    // 아이템 상세 xml 파서
    //
    public static ItemDetailInfo ItemDetailParseXML(XmlPullParser parser, APIResultData apiResultData) throws XmlPullParserException,IOException{
        ArrayList<String> imageListM = null;
        ArrayList<String> imageListL = null;
        int eventType = parser.getEventType();
        ItemDetailInfo ItemDetailInfo = null;

        try {
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        ItemDetailInfo = new ItemDetailInfo();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if  (name.equalsIgnoreCase("resultCode")) {
                            apiResultData.setResultCode(parser.nextText());
                        } else if (name.equalsIgnoreCase("resultMsg")) {
                            apiResultData.setResultMsg(parser.nextText());
                        } else if (name.equalsIgnoreCase("item")) {
                                if (parser.getAttributeValue(null, "key").equalsIgnoreCase("id")) {
                                    ItemDetailInfo.setId(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("nameKr")) {
                                    ItemDetailInfo.setNameKr(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("author")) {
                                    ItemDetailInfo.setAuthor(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("purposeName2")) {
                                    ItemDetailInfo.setPurposeName2(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("sizeInfo")) {
                                    ItemDetailInfo.setSizeInfo(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("materialName1")) {
                                    ItemDetailInfo.setMaterialName1(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("desc")) {
                                    ItemDetailInfo.setDesc(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("museumName2")) {
                                    ItemDetailInfo.setMuseumName2(parser.getAttributeValue(null, "value"));
                                }
                        }

                        if (name.equalsIgnoreCase("imageList")) {
                            imageListM = new ArrayList<>();
                            imageListL = new ArrayList<>();
                        } else if (imageListM != null && imageListL != null){
                            if (name.equalsIgnoreCase("item")) {
                                if (parser.getAttributeValue(null, "key").equalsIgnoreCase("imgThumUriM")) {
                                    imageListM.add(parser.getAttributeValue(null, "value"));
                                } else if (parser.getAttributeValue(null, "key").equalsIgnoreCase("imgThumUriL")) {
                                    imageListL.add(parser.getAttributeValue(null, "value"));
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("imageList") && imageListM != null && imageListL != null) {
                            ItemDetailInfo.setImageListM(imageListM);
                            ItemDetailInfo.setImageListL(imageListL);
                        }
                }
                eventType = parser.next();
            }
        }catch (Exception ex){
            Log.e(TAG, "Error = " + ex.toString());
        }

        return ItemDetailInfo;
    }
}
