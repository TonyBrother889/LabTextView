package com.mylab;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LabTextView labTextView = (LabTextView) findViewById(R.id.slantedTextView);
        labTextView.setText("lab1");
        labTextView.setLabColor(R.color.col_81bb58);


        LabTextView labText2 = (LabTextView) findViewById(R.id.labText2);
        labText2.setText("lab2");
        labText2.setLabColor(R.color.col_1b91ff);

        LabTextView labText3 = (LabTextView) findViewById(R.id.labText3);
        labText3.setText("lab2lab2lab2");
        labText3.setLabColor(R.color.col_d72f2f);
    }
}
