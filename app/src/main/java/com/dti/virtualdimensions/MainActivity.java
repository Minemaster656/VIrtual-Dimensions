package com.dti.virtualdimensions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.vp_tab_open);

        button.setOnClickListener(v -> VP_openFrg());
        if (!Calcs.ct.isAlive()){
        Calcs.ct.start();}
        if (!Calcs.fct.isAlive()){
            Calcs.fct.start();
        }
//        Calcs.ut.start();
    }
    public void VP_openFrg(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new VP_Frg()).commit();
    }

}