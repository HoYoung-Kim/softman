package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.content.pm.PackageManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 플레이 구글/디바이스 버전 체크
 */

public class MarketVersionChecker {

    //
    // 플레이 구글 버전
    //
    public static String getMarketVersion(String packageName) {
            String version = null;

            try {
                Document doc = Jsoup
                        .connect(
                                "https://play.google.com/store/apps/details?id="+ packageName)
                        .get();

                Elements Version = doc.select(".htlgb ");

                for (int i = 0; i < 5 ; i++) {
                    version = Version.get(i).text();
                    if (Pattern.matches("^[0-9]{1}.[0-9]{1}.[0-9]{1}$", version)) {
                        break;

                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return version;
    }

    //
    // 디바이스 버전
    //
    public static String getDeviceVersion(Context context, String packageName) {
        String version = null;

        try {
            version = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}
