package com.jamper.c19_android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamper.c19_android.BuildConfig;
import com.jamper.c19_android.MainActivity;
import com.jamper.c19_android.R;
import com.jamper.c19_android.callbacks.OnItemCallBack;
import com.jamper.c19_android.callbacks.OnItemClicked;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.jamper.c19_android.utils.Constants.FAILURE;
import static com.jamper.c19_android.utils.Constants.NOTIFICATION_CHANNEL_DESCRIPTION;
import static com.jamper.c19_android.utils.Constants.NOTIFICATION_CHANNEL_ID;
import static com.jamper.c19_android.utils.Constants.NOTIFICATION_ID;
import static com.jamper.c19_android.utils.Constants.NOTIFICATION_IMPORTANCE;
import static com.jamper.c19_android.utils.Constants.NOTIFICATION_NAME;
import static com.jamper.c19_android.utils.Constants.SUCCESS;


public class ActivityUtils {

    protected Activity mContext;
    protected FragmentManager fragmentManager;
    private boolean isShowProgressBar = true;


    public ActivityUtils(Activity mContext, FragmentManager supportFragmentManager) {
        this.mContext = mContext;
        this.fragmentManager = supportFragmentManager;
    }

    public ActivityUtils() {

    }

    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }


    public void showSuccess(String mess, OnItemClicked callBack) {
        SuccessDialog dailog = new SuccessDialog();
        dailog.setMessage(mess);
        dailog.show(fragmentManager, "SuccessDialog");
        dailog.setCallback(callBack);
    }


    public void showSuccess(String mess) {
        this.showSuccess(mess, null);
    }


    public void setIsShowProgressBar(boolean isShowProgressBar) {
        this.isShowProgressBar = isShowProgressBar;
    }

    public boolean isShowProgressBar() {
        return isShowProgressBar;
    }


    /**
     * Format the Amount entered in the EditText
     *
     * @return string amount eg)) 1.00
     */
    public static String getFormattedAmount(String amount) {
        try {
            if (amount != null) {
                if (amount.length() > 0) {
                    float formattedAmount = Float.parseFloat(amount);
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format(formattedAmount);
                }
            } else return "0.00";
        } catch (Exception e) {
            return "0.00";
        }

        return "0.00";
    }


    /**
     * Function to put comma(,) in between figures of an Amount of money
     **/
    public static String putInComma(String Amount) {
        String output = getFormattedAmount(Amount);
        try {
            if (!TextUtils.isEmpty(output)) {
                if (output.length() >= 6) {
                    double amount = parseDouble(output);
                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    return formatter.format(amount);
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }


    public static String putInComma(Float amnt) {
        String Amount = parseString(amnt);
        return putInComma(Amount);
    }


    public static <T> String convertClassToJson(T transaction) {
        try {
            Gson gson = new Gson();
            //  .serializeNulls().create();
            return gson.toJson(transaction);
        } catch (Exception e) {
            return "";
        }
    }


    public static <T> T fromJson(String obj, Class<T> transaction) {
        try {
            Gson gson = new Gson();
            //  .serializeNulls().create();
            return gson.fromJson(obj, (Type) transaction);
        } catch (Exception e) {
            return null;
        }
    }


    public static <T> String convertModel(T object) {
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(object);
        } catch (Exception e) {
            return "";
        }
    }


    public static Date getDateFromString(String dtStart, String dateFormat) {
        //String dtStart = "2010-10-15T09:27:37Z";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            Date date = format.parse(dtStart);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Date getDateFromString(String dtStart) {
        return getDateFromString(dtStart, "dd/MM/yyyy");
    }


    public static <T> String convertArrayObject(ArrayList<T> object) {
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(object);
        } catch (Exception e) {
            return "";
        }
    }



    /**
     * hide the keyboard
     **/
    public void hideKeyBoard() {
        try {
            mContext.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } catch (Exception e) {
        }
    }


    public ActivityUtils(Activity mContext) {
        this.mContext = mContext;
    }


    /**
     * Function to set the image drawable from Resource to an imageView
     *
     * @param resourceId the id of the resource from drawables or mipMap
     * @param imageView  the ImageView or CircleImageView to which the drawable will be set to
     **/
    public void setImageResource(int resourceId, ImageView imageView) {
        try {
            imageView.setImageDrawable(mContext.getResources().getDrawable(resourceId));
        } catch (OutOfMemoryError er) {
            LOG("Error Parsing image >> " + er.getMessage());
            try {
                imageView.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(
                        mContext.getResources(), resourceId, 350, 380));
            } catch (OutOfMemoryError eb) {
                try {
                    imageView.setImageResource(resourceId);
                } catch (OutOfMemoryError ef) {
                    LOG("Error Parsing image Alternative One >> " + ef.getMessage());
                }

            }
        } catch (Exception e) {
            LOG("Error Parsing image Alternative 2 >> " + e.getMessage());
            try {
                imageView.setImageBitmap(getBitmapFromVectorDrawable(resourceId));
            } catch (Exception r) {
                LOG("Error Parsing image Alternative 3 >> " + r.getMessage());
                try {
                    imageView.setImageResource(resourceId);
                } catch (Exception b) {
                    LOG("Error Parsing image Alternative 4 >> " + b.getMessage());
                }
            }
        }

    }

    /**
     * Function to set the image drawable from Resource to an imageView
     *
     * @param resourceId the id of the resource from drawables or mipMap
     * @param imageView  the ImageView or CircleImageView to which the drawable will be set to
     **/
    public void setImageResource(Context mContext, int resourceId, ImageView imageView) {
        try {
            imageView.setImageDrawable(mContext.getResources().getDrawable(resourceId));
        } catch (OutOfMemoryError er) {
            LOG("Error Parsing image >> " + er.getMessage());
            try {
                imageView.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(
                        mContext.getResources(), resourceId, 350, 380));
            } catch (OutOfMemoryError eb) {
                try {
                    imageView.setImageResource(resourceId);
                } catch (OutOfMemoryError ef) {
                    LOG("Error Parsing image Alternative One >> " + ef.getMessage());
                }

            }
        } catch (Exception e) {
            LOG("Error Parsing image Alternative 2 >> " + e.getMessage());
            try {
                imageView.setImageBitmap(getBitmapFromVectorDrawable(resourceId));
            } catch (Exception r) {
                LOG("Error Parsing image Alternative 3 >> " + r.getMessage());
                try {
                    imageView.setImageResource(resourceId);
                } catch (Exception b) {
                    LOG("Error Parsing image Alternative 4 >> " + b.getMessage());
                }
            }
        }

    }


    public Bitmap getBitmapFromVectorDrawable(int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    public static double parseDouble(String value) {
        try {
            if (value != null) {
                return Double.parseDouble(value);
            } else
                return 0.00;
        } catch (Exception e) {
            return 0.00;
        }
    }


    public static int parseInt(String value) {
        try {
            if (value != null) {
                return Integer.parseInt(value);
            } else
                return -1;
        } catch (Exception e) {
            return -1;
        }
    }


    public static Float parseFloat(String value) {
        try {
            if (value != null) {
                return Float.parseFloat(value);
            } else
                return 0.00f;
        } catch (Exception e) {
            return 0.00f;
        }
    }


    public static String parseString(double value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String parseString(float value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String parseString(char value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "0.00";
        }
    }


    public static String parseString(int value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "0";
        }
    }


    public void setDate(EditText editText) {
        int mDay, mMonth, mYear;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(mContext, (view, year, month, dayOfMonth) ->
                editText.setText(getSelectedDate(year, month, dayOfMonth)), mYear, mMonth, mDay);

        pickerDialog.show();
    }

    public void setDateTodayNbefore(EditText editText) {
        int mDay, mMonth, mYear;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(mContext, (view, year, month, dayOfMonth) ->
                editText.setText(getSelectedDate(year, month, dayOfMonth)), mYear, mMonth, mDay);

        pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        pickerDialog.show();
    }

    public void setDateTodayNafter(EditText editText) {
        int mDay, mMonth, mYear;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(mContext, (view, year, month, dayOfMonth) ->
                editText.setText(getSelectedDate(year, month, dayOfMonth)), mYear, mMonth, mDay);

        pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        pickerDialog.show();
    }


    /**
     * Get today's date from configured date on device
     *
     * @return Today's Date eg: 04.12.2017
     **/
    public static String getDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    public String getSelectedDate(int year, int monthOfYear, int dayOfMonth) {
        String newDay = dayOfMonth + "";
        String newMonth = String.valueOf(monthOfYear + 1);
        String selectedDate = "";


        if (newDay.length() == 1) {
            selectedDate = "0" + dayOfMonth + "/" + newMonth + "/" + year;
        }

        if (newMonth.length() == 1) {
            selectedDate = dayOfMonth + "/" + "0" + newMonth + "/" + year;
        }

        if (newDay.length() == 1 && newMonth.length() == 1) {
            selectedDate = "0" + dayOfMonth + "/" + "0" + newMonth + "/" + year;
        }

        if (newDay.length() > 1 && newMonth.length() > 1) {
            selectedDate = dayOfMonth + "/" + newMonth + "/" + year;
        }

        if (newDay.length() > 1 && newMonth.length() == 1) {
            selectedDate = dayOfMonth + "/" + "0" + newMonth + "/" + year;
        }

        if (newDay.length() == 1 && newMonth.length() > 1) {
            selectedDate = "0" + dayOfMonth + "/" + newMonth + "/" + year;
        }

        return selectedDate;
        //return getNewFormattedDate(selectedDate, "dd/MM/yyyy","yyyy-MM-dd");
    }


    private String getSelectedTime(int hour, int minutes) {
        String newHour = String.valueOf(hour);
        String newMinutes = String.valueOf(minutes);
        String selectedTime = "";


        if (newHour.length() == 1)
            newHour = "0" + newHour;

        if (newMinutes.length() == 1)
            newMinutes = "0" + newMinutes;


        selectedTime = newHour + ":" + newMinutes + ":00";
        LOG("Time is >> " + selectedTime);

        return selectedTime;
    }


    public static void LOG(String mess) {
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug"))
            Log.d("HTTP", mess);
    }


    /**
     * Add Fragment to an Activity
     *
     * @param fragment FRAGMENT to be added
     **/
    public void addFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.container, fragment, backStackName).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }


    /**
     * Replace the current Fragment With new Fragment With no Animation
     **/
    public void replaceFragment(Fragment newFragment) {
        this.replaceFragment(newFragment, null);
    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null)
            fragment.setArguments(bundle);

        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            LOG("Error chamging fragment " + e.getMessage());
        }
    }

    public void replaceFragments(Fragment fragment) {
        if (fragment != null && fragmentManager != null) {
            String tag = fragment.getClass().getName();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right);
            transaction.addToBackStack(tag);
            transaction.replace(R.id.container, fragment, tag);

            transaction.commit();
        }
    }


    public void showUserRestrictedAccessDialog() {
        this.alertMessage(
                "Access Denied.",
                "You have been restricted access to perform this operation. " +
                        "Please contact your immediate supervisor to get more clarification.",
                "OK",
                "",
                false,
                null);
    }

    public void showUserWarningDialog() {
        this.alertMessage(
                "Warning!",
                "You are already an Admin to this business. " +
                        "Changing your role will limit your access to this business.\nDo you want to proceed?",
                "Proceed",
                "",
                false,
                null);
    }

    /**
     * Display the information dialog
     **/
    public void alertMessage(String title, String message, String positiveButtonText, String negButtonText, boolean isShowNegButton, final OnItemCallBack callBack) {
        try {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(mContext);
            }

            builder.setTitle(title);
            builder.setMessage(message);

            builder.setCancelable(false);

            builder.setPositiveButton(positiveButtonText, (dialog, which) -> {
                dialog.dismiss();
                if (callBack != null)
                    callBack.execute(SUCCESS);
            });


            if (isShowNegButton)
                builder.setNegativeButton(negButtonText, (dialog, which) -> {
                    dialog.dismiss();
                    if (callBack != null)
                        callBack.execute(FAILURE);
                });

            builder.show();
        } catch (NullPointerException e) {
            LOG("Error >> " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG("Error >> " + e.getMessage());
        } catch (Exception e) {

        }
    }


    public void alertMessage(String mess, String posText, String negText, OnItemCallBack callBack) {
        this.alertMessage("", mess, posText, negText, true, callBack);
    }


    /**
     * Function to trim the phone number enetered, eg: to take away the leading zero(0) from 0245....
     * and appended to the country ISO code
     **/
    public static String getFormattedPhoneNumber(String phone) {
        String code = getCountryIsoCode();
        if (phone == null || TextUtils.isEmpty(phone))
            return "";

        if (phone.startsWith("0") && phone.length() == 10)
            return code + phone.substring(phone.length() - 9);

        if (phone.startsWith(code) && phone.length() == 12)
            return phone;

        if (!phone.startsWith("0") && phone.length() < 10)
            return code + phone;

        if (phone.contains("+"))
            phone = phone.replace("+", "");

        return phone;
    }

    private static String getCountryIsoCode() {
        return "233";
    }


    /**
     * Function to trim the phone number enetered, eg: to take away the leading zero(0) from 0245....
     * and appended to the country ISO code
     **/
    public static String getSinchFormattedPhoneNumber(String phone) {
        String code = getCountryIsoCode();
        if (phone == null || TextUtils.isEmpty(phone))
            return "";

        phone = phone.replace(" ", "").trim();


        if (phone.startsWith("0") && phone.length() == 10)
            return "+" + code + phone.substring(phone.length() - 9);

        if (!phone.startsWith("+") && phone.length() == 12)
            return "+" + phone;

        return phone;
    }


    public static String getNewFormattedDate(String datetoFromat, String oldFormat, String formatWanted) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(oldFormat);

            try {
                Date date = format.parse(datetoFromat);
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                datetoFromat = String.valueOf(df.format(formatWanted, date));

            } catch (ParseException e) {
                LOG("Time Stamp: Error here>> " + e.getMessage());
            }
        } catch (Exception e) {
            LOG("Time Stamp: Error here>> " + e.getMessage());
        }
        return datetoFromat;
    }





    protected void openCloseActivity(Class className) {
        if (mContext != null)
            try {
                Intent intent = new Intent(mContext, className);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

                mContext.finish();
            } catch (IllegalStateException e) {
                LOG("Error " + e.getLocalizedMessage());
            } catch (NullPointerException e) {
                LOG("Error " + e.getLocalizedMessage());
            } catch (RuntimeException e) {
                LOG("Error " + e.getLocalizedMessage());

            } catch (Exception e) {

            }
    }


    public void showNotification(String title, String message, Class clazz) {
        try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_NAME, NOTIFICATION_IMPORTANCE);
                    channel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);

                    NotificationManager manager = mContext.getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(mContext, clazz);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
                builder.setContentTitle(title);
                builder.setContentText(message);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setAutoCancel(true);
               //TODO Work on builder.setContentIntent(pendingIntent);

                NotificationManagerCompat notification = NotificationManagerCompat.from(mContext);
                notification.notify(NOTIFICATION_ID, builder.build());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showNotification(String message) {
        this.showNotification("Covid-19", message, MainActivity.class);
    }

    public void showNotification(String title,String message) {
        this.showNotification(title, message,MainActivity.class);
    }



}
