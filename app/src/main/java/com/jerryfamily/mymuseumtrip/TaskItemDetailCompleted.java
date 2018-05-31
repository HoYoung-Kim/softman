package com.jerryfamily.mymuseumtrip;

import java.util.ArrayList;

/**
 * 상세정보 호출 종료시 알려 주는 인터페이스
 */

public interface TaskItemDetailCompleted {

    // Define data you like to return string from AysncTask
    public void onTaskComplete(ItemDetailInfo itemDetailInfo,
                                APIResultData apiResultData);

}
