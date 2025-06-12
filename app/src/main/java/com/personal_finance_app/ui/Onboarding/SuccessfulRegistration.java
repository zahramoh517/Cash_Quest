package com.personal_finance_app.ui.Onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.personal_finance_app.ExpensesActivity;
import com.personal_finance_app.GoalsActivity;
import com.personal_finance_app.R;
import android.content.SharedPreferences;

public class SuccessfulRegistration extends AppCompatActivity {

    private TextView welcomeMessage;
    private ImageButton proceedButton;
    private String userName; // To store the user's name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_successful);

        // Initialize views
        welcomeMessage = findViewById(R.id.welcomeMessage);
        proceedButton = findViewById(R.id.proceedButton);

        // Get the user's name from the Intent extras
        Intent intent = getIntent();
        userName = intent.getStringExtra("USER_NAME");

        //save to shared preferences
        saveUsernameToPreferences(userName);

        // Set the welcome message
        welcomeMessage.setText("Welcome, " + userName + "!");

        // Set up the proceed button
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeActivity();
            }
        });
    }

    private void saveUsernameToPreferences(String username) {
        if (username != null) {
            SharedPreferences preferences = getSharedPreferences("personal_finance_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_name", username);
            editor.apply(); // Save changes
        }
    }
    private void launchHomeActivity() {
        Intent intent = new Intent(SuccessfulRegistration.this, ExpensesActivity.class);
        startActivity(intent);
        finish();
    }
}