package com.example.root.angelhackapp.Activity.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/4/16.
 */
public class Constants {
    public static String KEY_LOGGED_IN="facebook_logged_in";

    public static final String BUNDLE_KEY_EMAIL="email";
    public static final String BUNDLE_KEY_NAME="name";
    public static final String BUNDLE_KEY_IMAGE_URL="url";

    public static final ArrayList<String> PERM_LIST=new ArrayList<String>();

    public static ArrayList<String> getPermList(){
        PERM_LIST.add("public_profile");
        PERM_LIST.add("email");
        PERM_LIST.add("user_about_me");
        PERM_LIST.add("user_friends");
        return PERM_LIST;
    }

    public static final String KEY_INVESTMENTS="";
    public static final int MY_SOCKET_TIMEOUT_MS=30000;//Socket timeout
}
