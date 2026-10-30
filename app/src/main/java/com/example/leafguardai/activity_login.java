package com.example.leafguardai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Connect XML components with Java
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        // Login button click event
        loginButton.setOnClickListener(view -> {

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Check empty fields
            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        activity_login.this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                // Open Home Dashboard
                Toast.makeText(
                        activity_login.this,
                        "Welcome to LeafGuard AI!",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        activity_login.this,
                        HomeActivity.class
                );

                startActivity(intent);
                finish();
            }
        });
    }
}