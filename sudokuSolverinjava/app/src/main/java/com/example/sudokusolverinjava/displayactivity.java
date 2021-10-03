package com.example.sudokusolverinjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayactivity extends AppCompatActivity {
    private static final Object URL = "";
    Intent intent;
    Bitmap message;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayactivity);
        Button back = findViewById(R.id.back);
        Button change = findViewById(R.id.change);
        intent = getIntent();
        message = intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        //mnistClassifier = new DigitsDetector(this);
        //ImageView ivShowImage = findViewById(R.id.displayImageView);
        //onDetectClicked();
        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //onDetectClicked();
//                TextView[] res = new TextView[9];
//                for(int i = 1; i <= 9; i++) {
//                    String ida="digit"+i;
//                    int resID = getResources().getIdentifier(ida, "id", getPackageName());
//                    res[i] = ((TextView) findViewById(resID));
//                }
//                CharSequence digit=res.getText();
//                Log.d(TAG,digit.toString());
//                String newdigit=digit.toString();
//                if(newdigit=="6"){
//
//
//                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}




