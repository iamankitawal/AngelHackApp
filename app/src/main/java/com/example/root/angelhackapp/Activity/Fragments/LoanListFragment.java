package com.example.root.angelhackapp.Activity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.angelhackapp.Activity.Model.User;
import com.example.root.angelhackapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanListFragment extends Fragment {
    public static final String PROFILE_ID="profileID";
    ListView lv;
    ArrayAdapter adapter;
    public LoanListFragment() {
        // Required empty public constructor
    }

    public static LoanListFragment newInstance(int profileId) {
        LoanListFragment fragment = new LoanListFragment();
        Bundle args = new Bundle();

        args.putInt(PROFILE_ID,profileId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_loan_list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list_loan);
        //adapter=new ArrayAdapter(
    return rootView;
    }


}
