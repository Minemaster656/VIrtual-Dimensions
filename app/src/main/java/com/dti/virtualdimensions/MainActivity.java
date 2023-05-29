package com.dti.virtualdimensions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Calcs calcs;
    Button VP_tab;
    Button VD_tab;
    Button SAVE;
    Button tutorial_tab;
    Button settingsTab;
    Button QU_tab;
    Button extraTab;
    Date dt = new Date();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            UpdateUI();
        }
    };

    int counter;
    public static final String SAVEfNAME = "save.txt";

    //    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(Utils.bd2txt(Utils.loge(new BigDecimal("1e308"))));
        Runnable load = () -> {

            loadData();
        };
        vars.dims.add(new Dim(1, 10f));
        vars.dims.add(new Dim(100, 50f));
        vars.dims.add(new Dim(10000, 100f));
        vars.dims.add(new Dim(1000000, 1E3));
        vars.dims.add(new Dim(100000000, 1E7));
        vars.dims.add(new Dim(10000000000L, 1E11));
        vars.dims.add(new Dim(-1L, 1E308));

        for (int i = 0; i < 6; i++) {
            vars.dimAutoUnlocks[i] = false;
            vars.dimAutoToggles[i] = true;
        }
        vars.dimAutoPrices[0] = BigDecimal.valueOf(1E50);
        vars.dimAutoPrices[1] = BigDecimal.valueOf(1E60);
        vars.dimAutoPrices[2] = BigDecimal.valueOf(1E70);
        vars.dimAutoPrices[3] = BigDecimal.valueOf(1E80);
        vars.dimAutoPrices[4] = BigDecimal.valueOf(1E90);
        vars.dimAutoPrices[5] = BigDecimal.valueOf(1E100);
        vars.extraAutoPrices.add(BigDecimal.valueOf(1E75));
        vars.extraAutoPrices.add(BigDecimal.valueOf(1E115));

        for (int i = 0; i < vars.extraAutoCount; i++) {
            vars.extraAutoUnlocks.add(false);//vars.extraAutoToggles.add(true);
            vars.extraAutoToggles.add(true);
        }
        for (int i = 0; i < 6; i++) {
            vars.dimAutoUnlocks[i] = false;
            if (vars.isDebugBuild) {
                vars.dimAutoUnlocks[i] = true;
            }
        }


//        for (int i = 0; i < 7; i++) {
//            vars.dims.get(i).update();
//        }
        Thread loadT = new Thread(load);
        loadT.start();
        setContentView(R.layout.activity_main);
        VP_tab = findViewById(R.id.vp_tab_open);
        VD_tab = findViewById(R.id.tab_VD);
        QU_tab = findViewById(R.id.tab_quarks);
        settingsTab = findViewById(R.id.settingsTab);
        SAVE = findViewById(R.id.SAVE);
        extraTab = findViewById(R.id.extra);
        VD_tab.setOnClickListener(v -> VD_openFrg());
        VP_tab.setOnClickListener(v -> VP_openFrg());
        extraTab.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.container, new extraTabs()).commit());
        settingsTab.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.container, new settings()).commit());
        tutorial_tab = findViewById(R.id.tutorial_tab);
        VD_tab.setEnabled(false);
        calcs = new Calcs();
        QU_tab.setEnabled(false);
        QU_tab.setVisibility(View.GONE);
//        saveData();


        if (!calcs.ct.isAlive()) {
            calcs.ct.start();
        }
        if (!calcs.fct.isAlive()) {
            calcs.fct.start();
        }
        if (!calcs.prt.isAlive()) {
            calcs.prt.start();
        }
        SAVE.setOnClickListener(v -> {
            Runnable save = () -> {
                saveData();
            };
            Thread saveT = new Thread(save);
            saveT.start();
            Toast.makeText(this, getResources().getText(R.string.gameSaved), Toast.LENGTH_LONG).show();
        });
        tutorial_tab.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.container, new tutorial()).commit());
        QU_tab.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.container, new Quarks()).commit());
