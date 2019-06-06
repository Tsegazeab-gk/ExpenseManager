package com.tsegazeabg.com.expensemanager.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tsegazeabg.com.expensemanager.R;

import java.io.InputStream;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        InputStream iFile = getResources().openRawResource(R.raw.help);

        TextView helpText = (TextView) findViewById(R.id.txt_help_txt);
        String strFile = inputStreamToString(iFile);
        helpText.setText(strFile);
    }

    private String inputStreamToString(InputStream iFile) {
        String str = iFile.toString();
        return str;
    }
}
