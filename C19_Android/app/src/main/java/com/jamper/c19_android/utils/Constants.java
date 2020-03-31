package com.jamper.c19_android.utils;

import android.app.NotificationManager;

import java.util.Random;

public class Constants {
    public static final String BUSINESS_INFO = "BusinessDashBoardActivity.BUSINESS_INFO";
    public static final String BUSINESS_ID = "BusinessDashBoardActivity.BUSINESS_ID";
    public static final String USER_DETAILS = "BusinessDashBoardActivity.USER_DETAILS";
    public static final int ADD_NEW_USER = 34599;
    public static final String NOTIFICATION_CHANNEL_ID = "covid_notif_id";
    public static final String NOTIFICATION_CHANNEL_DESCRIPTION = "covid_notif_description";
    public static final CharSequence NOTIFICATION_NAME = "covid_notif_name";
    public static final int NOTIFICATION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;
    public static final int NOTIFICATION_ID = new Random().nextInt(1000);
    public static final Object SUCCESS = "SUCCESS";
    public static final Object FAILURE = "FAILURE";
}
