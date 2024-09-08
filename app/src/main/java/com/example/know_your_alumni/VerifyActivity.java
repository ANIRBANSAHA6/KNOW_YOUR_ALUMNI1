package com.example.know_your_alumni;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    EditText phone, otp;
    Button btngenOTP;
    Button BtnVerify;
    FirebaseAuth mAuth;
    String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        phone = findViewById(R.id.phone_number);
        otp = findViewById(R.id.otp);
        btngenOTP = findViewById(R.id.Send_otp_btn);
        BtnVerify = findViewById(R.id.verify_btn);
        mAuth = FirebaseAuth.getInstance();

        btngenOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    Toast.makeText(VerifyActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                } else {
                    String number = phone.getText().toString();

                }

            }
        });

        BtnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyActivity.this, SuccessActivity.class);
                startActivity(intent);
            }
        });
    }

}