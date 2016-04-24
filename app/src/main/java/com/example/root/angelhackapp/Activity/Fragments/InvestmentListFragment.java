package com.example.root.angelhackapp.Activity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.angelhackapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestmentListFragment extends Fragment {
    public static final String PROFILE_ID="profileID";
    public InvestmentListFragment() {
        // Required empty public constructor
    }
    public static InvestmentListFragment newInstance(int profileId) {
        InvestmentListFragment fragment = new InvestmentListFragment();
        Bundle args = new Bundle();
        args.putInt(PROFILE_ID,profileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investment_list, container, false);
    }

}
