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

public class SenderForm extends AppCompatActivity {

    Button senderbtn;
    List<Person> persons = new ArrayList<>();
    TextInputEditText emailField, fullNameField, contactInfoField, countryField, addressField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sender_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailField = findViewById(R.id.senderFormEmailInput);
        senderbtn = findViewById(R.id.senderForm_button);

        emailField = findViewById(R.id.senderFormEmailInput);
        fullNameField = findViewById(R.id.senderFormFullNameInput);
        contactInfoField = findViewById(R.id.senderFormContactInfoInput);
        countryField = findViewById(R.id.senderFormCountryInput);
        addressField = findViewById(R.id.senderFormAddressInput);
        senderbtn = findViewById(R.id.senderForm_button);

        senderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String fullName = fullNameField.getText().toString();
                String contactInfo = contactInfoField.getText().toString();
                String country = countryField.getText().toString();
                String address = addressField.getText().toString();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(getFilesDir(), "senders.txt"), true))) {
                    String s = String.format("%s,%s,%s,%s,%s\n", email, fullName, contactInfo, country, address);
                    System.out.println(s);
                    writer.write(s);
                    Intent i = new Intent(SenderForm.this, ReceiverForm.class);
                    startActivity(i);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SenderForm.this, "Failed to save email", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
