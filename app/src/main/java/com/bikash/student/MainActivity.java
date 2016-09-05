package com.bikash.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        try {
            FileOutputStream file = openFileOutput("data.txt",MODE_WORLD_READABLE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
            try {
                for(int j=0; j<5; j++){
                    writer.write(0 + " ");
                    writer.write(0 + " ");}
                writer.flush();
                writer.close();
                Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Routine activity
        final TextView routine = (TextView)findViewById(R.id.routine);
        routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Intent i = new Intent(MainActivity.this, Routine.class);
                    startActivity(i);
                }
            }
        );


        final TextView presentance = (TextView)findViewById(R.id.presentance);
        presentance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Presentance.class);
                startActivity(i);
            }
        }
        );


    }


}
