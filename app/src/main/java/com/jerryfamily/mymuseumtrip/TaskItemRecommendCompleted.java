package com.jerryfamily.mymuseumtrip;

import java.util.List;

/**
 * 추천 검색어 호출 종료시 알려 주는 인터페이스
 */

public interface TaskItemRecommendCompleted {

    // Define data you like to return string from AysncTask
    public void onTaskComplete(List<String> recommendData);
}
