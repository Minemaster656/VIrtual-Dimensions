package com.dti.virtualdimensions;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;


public class settings extends Fragment {
//    public static final String SAVEfNAME = "save.txt";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };
    Button DELETE_SAVE;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DELETE_SAVE = view.findViewById(R.id.DELETE_SAVE_FILE);
        DELETE_SAVE.setOnLongClickListener(v->{
            DELETE_SAVE();
            String ttext=getResources().getString(R.string.SAVE_DELETED);
            Toast.makeText(requireContext(), ttext, Toast.LENGTH_LONG).show();

            return false;
        });




    }

    public settings() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }
    public void DELETE_SAVE(){
        FileOutputStream fos = null;

        try {
            fos = requireContext().openFileOutput(MainActivity.SAVEfNAME, Context.MODE_PRIVATE);
            fos.write("SAVE FILE DELETED!#".getBytes());
            vars.doSave=false;
        }
        catch(IOException ex) {

            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}