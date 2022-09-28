package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

    private EditText editTextMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMsg=findViewById(R.id.editTextMessage);
    }

    public void OnClickSendMessage(View view) {
        String msg=editTextMsg.getText().toString();
        /*Intent intent=new Intent(this,ReceivedMessageActivity.class);
        intent.putExtra("msg",msg);
        startActivity(intent);*/
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        Intent chosenIntent=Intent.createChooser(intent,getString(R.string.chooser_title));
        startActivity(chosenIntent);
    }
}