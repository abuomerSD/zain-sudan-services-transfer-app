package com.example.zainsdservicestransferapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkThePermissions();

        final EditText txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        final RadioButton rbtnEasy = (RadioButton) findViewById(R.id.rbtnEasy);
        final RadioButton rbtnShabab = (RadioButton) findViewById(R.id.ebtnShabab);
        final RadioButton rbtnClass = (RadioButton) findViewById(R.id.rbtnClass);
        final RadioButton rbtnWeek = (RadioButton) findViewById(R.id.rbtnWeek);
        final RadioButton rbtnMonth = (RadioButton) findViewById(R.id.rbtnMonth);
        final Button btnSend = (Button) findViewById(R.id.btnSend);
        final TextView tvUssdCode = (TextView) findViewById(R.id.tvUssdCode);
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String phoneNumber = String.valueOf(txtPhoneNumber.getText()) ;

                if (phoneNumber.length() < 1) {
                    Toast.makeText(btnSend.getContext(), "ادخل رقم الهاتف اولا", Toast.LENGTH_LONG).show();
                    return;
                }

                if (phoneNumber.length() < 10) {
                    Toast.makeText(btnSend.getContext(), "ادخل رقم هاتف صحيح", Toast.LENGTH_LONG).show();
                    return;
                }
//                Toast.makeText(btnSend.getContext(),  txtPhoneNumber.getText() ,Toast.LENGTH_LONG).show();
//                Toast.makeText(btnSend.getContext(),  phoneNumber ,Toast.LENGTH_LONG).show();
                String code  = "";
                if(rbtnEasy.isChecked()){
                    if(rbtnWeek.isChecked())
                        code = "140*10*" +  phoneNumber + "*5";
                    else if (rbtnMonth.isChecked())
                        code = "140*10*" +  phoneNumber + "*6";
                }

                if (rbtnShabab.isChecked()){
                    if(rbtnWeek.isChecked())
                        code = "141*10*" +  phoneNumber + "*4";
                    else if (rbtnMonth.isChecked())
                        code = "141*10*" +  phoneNumber + "*5";
                }

                if (rbtnClass.isChecked()){
                    if(rbtnWeek.isChecked())
                        code = "142*10*" +  phoneNumber + "*2";
                    else if (rbtnMonth.isChecked())
                        code = "142*10*" +  phoneNumber + "*3";
                }

                // to debug the code

//                Toast.makeText(btnSend.getContext(),"*"+ code + "#" ,Toast.LENGTH_LONG).show();

                tvUssdCode.setText("Code: *"+ code + "#");

                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(Uri.parse("tel:"+ "*" + code) + Uri.encode("#")));
                    startActivity(intent);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(btnSend.getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }




//                try{
//                    Intent intent = new Intent((Intent.ACTION_CALL));
//                    intent
//                }
//                catch (Exception ex){
//                    ex.printStackTrace();
//                    Toast.makeText(btnSend.getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
//                }

            }
        });
    }

    private void checkThePermissions() {

        String array [] = {android.Manifest.permission.CALL_PHONE};

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, array,
                    100);

        }
    }
}


