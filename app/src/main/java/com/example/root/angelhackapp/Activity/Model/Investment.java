package com.example.root.angelhackapp.Activity.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by root on 24/4/16.
 */
public class Investment {
    public String name;
    public String reason;
    public int askAmount;
    public User user;
    public String module;
    public int totalInvestment;
    public Date duedate;
    public int rating;

    public Investment(JSONObject object) throws JSONException {
        name=object.getString("name");
        reason=object.getString("reason");
        int askAmount=object.getInt("amount");
        module=object.getString("module");
        totalInvestment=object.getInt("t_investment");
        rating=object.getInt("rating");

    }
}
