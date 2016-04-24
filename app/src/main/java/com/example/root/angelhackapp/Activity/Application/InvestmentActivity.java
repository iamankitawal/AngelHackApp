package com.example.root.angelhackapp.Activity.Application;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.root.angelhackapp.Activity.Managers.PrefUtils;
import com.example.root.angelhackapp.Activity.Model.User;
import com.example.root.angelhackapp.R;

public class InvestmentActivity extends AppCompatActivity {
    TextView name,total;
    RatingBar rating;
    EditText value;
    Button pay;
    int updatedTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle=getIntent().getExtras();
        updatedTotal=bundle.getInt("totalInvestment");
        name=(TextView)findViewById(R.id.nameOfGainer);
        total=(TextView)findViewById(R.id.amountRequest);
        rating=(RatingBar)findViewById(R.id.rating);
        value=(EditText)findViewById(R.id.donateAmt);
        pay=(Button)findViewById(R.id.pay_button);
        name.setText(bundle.getString("name"));
        total.setText(updatedTotal);
        rating.setRating(bundle.getInt("rating"));


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment(Integer.parseInt(value.getText().toString()));
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    void makePayment(int value){
        if(value>0){
               updatedTotal=updatedTotal+value;
                User user= PrefUtils.getCurrentUser(getApplicationContext());
            user.balance=user.balance-value;

        }
    }

}
