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
        labTextView.setText("111111");
        labTextView.setTextColor(R.color.col_81bb58);
    }
}
