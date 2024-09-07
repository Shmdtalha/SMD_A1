package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReviewInfo extends AppCompatActivity {

    List<Person> receivers = null;
    List<Person> senders = null;
    private LinearLayout textContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textContainer = findViewById(R.id.textContainer);


        File receiversFile = new File(getFilesDir(), "receivers.txt");
        File sendersFile = new File(getFilesDir(), "senders.txt");

        try (BufferedReader receiverReader = new BufferedReader(new FileReader(receiversFile));
             BufferedReader senderReader = new BufferedReader(new FileReader(sendersFile))) {

            String lineReceiver = null, lineSender = null;
            while ((lineSender = senderReader.readLine()) != null || (lineReceiver = receiverReader.readLine()) != null) {
                if (lineSender != null) System.out.println("Sender: " + lineSender);
                if (lineReceiver != null) System.out.println("Receiver: " + lineReceiver);
                if (lineSender != null) addText("Sender - " + lineSender);
                if (lineReceiver != null) addText("Receiver - " + lineReceiver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ReviewInfo.this, SenderForm.class);
            startActivity(intent);
        });



    }

    private void addText(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(0, 16, 0, 16);
        textContainer.addView(textView);
    }
}