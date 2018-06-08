package com.example.albin.sportec.Session;

public class Session {
    private static String mId;
    private static String mUser;

    public static String getmId() {
        return mId;
    }

    public static void setmId(String mId) {
        Session.mId = mId;
    }

    public static String getmUser() {
        return mUser;
    }

    public static void setmUser(String mUser) {
        Session.mUser = mUser;
    }
}
