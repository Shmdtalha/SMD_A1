package com.example.a1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReceiverForm extends AppCompatActivity {

    Button receiverbtn;
    List<Person> persons = new ArrayList<>();
    TextInputEditText emailField, fullNameField, contactInfoField, countryField, addressField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        receiverbtn = findViewById(R.id.receiverForm_button);


        emailField = findViewById(R.id.receiverFormEmailInput);
        fullNameField = findViewById(R.id.receiverFormFullNameInput);
        contactInfoField = findViewById(R.id.receiverFormContactInfoInput);
        countryField = findViewById(R.id.receiverFormCountryInput);
        addressField = findViewById(R.id.receiverFormAddressInput);
        receiverbtn = findViewById(R.id.receiverForm_button);

        receiverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String fullName = fullNameField.getText().toString();
                String contactInfo = contactInfoField.getText().toString();
                String country = countryField.getText().toString();
                String address = addressField.getText().toString();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(getFilesDir(), "receivers.txt"), true))) {
                    writer.write(String.format("%s,%s,%s,%s,%s\n", email, fullName, contactInfo, country, address));
                    Intent i = new Intent(ReceiverForm.this, ReviewInfo.class);
                    startActivity(i);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ReceiverForm.this, "Failed to save email", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
