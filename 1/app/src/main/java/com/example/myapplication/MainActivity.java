package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText pass;
    private Button btn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void  addListenerOnButton(){
        pass=(EditText)findViewById(R.id.editTextTextPassword);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        btn.setText("Done");
                        btn.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        Toast.makeText(
                                MainActivity.this, pass.getText(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
    }
}