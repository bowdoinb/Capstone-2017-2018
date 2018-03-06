package com.example.blakebowdoin.findme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        textView = (TextView) findViewById(R.id.textView);
        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        textView.setText(TempHolder);


        //Bundle recdData = getIntent().getExtras();
        //String myVal = recdData.getString("my.package.dataToPass");


    }
}
