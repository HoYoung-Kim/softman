package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 호영 on 2018-03-22.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = CategoryFragmentPagerAdapter.class.getSimpleName();
    private Context mContext;


    public CategoryFragmentPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.category1_name);
            case 1:
                return mContext.getString(R.string.category2_name);
            case 2:
                return mContext.getString(R.string.category3_name);
            case 3:
                return mContext.getString(R.string.category4_name);
            case 4:
                return mContext.getString(R.string.category5_name);
            case 5:
                return mContext.getString(R.string.category6_name);
            case 6:
                return mContext.getString(R.string.category7_name);
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new FRNational1Fragment();
            case 1 :
                return new FRNational2Fragment();
            case 2 :
                return new FRPublicFragment();
            case 3 :
                return new FRCorporationFragment();
            case 4 :
                return new FRUniversityFragment();
            case 5 :
                return new FROthersFragment();
            case 6 :
                return new FRForeignCountryFragment();
            default:
                return new FRNational1Fragment();
        }
    }

    @Override
    public int getCount() {
        return ConstantManager.TAB_COUNT;
    }
}
