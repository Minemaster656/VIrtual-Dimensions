package com.dti.virtualdimensions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Calcs calcs;
    Button VP_tab ;
    Button VD_tab;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            UpdateUI();
        }
    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vars.dims.add(new Dim(1, 10f));
        vars.dims.add(new Dim(100, 1.2f));
        vars.dims.add(new Dim(10000, 1.3f));
        vars.dims.add(new Dim(1000000, 1.4f));
        vars.dims.add(new Dim(100000000, 1.5f));
        vars.dims.add(new Dim(10000000000L, 1.6f));
        vars.dims.add(new Dim(-1L, 1E308));
        setContentView(R.layout.activity_main);
        VP_tab = findViewById(R.id.vp_tab_open);
        VD_tab = findViewById(R.id.tab_VD);
        VD_tab.setOnClickListener(v->VD_openFrg());
        VP_tab.setOnClickListener(v -> VP_openFrg());
        VD_tab.setEnabled(false);
        calcs = new Calcs();

        if (!calcs.ct.isAlive()){
        calcs.ct.start();}
        if (!calcs.fct.isAlive()){
            calcs.fct.start();
        }
        if (!calcs.prt.isAlive()){
            calcs.prt.start();
        }
//        Calcs.ut.start();
        Runnable Checks = () -> {

                while (Thread.currentThread().isAlive()) {
                    //Log.d(TAG, "PRESSED!!!");
                    try {
                        Thread.sleep(Math.round(1000 / vars.FPS));
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        };
        Thread checks = new Thread(Checks);
        checks.start();
    }
    public void UpdateUI(){
        if (vars.isVPPhaseDestroyed){
            VP_tab.setEnabled(false);
            VP_tab.setBackgroundColor(getResources().getColor(R.color.abyss));
            VD_tab.setEnabled(true);
        }
    }

    public void VP_openFrg(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new VP_Frg()).commit();
    }

    public void VD_openFrg(){
        VDims vd = new VDims();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, vd).commit();
        //calcs.setSecondFragment(vd);
    }

}