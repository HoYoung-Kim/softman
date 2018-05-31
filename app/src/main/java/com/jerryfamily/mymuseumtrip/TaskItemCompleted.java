package com.jerryfamily.mymuseumtrip;

import android.view.View;

import java.util.ArrayList;

/**
 * 카테고리 호출 종료시 알려 주는 인터페이스
 */

public interface TaskItemCompleted {

    // Define data you like to return string from AysncTask
    public void onTaskComplete(ArrayList<ItemInfo> itemInfos,
                               APIResultData apiResultData);

}
