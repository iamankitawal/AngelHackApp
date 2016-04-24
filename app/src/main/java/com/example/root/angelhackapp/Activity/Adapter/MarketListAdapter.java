package com.example.root.angelhackapp.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.angelhackapp.Activity.Model.Investment;
import com.example.root.angelhackapp.R;

import java.util.ArrayList;

/**
 * Created by root on 24/4/16.
 */
public class MarketListAdapter extends ArrayAdapter<Investment> {
    Context context;
    LayoutInflater inflater;
    public MarketListAdapter(Context context, ArrayList<Investment> investments) {
        super(context,0,investments);
        this.context=context;
    }
    static class ViewHolder{
        private TextView nameOfI;
        private TextView reasonforI;
        private TextView AmountRequest;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Investment investment=getItem(position);
        ViewHolder v;
        if(inflater==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.invest_list_item,parent,false);
            v=new ViewHolder();
            v.nameOfI=(TextView)convertView.findViewById(R.id.investor_name);
            v.reasonforI=(TextView)convertView.findViewById(R.id.investor_reason);
            v.AmountRequest=(TextView)convertView.findViewById(R.id.amountRequest);
            convertView.setTag(v);
        }
        else{
            v=(ViewHolder)convertView.getTag();
        }
        v.nameOfI.setText(investment.name);
        v.AmountRequest.setText(investment.askAmount);
        v.reasonforI.setText(investment.reason);

        return convertView;
    }
    @Override
    public Investment getItem(int position) {
       // Log.i(Constant.TAG_ARTICLE_ADAPTER, super.getItem(position).getArticle_title());
        return super.getItem(position);
    }
}
