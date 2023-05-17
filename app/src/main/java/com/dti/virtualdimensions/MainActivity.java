package com.dti.virtualdimensions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Calcs calcs;
    Button VP_tab ;
    Button VD_tab;
    Button SAVE;
    Button tutorial_tab;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            UpdateUI();
        }
    };
    int counter;
    public static final String SAVEfNAME = "save.txt";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runnable load=()->{

            loadData();
        };
        vars.dims.add(new Dim(1, 10f));
        vars.dims.add(new Dim(100, 50f));
        vars.dims.add(new Dim(10000, 100f));
        vars.dims.add(new Dim(1000000, 1E3));
        vars.dims.add(new Dim(100000000, 1E7));
        vars.dims.add(new Dim(10000000000L, 1E11));
        vars.dims.add(new Dim(-1L, 1E308));
//        for (int i = 0; i < 7; i++) {
//            vars.dims.get(i).update();
//        }
        Thread loadT = new Thread(load);
        loadT.start();
        setContentView(R.layout.activity_main);
        VP_tab = findViewById(R.id.vp_tab_open);
        VD_tab = findViewById(R.id.tab_VD);
        SAVE=findViewById(R.id.SAVE);
        VD_tab.setOnClickListener(v->VD_openFrg());
        VP_tab.setOnClickListener(v -> VP_openFrg());
        tutorial_tab=findViewById(R.id.tutorial_tab);
        VD_tab.setEnabled(false);
        calcs = new Calcs();
//        saveData();


        if (!calcs.ct.isAlive()){
        calcs.ct.start();}
        if (!calcs.fct.isAlive()){
            calcs.fct.start();
        }
        if (!calcs.prt.isAlive()){
            calcs.prt.start();
        }
        SAVE.setOnClickListener(v->{
            Runnable save=()->{
                saveData();
            };
            Thread saveT = new Thread(save);
            saveT.start();
            Toast.makeText(this, getResources().getText(R.string.gameSaved), Toast.LENGTH_LONG).show();
        });
        tutorial_tab.setOnClickListener(v->getSupportFragmentManager().beginTransaction().replace(R.id.container, new tutorial()).commit());
//        Calcs.ut.start();
        Runnable Checks = () -> {

                while (Thread.currentThread().isAlive()) {
                    //Log.d(TAG, "PRESSED!!!");
                    try {
                        Thread.sleep(Math.round(1000 / vars.FPS));
                        handler.sendEmptyMessage(1);
                        counter++;
                        if (counter>=vars.FPS*30){
                            counter=0;
                            Runnable save=()->{
                                saveData();
                            };
                            Thread saveT = new Thread(save);
                            saveT.start();

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        };
        Thread checks = new Thread(Checks);
        checks.start();

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        Runnable save=()->{
            saveData();
        };
        Thread saveT = new Thread(save);
        saveT.start();
    }
    @Override
    protected void onStop() {

        super.onStop();
        Runnable save=()->{
            saveData();
        };
        Thread saveT = new Thread(save);
        saveT.start();
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
    public void saveData(){

        FileOutputStream fos = null;
        try {


            fos = openFileOutput(SAVEfNAME, MODE_PRIVATE);
            fos.write("SAVEFILE#".getBytes());
            fos.write((String.valueOf(vars.isVPPhaseDestroyed)+"#").getBytes());
            for (int i = 0; i < 7; i++) {
//                vars.dims.get(i).update();
                fos.write((vars.dims.get(i).toString()+"#").getBytes());
            }
            fos.write((vars.v_VP.toString()+"#").getBytes());
            fos.write((vars.v_tickspeed.toString()+"#").getBytes());
            fos.write((vars.v_tickspeedBought.toString()+"#").getBytes());
            fos.write((vars.v_tickspeedPrice.toString()+"#").getBytes());
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void loadData(){
        FileInputStream fin = null;

        try {
            fin = openFileInput(SAVEfNAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            String[] data = text.split("#");
            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
            }
            if (data[0].equals("SAVEFILE")) {
                vars.isVPPhaseDestroyed = Boolean.parseBoolean(data[1]);
                vars.dims.set(0, Dim.fromString(data[2]));
                vars.dims.set(1, Dim.fromString(data[3]));
                vars.dims.set(2, Dim.fromString(data[4]));
                vars.dims.set(3, Dim.fromString(data[5]));
                vars.dims.set(4, Dim.fromString(data[6]));
                vars.dims.set(5, Dim.fromString(data[7]));
                vars.dims.set(6, Dim.fromString(data[8]));
                vars.v_VP= new BigDecimal(data[9]);
                vars.v_tickspeed=new BigDecimal(data[10]);
                vars.v_tickspeedBought=new BigDecimal(data[11]);
                vars.v_tickspeedPrice=new BigDecimal(data[12]);
            }
            else{
                saveData();
                Log.w(TAG, "loadData: NO DATA!");
                System.out.println("data[0]: "+data[0]);
            }
        }
        catch(IOException ex) {
            saveData();
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch (ArrayIndexOutOfBoundsException ae){
            saveData();
//            loadData();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}