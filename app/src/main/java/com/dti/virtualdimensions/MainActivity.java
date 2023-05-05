package com.dti.virtualdimensions;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button VP_tab ;
    Button VD_tab;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VP_tab = findViewById(R.id.vp_tab_open);
        VD_tab = findViewById(R.id.tab_VD);
        VD_tab.setOnClickListener(v->VD_openFrg());
        VP_tab.setOnClickListener(v -> VP_openFrg());
        VD_tab.setEnabled(false);
        if (!Calcs.ct.isAlive()){
        Calcs.ct.start();}
        if (!Calcs.fct.isAlive()){
            Calcs.fct.start();
        }
        if (!Calcs.prt.isAlive()){
            Calcs.prt.run();
        }
//        Calcs.ut.start();
        Runnable Checks = () -> {
            while (true) {
                while (true) {
                    //Log.d(TAG, "PRESSED!!!");
                    try {
                        Thread.sleep(Math.round(1000 / vars.FPS));
                        if (vars.isVPPhaseDestroyed){
                            VP_tab.setEnabled(false);
                            VP_tab.setBackgroundColor(getResources().getColor(R.color.abyss));
                            VD_tab.setEnabled(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Checks.run();
    }

    public void VP_openFrg(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new VP_Frg()).commit();
    }
    public void VD_openFrg(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new VD_frg()).commit();
    }

}