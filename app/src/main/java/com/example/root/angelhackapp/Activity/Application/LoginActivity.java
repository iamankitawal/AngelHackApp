package com.example.root.angelhackapp.Activity.Application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.angelhackapp.Activity.Managers.PrefUtils;
import com.example.root.angelhackapp.Activity.Model.User;
import com.example.root.angelhackapp.Activity.Utils.Constants;
import com.example.root.angelhackapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends Activity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
//    private TextView btnLogin;
    private ProgressDialog progressDialog;
    User user;
    private static final String TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(PrefUtils.getCurrentUser(LoginActivity.this) != null){
            Intent homeIntent = new Intent(LoginActivity.this,MyProfileActivity.class);
            startActivity(homeIntent);
            finish();
        }
        callbackManager=CallbackManager.Factory.create();
        loginButton= (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Constants.getPermList());
        loginButton.registerCallback(callbackManager, mCallBack);
    }

    @Override
    protected void onResume() {
        super.onResume();


//        callbackManager=CallbackManager.Factory.create();
//
//        loginButton= (LoginButton)findViewById(R.id.login_button);
//        loginButton.setReadPermissions("public_profile", "email", "user_friends");
//        loginButton.registerCallback(callbackManager, mCallBack);
//        btnLogin= (TextView) findViewById(R.id.btnLogin);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                progressDialog = new ProgressDialog(LoginActivity.this);
//                progressDialog.setMessage("Loading...");
//                progressDialog.show();
//
//                loginButton.performClick();
//
//                loginButton.setPressed(true);
//
//                loginButton.invalidate();
//
//
//                loginButton.setPressed(false);
//
//                loginButton.invalidate();
//
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            //progressDialog.dismiss();

            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
                                user = new User();
                                user.facebookID = object.getString("id").toString();
                                user.email = object.getString("email").toString();
                                user.name = object.getString("name").toString();
                                user.gender = object.getString("gender").toString();
                               // user.about=object.getString("about").toString();
                                //user.homeTown=object.getString("hometown").toString();
                                user.imageUrl=object.getJSONObject("picture").getJSONObject("data").getString("url").toString();
                                PrefUtils.setCurrentUser(user,getApplicationContext());
                                Log.i(TAG,getApplicationContext().toString());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(LoginActivity.this, "welcome " + user.name, Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,MyProfileActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday,picture,hometown");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "Log in Canceled", Toast.LENGTH_SHORT).show();
            //progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            Toast.makeText(LoginActivity.this, "Log in Error", Toast.LENGTH_SHORT).show();
            //progressDialog.dismiss();
        }
    };

}

