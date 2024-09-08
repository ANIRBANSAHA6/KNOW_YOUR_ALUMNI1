package com.example.know_your_alumni;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner = findViewById(R.id.spinner);
        EditText id = findViewById(R.id.college_id);
        EditText pass = findViewById(R.id.password);
        EditText et_confirm = findViewById(R.id.confirm_password);
        Button btn = findViewById(R.id.register_btn);
        TextView SignInText = findViewById(R.id.Signintxt);





        // Set the click listener for the register button
        btn.setOnClickListener(view -> {
            String collegeId = id.getText().toString().trim();
            String password = pass.getText().toString().trim();
            String confirm = et_confirm.getText().toString().trim();
            DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "college_details", null, 1);

            if (collegeId.length() == 0 || password.length() == 0) {
                Toast.makeText(this, "PLEASE FILL THE FUCKING DETAILS !!", Toast.LENGTH_SHORT).show();
            } else {
                if (password.compareTo(confirm) == 0) {

                    db.register(collegeId, password);
                    Toast.makeText(this, "RECORD INSERTED", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, VerifyActivity.class));

                } else {
                    Toast.makeText(this, "password is not written correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignInText.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(RegisterActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
        });

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.enter_your_roles, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Spinner selection code
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(RegisterActivity.this, "Selected: " + selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

    }


}