//        Calcs.ut.start();
        Runnable Checks = () -> {

            while (Thread.currentThread().isAlive()) {
                //Log.d(TAG, "PRESSED!!!");
                try {
                    Thread.sleep(Math.round(1000 / vars.FPS));
                    handler.sendEmptyMessage(1);
                    counter++;
                    if (counter >= vars.FPS * 30 & vars.doSave) {
                        counter = 0;
                        Runnable save = () -> {
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
        if (vars.doSave) {
            Runnable save = () -> {
                saveData();
            };
            Thread saveT = new Thread(save);
            saveT.start();
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        if (vars.doSave) {
            Runnable save = () -> {
                saveData();
            };
            Thread saveT = new Thread(save);
            saveT.start();
        }
    }

    public void UpdateUI() {
        if (vars.isVPPhaseDestroyed) {
            VP_tab.setEnabled(false);
            VP_tab.setBackgroundColor(getResources().getColor(R.color.abyss));
//            VP_tab.setVisibility(View.INVISIBLE);
            VP_tab.setVisibility(View.GONE);
            VD_tab.setEnabled(true);
            if (vars.q_isUnlocked | vars.quarksOnAnnihilate.compareTo(BigDecimal.valueOf(1)) >= 0) {
                QU_tab.setEnabled(true);
                QU_tab.setVisibility(View.VISIBLE);
//                System.out.println(Utils.bd2txt(vars.quarksOnAnnihilate));
//                System.out.println(vars.q_isUnlocked);
            }
        }
        String data_extraTab = vars.findInvoke_FindSender_ReturnData("tab_extra", true);
        if (data_extraTab != null) {
            if (data_extraTab.equals("open fragment_auto")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Automatics()).commit();
            }
        }
        String data_loader = vars.findInvoke_FindSender_ReturnData("DATA_LOADER", true);
        if (data_loader!=null){
            if (data_loader.equals("calc offline")){
                //if (vars.offTime>=1000){
                    Calcs.calcOffline(this);
                    Log.d(TAG, "Called off calc!");
                //}
            }
        }
        String data_calc_off = vars.findInvoke_FindSender_ReturnData("CALC_offline_ddata", true);
        if (data_calc_off!=null){
            String[] sdata = data_calc_off.split("#");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(this.getResources().getString(R.string.offline));
            builder.setMessage(
                    getStr(R.string.off_time)+ " "+Utils.millsToTime(vars.offTime)+"\n"+
                    getStr(R.string.VP_tabName) + ": " + sdata[0] + "→" + Utils.bd2txt(vars.v_VP) + "\n" +
                            getStr(R.string.dim1) + " " + getStr(R.string.word_count) + ": " + sdata[1] + "→" + Utils.bd2txt(vars.dims.get(0).count) + "\n" +
                            getStr(R.string.dim6) + " " + getStr(R.string.word_count) + ": " + sdata[2] + "→" + Utils.bd2txt(vars.dims.get(5).count) + "\n" +
                            getStr(R.string.Tickspeed_bought) + ": " + sdata[3] + "→" + Utils.bd2txt(vars.v_tickspeedBought) + "\n" +
                            getStr(R.string.collapse)+" " +getStr(R.string.word_count) + ": " + sdata[4] + "→" + Utils.bd2txt(vars.vCollapse_count) + "\n" +
                            getStr(R.string.quarks)+" " +getStr(R.string.word_count) + ": " + sdata[5] + "→" + Utils.bd2txt(vars.quarks) + "\n"
            );
            builder.setPositiveButton(getStr(R.string.word_close), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d(TAG, "dialog shown");
        }
    }
    public String getStr(int id) {
        return getResources().getString(id);
    }

    public void VP_openFrg() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new VP_Frg()).commit();
    }

    public void VD_openFrg() {
        VDims vd = new VDims();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, vd).commit();
        //calcs.setSecondFragment(vd);
    }


    public void saveData() {

        FileOutputStream fos = null;
        try {

            Date dt = new Date();
            fos = openFileOutput(SAVEfNAME, MODE_PRIVATE);
            fos.write("SAVEFILE#".getBytes());
            fos.write((String.valueOf(vars.isVPPhaseDestroyed) + "#").getBytes());
            for (int i = 0; i < 7; i++) {
//                vars.dims.get(i).update();
                fos.write((vars.dims.get(i).toString() + "#").getBytes());
            }
            fos.write((Utils.bd2txt(vars.v_VP) + "#").getBytes());
            fos.write((vars.v_tickspeed.toString() + "#").getBytes());
            fos.write((vars.v_tickspeedBought.toString() + "#").getBytes());
            fos.write((vars.v_tickspeedPrice.toString() + "#").getBytes());
            fos.write((vars.vCollapse_count.toString() + "#").getBytes());
            fos.write((vars.vCollapse_mlt.toString() + "#").getBytes());
            fos.write((vars.vCollapse_price.toString() + "#").getBytes());
            fos.write((vars.vCollapse_priceMlt.toString() + "#").getBytes());
            fos.write((vars.vCollapse_priceMltMlt.toString() + "#").getBytes());
            for (int i = 0; i < 6; i++) {
                fos.write((vars.dimAutoUnlocks[i] + "#").getBytes());
            }
            for (int i = 0; i < 6; i++) {
                fos.write((vars.dimAutoToggles[i] + "#").getBytes());
            }
            fos.write((vars.extraAutoUnlocks.get(0)+"#").getBytes());
            fos.write((vars.extraAutoUnlocks.get(1)+"#").getBytes());
            fos.write((vars.extraAutoToggles.get(0)+"#").getBytes());
            fos.write((vars.extraAutoToggles.get(1)+"#").getBytes());

            fos.write((Utils.bd2txt(vars.quarks)+"#").getBytes());

            fos.write((vars.q_isVoidCleared +"#").getBytes());
            fos.write((vars.q_isUnlocked +"#").getBytes());
            fos.write((dt.getTime()+"#").getBytes());

        } catch (IOException ex) {

            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {

                //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadData() {
        FileInputStream fin = null;
        Date dt = new Date();
        try {
            fin = openFileInput(SAVEfNAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            String[] data = text.split("#");
            for (int i = 0; i < data.length; i++) {
//                System.out.println(data[i]);
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
                vars.v_VP = new BigDecimal(data[9]);
                vars.v_tickspeed = new BigDecimal(data[10]);
                vars.v_tickspeedBought = new BigDecimal(data[11]);
                vars.v_tickspeedPrice = new BigDecimal(data[12]);
                vars.vCollapse_count = new BigDecimal(data[13]);
                vars.vCollapse_mlt = new BigDecimal(data[14]);
                vars.vCollapse_price = new BigDecimal(data[15]);
                vars.vCollapse_priceMlt = new BigDecimal(data[16]);
                vars.vCollapse_priceMltMlt = new BigDecimal(data[17]);

                vars.dimAutoUnlocks[0] = Boolean.parseBoolean(data[18]);
                vars.dimAutoUnlocks[1] = Boolean.parseBoolean(data[19]);
                vars.dimAutoUnlocks[2] = Boolean.parseBoolean(data[20]);
                vars.dimAutoUnlocks[3] = Boolean.parseBoolean(data[21]);
                vars.dimAutoUnlocks[4] = Boolean.parseBoolean(data[22]);
                vars.dimAutoUnlocks[5] = Boolean.parseBoolean(data[23]);

                vars.dimAutoToggles[0] = Boolean.parseBoolean(data[24]);
                vars.dimAutoToggles[1] = Boolean.parseBoolean(data[25]);
                vars.dimAutoToggles[2] = Boolean.parseBoolean(data[26]);
                vars.dimAutoToggles[3] = Boolean.parseBoolean(data[27]);
                vars.dimAutoToggles[4] = Boolean.parseBoolean(data[28]);
                vars.dimAutoToggles[5] = Boolean.parseBoolean(data[29]);

                vars.extraAutoUnlocks.set(0, Boolean.parseBoolean(data[30]));
                vars.extraAutoUnlocks.set(1, Boolean.parseBoolean(data[31]));
                vars.extraAutoToggles.set(0, Boolean.parseBoolean(data[32]));
                vars.extraAutoToggles.set(1, Boolean.parseBoolean(data[33]));

                vars.quarks=new BigDecimal(data[34]);

                vars.q_isVoidCleared=Boolean.parseBoolean(data[35]);
                vars.q_isUnlocked=Boolean.parseBoolean(data[36]);

                vars.offTime=dt.getTime()-Long.parseLong(data[37]);
                Log.d(TAG, "off time: "+vars.offTime);
                vars.invoke("DATA_LOADER", "MAIN", "calc offline");




            } else {
                saveData();
                Log.e(TAG, "loadData: NO DATA!");
                System.out.println("data[0]: " + data[0]);
            }
        } catch (IOException ex) {
            saveData();
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (ArrayIndexOutOfBoundsException ae) {
            saveData();
//            loadData();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void DELETE_SAVE() {
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(SAVEfNAME, MODE_PRIVATE);
            fos.write("SAVE FILE DELETED!#".getBytes());
        } catch (IOException ex) {

            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {

                //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}