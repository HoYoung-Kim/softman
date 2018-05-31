package com.jerryfamily.mymuseumtrip;

import android.text.Spannable;
import android.text.style.URLSpan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


/**
 * Created by 호영 on 2018-04-20.
 */

public class Utils {

    //
    // 숫자 -> 콤마 입력으로 변환하는 함수
    //
    public static String toStringFormat(int num) {

        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(num);

    }

    //
    // InputStream -> string 으로 변환하는 함수
    //
    public static String inputStreamToString(InputStream in) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();

        InputStreamReader isr = new InputStreamReader(in);

        BufferedReader rd = new BufferedReader(isr);

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
}
