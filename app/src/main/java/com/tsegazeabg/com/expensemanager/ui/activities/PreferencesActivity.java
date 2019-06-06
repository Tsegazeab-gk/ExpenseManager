package com.tsegazeabg.com.expensemanager.ui.activities;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.tsegazeabg.com.expensemanager.R;

/**
 * Created by user on 6/24/2016.
 */
public class PreferencesActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (preference instanceof ListPreference) {
            String stringValue = newValue.toString();
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
       /* else if(preference instanceof CheckBoxPreference){
            CheckBoxPreference chkPreference=(CheckBoxPreference) preference;
         // Boolean prefIndex= (Boolean) stringValue; // chkPreference.integer.parseInt(stringValue);
           // Boolean stringValue=(Boolean) newValue;
            /*
            if (chkPreference.isChecked()){
                preference.setSummary(chkPreference.getSummaryOn());
            }else
            {
                preference.setSummary(chkPreference.getSummaryOff());
            }
            String stringValue=newValue.toString();
           // int prefIndex=chkPreference.findIndexOfValue(stringValue);

            if (chkPreference.isChecked()) {
                preference.setSummary(stringValue);
            }
        }
    */
        else {
            String stringValue = newValue.toString();
            preference.setSummary(stringValue);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_lang_key)));
        //bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_pwd_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_cal_key)));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

}
