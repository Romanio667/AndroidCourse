package com.example.toolsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrillDetailActivity extends AppCompatActivity {

    private TextView TextViewTittle;
    private TextView TextViewInfo;
    private ImageView ImageViewDrill;
    private  String LogoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_detail);

        TextViewTittle=findViewById(R.id.textViewTittle1);
        TextViewInfo=findViewById(R.id.textViewInfo);
        ImageViewDrill=findViewById(R.id.imageViewDrill);

        Intent intent=getIntent();
        if(intent.hasExtra("tittle")&&intent.hasExtra("info")&&intent.hasExtra("resId")){

            String tittle = intent.getStringExtra("tittle");
            String info=intent.getStringExtra("info");
            int resID = intent.getIntExtra("resId",-1);
            TextViewTittle.setText(tittle);
            TextViewInfo.setText(info);
            ImageViewDrill.setImageResource(resID);
        }else{
            Intent backToCategory=new Intent(this,DrillCategoryActivity.class);
            startActivity(backToCategory);
        }
    }
}