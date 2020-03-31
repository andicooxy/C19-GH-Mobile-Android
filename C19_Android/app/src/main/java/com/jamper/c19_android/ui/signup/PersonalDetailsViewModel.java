package com.jamper.c19_android.ui.signup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;


import com.jamper.c19_android.app.AppApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jamper.c19_android.utils.ActivityUtils.getCurrentYear;

public class PersonalDetailsViewModel extends ViewModel {

    private String firstName;
    private String month;
    private String day;
    private String year;
    private String birthDate;
    private String sex;
    private String eduLevel;
    private String maritalStatus;
    private String livingAlone;

    @BindingAdapter(value = {"selectedItem", "entryId"}, requireAll = false)
    public static void setEntry(Spinner spinner, String selectedItem, int id){
        List<String> spinnerArray = new ArrayList<>();

        try {
            if (id == -1) {
               spinnerArray.addAll(AppApplication.getApplication().getYearList());
            } else {
                String[] currencyArr = spinner.getContext().getResources().getStringArray(id);
                spinnerArray = Arrays.asList(currencyArr);
            }

            if ( spinnerArray.size() == 0)
                return;

            ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            int index = spinnerArray.indexOf(selectedItem);
            spinner.setSelection(index != -1 ? index : 0);

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getLivingAlone() {
        return livingAlone;
    }
}
