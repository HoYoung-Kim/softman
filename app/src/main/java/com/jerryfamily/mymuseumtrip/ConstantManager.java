package com.jerryfamily.mymuseumtrip;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 호영 on 2018-03-22.
 */

public class ConstantManager  {

    public ConstantManager() {
    }

    // 코드/소장품 목록/소장품 상세 조회
    public static final String CODE_LIST_URL        = "http://www.emuseum.go.kr/openapi/code?serviceKey=%s&pageNo=%s&numOfRows=%d&parentCode=%s";
    public static final String ITEM_LIST_URL        = "http://www.emuseum.go.kr/openapi/relic/list?serviceKey=%s&pageNo=%s&numOfRows=%d&museumCode=%s";
    public static final String ITEM_DETAIL_URL      = "http://www.emuseum.go.kr/openapi/relic/detail?serviceKey=%s&id=%s";
    public static final String SEARCH_LIST_URL      = "http://www.emuseum.go.kr/openapi/relic/list?serviceKey=%s&pageNo=%s&numOfRows=%d&name=%s";

    public static final String serviceKey             = "85%2Fs8t4ASErgzcXZOzkTm740fl2tKob23elNmVeJlUpIqCaEQYcYBkI2zM1%2Fo01MxjVGr43q8eSGWoQajWBs%2Bw%3D%3D";
    public static int numOfRows                         = 20;

    // 앱 종료 시간 정의 - 2초안에 백 버튼을 누르면 종료하는 변수 정의
    public static final long FINISH_INTERVAL_TIME   = 2000;
    public static long backPressedTime                  = 0;

    // TABLayout count
    public static final int TAB_COUNT                  = 7;

    // 아이템 검색 결과 보여주기
    public static final String ITEM_RESULT_MESSAGE    = "검색결과 : %s 건";

    // 스플래쉬 화면 타임아웃 설정
    public static int SPLASH_TIME_OUT                   = 2000; // 라이브에서는 2000 으로 변경함...

    // sharedpreference 에서 값 읽고 쓰기 위한 변수
    public static final String SF_TAG                   = "MyMuseumTrip_SF";
    public static final String SF_SEARCH_TAG           = "searchTextList";
    public static final String SF_VERSION_TAG          = "version";

    // 추천 검색어 가져오는 url
    public static final String RECOMMEND_URL           = "http://appm.rntservice.xyz/getRecommendData.aspx";

    // 앱 초기 데이타 가져오는 url
    public static final String INIT_URL                 = "http://appm.rntservice.xyz/getInitData.aspx";

    // 문의 건의 저장하는 url
    public static final String INQUIRY_URL              = "http://appm.rntservice.xyz/insert_inquiry.aspx";

    // 카카오톡 이미지/링크 url - 라이브시에 변경
    public static final String KAKAO_IMGSRC             = "http://appm.rntservice.xyz/image/ic_launcher_round.png";
    public static final String KAKAO_SITEURL            = "https://play.google.com/store/apps/details?id=com.jerryfamily.mymuseumtrip";

    // 이색 박물관 데이타 리스트
    public static List<StrangeMuseum> strangeMuseums;

    // 전시회 정보 데이타 리스트
    public static List<ExhibitionInfo> exhibitionInfos;
}
