package com.example.root.angelhackapp.Activity.Application;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.root.angelhackapp.Activity.Adapter.MarketListAdapter;
import com.example.root.angelhackapp.Activity.Model.Investment;
import com.example.root.angelhackapp.Activity.Model.Singleton;
import com.example.root.angelhackapp.Activity.Utils.API;
import com.example.root.angelhackapp.Activity.Utils.Constants;
import com.example.root.angelhackapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ListView investmentList;
    private ProgressDialog progressDialog;
    MarketListAdapter adapter;
    ArrayList<Investment> list;
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=getApplicationContext();
        list=new ArrayList<>();
        investmentList=(ListView)findViewById(R.id.marketListView);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.listViewSwipeRefresh);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        adapter=new MarketListAdapter(context,list);
        investmentList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                Singleton.getInstance(context).addToRequestQueue(makeJsonRequest());
            }
        });

        investmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //call the Activity
                Intent intent=new Intent(MarketActivity.this,MarketActivity.class);
                Investment i=adapter.getItem(position);
                Bundle bundle=new Bundle();
                bundle.putString("name",i.name);
                bundle.putInt("totalInvestment",i.totalInvestment);
                bundle.putInt("rating",i.rating);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //getAllInvestments
    public JsonObjectRequest makeJsonRequest(){
        //showPDialog();
//        progressDialog=new ProgressDialog(MarketActivity.this);
//        progressDialog.setMessage("Loading...");
        swipeRefreshLayout.setRefreshing(true);
       // Log.i(Constant.TAG_MAIN_ACTIVITY, "Requesting...");
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,API.getAllInvestments(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(Constants.KEY_INVESTMENTS);
                            for (int i=0;i<jsonArray.length();i++){
                                //individual objects
                                JSONObject object=jsonArray.getJSONObject(i);
                                Investment investment=new Investment(object);
                                //check for duplication of articles
                                list.add(investment);
                                Log.i("MarketActivity",investment.name);
                                //articles.add(article);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                           // Log.e(Constant.TAG_MAIN_ACTIVITY,"****Error in Volley Request****");
                        }
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }
        );
        request.setShouldCache(true);
     //   request.setTag(Constant.TAG_MAIN_ACTIVITY);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


        return request;
    }


    @Override
    public void onRefresh() {
        list.clear();
        Singleton.getInstance(getApplicationContext()).addToRequestQueue(makeJsonRequest());
    }
}
