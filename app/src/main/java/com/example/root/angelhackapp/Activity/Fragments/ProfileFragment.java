package com.example.root.angelhackapp.Activity.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.root.angelhackapp.Activity.Application.LoginActivity;
import com.example.root.angelhackapp.Activity.Managers.PrefUtils;
import com.example.root.angelhackapp.Activity.Model.Singleton;
import com.example.root.angelhackapp.Activity.Model.User;
import com.example.root.angelhackapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    public static final String TAG="Profile Fragment";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        NetworkImageView profileImage=(NetworkImageView)rootView.findViewById(R.id.profileImage);
        TextView emailId=(TextView)rootView.findViewById(R.id.profileEmail);
        TextView name=(TextView)rootView.findViewById(R.id.profileName);
        Context context=getActivity().getApplicationContext();
        //MakeRequests
        User user=new User();
        String url;
        user= PrefUtils.getCurrentUser(context);
        ImageLoader imageLoader= Singleton.getInstance(context).getImageLoader();
        if(!TextUtils.isEmpty(user.imageUrl)) {
            profileImage.setImageUrl(user.imageUrl, imageLoader);
        }
        emailId.setText(user.email);
        name.setText(user.name);
        return rootView;
    }


}
