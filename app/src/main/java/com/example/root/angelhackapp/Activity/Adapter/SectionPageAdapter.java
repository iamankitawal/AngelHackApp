package com.example.root.angelhackapp.Activity.Adapter;

import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.root.angelhackapp.Activity.Fragments.InvestmentListFragment;
import com.example.root.angelhackapp.Activity.Fragments.LoanListFragment;

/**
 * Created by root on 24/4/16.
 */
public class SectionPageAdapter extends FragmentStatePagerAdapter {

    public static final String TITLE_INVESTEMET_LIST="My Investments";
    public static final String TITLE_LOAN_LIST="My Loans";


    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //Log.i("SECTION_ADAPTER", "*****" + sections_list[position] + "****");
       int profileID=1;//get profileID from user
        if(position==0){
           return LoanListFragment.newInstance(profileID);
       }
            return InvestmentListFragment.newInstance(profileID);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        //return ArticleService.getSectionList().length;
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return TITLE_LOAN_LIST;
        }
        return TITLE_INVESTEMET_LIST;
    }
}
